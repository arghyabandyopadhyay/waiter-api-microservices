package com.businessgenie.maxtakeawayservice.service;

import com.businessgenie.maxtakeawayservice.model.MaxTakeAway;
import com.businessgenie.maxtakeawayservice.repository.MaxTakeAwayRepository;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayAlreadyExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.NoMaxTakeAwayExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class MaxTakeAwayServiceImpl implements MaxTakeAwayService {

    @Autowired
    MaxTakeAwayRepository maxTakeAwayRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public MaxTakeAwayServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public MaxTakeAway updateMaxTakeAway(MaxTakeAway maxTakeAway) throws MaxTakeAwayNotExistsException {
        if(maxTakeAwayRepository.existsById(maxTakeAway.getId())) return maxTakeAwayRepository.save(maxTakeAway);
        else throw new MaxTakeAwayNotExistsException();
    }

    @Override
    public void deleteMaxTakeAway(UUID uuid) throws MaxTakeAwayNotExistsException {
        if(maxTakeAwayRepository.existsById(uuid)) maxTakeAwayRepository.deleteById(uuid);
        else throw new MaxTakeAwayNotExistsException();
    }

    @Override
    public List<MaxTakeAway> getAllMaxTakeAway() {
        return maxTakeAwayRepository.findAll();
    }

    @Override
    public MaxTakeAway getMaxTakeAway(UUID uuid) throws MaxTakeAwayNotExistsException {
        return maxTakeAwayRepository.findById(uuid).orElseThrow(MaxTakeAwayNotExistsException::new);
    }


    @Override
    public MaxTakeAway createMaxTakeAway(MaxTakeAway maxTakeAway)
            throws MaxTakeAwayAlreadyExistsException {
        if(maxTakeAwayRepository.findMaxTakeAway(maxTakeAway.getOutletId(), maxTakeAway.getCurrentDate()) == null)
            return maxTakeAwayRepository.save(maxTakeAway);
        else throw new MaxTakeAwayAlreadyExistsException();
    }
}
