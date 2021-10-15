package com.ivandimitrov.revenue.adapter.api.resource;

import com.ivandimitrov.revenue.adapter.api.request.SaveCompanyRequest;
import com.ivandimitrov.revenue.application.CompanyApplicationService;
import com.ivandimitrov.revenue.application.command.IndexCompanyCommand;
import com.ivandimitrov.revenue.application.port.CompanyPort;
import com.ivandimitrov.revenue.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    private final CompanyApplicationService companyApplicationService;
    private final CompanyPort companyPort;

    @Autowired
    public CompanyResource(CompanyApplicationService companyApplicationService, CompanyPort companyPort) {
        this.companyApplicationService = companyApplicationService;
        this.companyPort = companyPort;
    }

    @GetMapping(path = "/all")
    public Map<String, Integer> getAllCompanies() {
        return companyPort.findAll().stream()
                .collect(Collectors.toMap(Company::getName, Company::getValue));
    }

    @GetMapping(path = "/sum")
    public Integer getAggregateOfValues() {
        return companyPort.findAll().stream()
                .mapToInt(Company::getValue)
                .sum();
    }

    @PostMapping
    public Company saveCompany(@Valid @RequestBody SaveCompanyRequest saveCompanyRequest) {
        final var command = IndexCompanyCommand.builder()
                .name(saveCompanyRequest.getName())
                .value(saveCompanyRequest.getValue())
                .build();

        return companyApplicationService.index(command);
    }
}
