package com.businessgenie.maxtakeawayservice.service;


import com.businessgenie.maxtakeawayservice.model.MaxTakeAway;
import com.businessgenie.maxtakeawayservice.util.exception.NoMaxTakeAwayExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayAlreadyExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayNotExistsException;
import java.util.List;
import java.util.UUID;

public interface MaxTakeAwayService {

    public MaxTakeAway createMaxTakeAway(MaxTakeAway users) throws MaxTakeAwayAlreadyExistsException;

    public List<MaxTakeAway> getAllMaxTakeAway() throws NoMaxTakeAwayExistsException;

    public MaxTakeAway getMaxTakeAway(UUID uuid) throws MaxTakeAwayNotExistsException;
    public MaxTakeAway updateMaxTakeAway(MaxTakeAway users)  throws MaxTakeAwayNotExistsException;

    public void deleteMaxTakeAway(UUID uuid) throws MaxTakeAwayNotExistsException;
}
