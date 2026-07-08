package com.businessgenie.users.service;


import com.businessgenie.users.Constants.ContactTypes;
import com.businessgenie.users.model.Login;
import com.businessgenie.users.model.Users;
import com.businessgenie.users.util.exception.NoUsersExistsException;
import com.businessgenie.users.util.exception.UserAlreadyExistsException;
import com.businessgenie.users.util.exception.UserAuthenticationException;
import com.businessgenie.users.util.exception.UserNotExistsException;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    public Users createUser(Users users) throws UserAlreadyExistsException;

    public List<Users> getAllUsers() throws NoUsersExistsException;

    public Users getUser(UUID uuid) throws UserNotExistsException;
    public Users authenticate(Login login) throws UserAuthenticationException,UserNotExistsException;
    public List<Users> searchUser(String searchText) throws NoUsersExistsException;
    public Users searchUser(String searchText, ContactTypes contactTypes) throws UserNotExistsException;

    public Users updateUser(Users users)  throws UserNotExistsException;

    public void deleteUser(UUID uuid) throws UserNotExistsException;
}
