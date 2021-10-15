package com.ivandimitrov.revenue.application;

import com.ivandimitrov.revenue.application.command.IndexCompanyCommand;
import com.ivandimitrov.revenue.application.port.CompanyPort;
import com.ivandimitrov.revenue.domain.Company;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static java.util.Objects.requireNonNull;

@Service
public class CompanyApplicationService {
    private final CompanyPort companyPort;

    @Autowired
    public CompanyApplicationService(CompanyPort companyPort) {
        this.companyPort = companyPort;
    }

    public Company index(@NonNull IndexCompanyCommand command) {
        requireNonNull(command, "IndexCompanyCommand is required.");

        final var company = Company.saveOn(command);
        return companyPort.save(company);
    }
}
