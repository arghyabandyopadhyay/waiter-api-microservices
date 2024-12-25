package com.businessgenie.taxclassservice.service;

import com.businessgenie.taxclassservice.model.TaxClass;
import com.businessgenie.taxclassservice.repository.TaxClassRepository;
import com.businessgenie.taxclassservice.util.exception.NoTaxClassExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassAlreadyExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class TaxClassServiceImpl implements TaxClassService {

    @Autowired
    TaxClassRepository taxClassRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TaxClassServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TaxClass createTaxClass(TaxClass taxClass) throws TaxClassAlreadyExistsException {
        if (taxClassRepository.existsById(taxClass.getId())) {
            throw new TaxClassAlreadyExistsException();
        }
        return taxClassRepository.save(taxClass);
    }

    @Override
    public List<TaxClass> getAllTaxClasses() throws NoTaxClassExistsException {
        List<TaxClass> taxClasses = taxClassRepository.findAll();
        if (taxClasses.isEmpty()) {
            throw new NoTaxClassExistsException();
        }
        return taxClasses;
    }

    @Override
    public TaxClass getTaxClass(UUID uuid) throws TaxClassNotExistsException {
        return taxClassRepository.findById(uuid).orElseThrow(TaxClassNotExistsException::new);
    }

    @Override
    public List<TaxClass> getAllTaxClassesForUser(String userId) throws NoTaxClassExistsException {
        List<TaxClass> taxClasses = taxClassRepository.findByUserId(userId);
        if (taxClasses.isEmpty()) {
            throw new NoTaxClassExistsException();
        }
        return taxClasses;
    }

    @Override
    public TaxClass updateTaxClass(TaxClass taxClass) throws TaxClassNotExistsException {
        if (taxClassRepository.existsById(taxClass.getId())) {
            return taxClassRepository.save(taxClass);
        } else {
            throw new TaxClassNotExistsException();
        }
    }

    @Override
    public void deleteTaxClass(UUID uuid) throws TaxClassNotExistsException {
        if (taxClassRepository.existsById(uuid)) {
            taxClassRepository.deleteById(uuid);
        } else {
            throw new TaxClassNotExistsException();
        }
    }
}
