package com.businessgenie.outletservice.service;

import com.businessgenie.outletservice.model.Outlets;
import com.businessgenie.outletservice.repository.OutletsRepository;
import com.businessgenie.outletservice.util.exception.NoOutletExistsException;
import com.businessgenie.outletservice.util.exception.OutletAlreadyExistsException;
import com.businessgenie.outletservice.util.exception.OutletNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class OutletServiceImpl implements OutletService {

    @Autowired
    OutletsRepository outletsRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public OutletServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Outlets createOutlet(Outlets outlet) throws OutletAlreadyExistsException {
        if (outletsRepository.existsById(outlet.getId())) {
            throw new OutletAlreadyExistsException();
        }
        return outletsRepository.save(outlet);
    }

    @Override
    public List<Outlets> getAllOutlets() throws NoOutletExistsException {
        List<Outlets> outlets = outletsRepository.findAll();
        if (outlets.isEmpty()) {
            throw new NoOutletExistsException();
        }
        return outlets;
    }

    @Override
    public Outlets getOutlet(UUID uuid) throws OutletNotExistsException {
        return outletsRepository.findById(uuid).orElseThrow(OutletNotExistsException::new);
    }

    @Override
    public List<Outlets> getAllOutletsForClient(String clientId) throws NoOutletExistsException {
        List<Outlets> outlets = outletsRepository.findByClientId(clientId);
        if (outlets.isEmpty()) {
            throw new NoOutletExistsException();
        }
        return outlets;
    }

    @Override
    public Outlets updateOutlet(Outlets outlet) throws OutletNotExistsException {
        if (outletsRepository.existsById(outlet.getId())) {
            return outletsRepository.save(outlet);
        } else {
            throw new OutletNotExistsException();
        }
    }

    @Override
    public void deleteOutlet(UUID uuid) throws OutletNotExistsException {
        if (outletsRepository.existsById(uuid)) {
            outletsRepository.deleteById(uuid);
        } else {
            throw new OutletNotExistsException();
        }
    }
}
