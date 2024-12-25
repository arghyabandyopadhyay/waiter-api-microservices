package com.businessgenie.taxclassservice.service;


import com.businessgenie.taxclassservice.model.TaxClass;
import com.businessgenie.taxclassservice.util.exception.NoTaxClassExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassAlreadyExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassNotExistsException;
import java.util.List;
import java.util.UUID;

public interface TaxClassService {

    public TaxClass createTaxClass(TaxClass taxClass) throws TaxClassAlreadyExistsException;

    public List<TaxClass> getAllTaxClasses() throws NoTaxClassExistsException;

    public TaxClass getTaxClass(UUID uuid) throws TaxClassNotExistsException;

    public List<TaxClass> getAllTaxClassesForUser(String userId) throws NoTaxClassExistsException;

    public TaxClass updateTaxClass(TaxClass taxClass) throws TaxClassNotExistsException;

    public void deleteTaxClass(UUID uuid) throws TaxClassNotExistsException;
}
