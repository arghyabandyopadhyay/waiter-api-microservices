package com.businessgenie.outletservice.service;

import com.businessgenie.outletservice.model.Outlets;
import com.businessgenie.outletservice.util.exception.NoOutletExistsException;
import com.businessgenie.outletservice.util.exception.OutletAlreadyExistsException;
import com.businessgenie.outletservice.util.exception.OutletNotExistsException;
import java.util.List;
import java.util.UUID;

public interface OutletService {

    public Outlets createOutlet(Outlets outlet) throws OutletAlreadyExistsException;

    public List<Outlets> getAllOutlets() throws NoOutletExistsException;

    public Outlets getOutlet(UUID uuid) throws OutletNotExistsException;

    public List<Outlets> getAllOutletsForClient(String clientId) throws NoOutletExistsException;

    public Outlets updateOutlet(Outlets outlet) throws OutletNotExistsException;

    public void deleteOutlet(UUID uuid) throws OutletNotExistsException;
}
