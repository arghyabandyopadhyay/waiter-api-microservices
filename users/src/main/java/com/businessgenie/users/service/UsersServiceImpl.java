package com.businessgenie.users.service;


import com.businessgenie.users.Constants.ContactTypes;
import com.businessgenie.users.model.Login;
import com.businessgenie.users.model.Users;
import com.businessgenie.users.repository.UsersRepository;
import com.businessgenie.users.util.exception.UserAlreadyExistsException;
import com.businessgenie.users.util.exception.UserAuthenticationException;
import com.businessgenie.users.util.exception.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public UsersServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Users> searchUser(String searchText) {
        return usersRepository.findBySearchText(searchText);
    }

    @Override
    public Users searchUser(String searchText, ContactTypes contactTypes) throws UserNotExistsException {
        return switch (contactTypes) {
            case EmailID -> {
                Users user = usersRepository.findByEmailId(searchText);
                if (user == null) throw new UserNotExistsException();
                yield user;
            }
            case MobileNumber -> {
                Users user = usersRepository.findByMobileNumber(searchText);
                if (user == null) throw new UserNotExistsException();
                yield user;
            }
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public Users updateUser(Users users) throws UserNotExistsException {
        if(usersRepository.existsById(users.getId())) return usersRepository.save(users);
        else throw new UserNotExistsException();
    }

    @Override
    public void deleteUser(UUID uuid) throws UserNotExistsException {
        if(usersRepository.existsById(uuid)) usersRepository.deleteById(uuid);
        else throw new UserNotExistsException();
    }

    @Override
    public Users createUser(Users users) throws UserAlreadyExistsException {
        if(usersRepository.findByEmailId(users.getEmailId())!=null) throw new UserAlreadyExistsException();
        else {
            users.setLastLogin(LocalDateTime.now());
            return usersRepository.save(users);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(UUID uuid) throws UserNotExistsException {
        return usersRepository.findById(uuid).orElseThrow(UserNotExistsException::new);
    }

    @Override
    public Users authenticate(Login login) throws UserAuthenticationException,UserNotExistsException {
        switch (login.getLoginMethod()) {
            case "EmailId" -> {
                Users user = searchUser(login.getEmailId(), ContactTypes.EmailID);
                if (user.getEmailId().isEmpty() || user.getPassword().isEmpty() || login.getPassword().isEmpty())
                    throw new UserAuthenticationException();
                else if (Objects.equals(user.getPassword(), login.getPassword())) return user;
            }
            case "Mobile" -> {
                //add otp logic
                return searchUser(login.getMobileNumber(), ContactTypes.MobileNumber);
            }
            case "OAuth" -> {
                //add oauth logic
                return searchUser(login.getEmailId(), ContactTypes.EmailID);
            }
            case null, default -> throw new IllegalArgumentException();
        }
        return null;
    }
}
