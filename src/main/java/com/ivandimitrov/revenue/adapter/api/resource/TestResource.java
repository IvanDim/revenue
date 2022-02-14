package com.ivandimitrov.revenue.adapter.api.resource;

import com.ivandimitrov.revenue.application.CompanyApplicationService;
import com.ivandimitrov.revenue.application.port.CompanyPort;
import com.ivandimitrov.revenue.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
public class TestResource {

    private final CompanyApplicationService companyApplicationService;
    private final CompanyPort companyPort;

    @Autowired
    public TestResource(CompanyApplicationService companyApplicationService, CompanyPort companyPort) {
        this.companyApplicationService = companyApplicationService;
        this.companyPort = companyPort;
    }

    @GetMapping(path = "/all")
    public Map<String, Integer> getAllCompanies() {
        return companyPort.findAll().stream()
                .collect(Collectors.toMap(Company::getName, Company::getValue));
    }

}
