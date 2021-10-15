package com.ivandimitrov.revenue.application.port;

import com.ivandimitrov.revenue.domain.Company;

import java.util.List;

public interface CompanyPort {
    List<Company> findAll();
    Company save(Company company);
}
