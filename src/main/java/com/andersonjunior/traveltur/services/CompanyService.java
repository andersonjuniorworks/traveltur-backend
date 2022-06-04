package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.models.Company;
import com.andersonjunior.traveltur.repositories.CompanyRepository;
import com.andersonjunior.traveltur.services.exceptions.DataIntegrityException;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable).getContent();
    }

    public Company findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public Long count() {
        Long count = companyRepository.count();
        return count;
    }

    @Transactional
    public Company insert(Company company) {
        company.setId(null);
        return companyRepository.save(company);
    }

    @Transactional
    public Company update(Company company) {
        Company newCompany = findById(company.getId());
        updateData(newCompany, company);
        return companyRepository.save(newCompany);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            companyRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir essa empresa!");
        }
    }

    private void updateData(Company newCompany, Company company) {
        newCompany.setBusinessName(company.getBusinessName());
        newCompany.setZipcode(company.getZipcode());
        newCompany.setAddress(company.getAddress());
        newCompany.setNumber(company.getNumber());
        newCompany.setComplement(company.getComplement());
        newCompany.setNeighborhood(company.getNeighborhood());
        newCompany.setState(company.getState());
        newCompany.setCity(company.getCity());
        newCompany.setPhoneNumber(company.getPhoneNumber());
        newCompany.setEmail(company.getEmail());
    }

}
