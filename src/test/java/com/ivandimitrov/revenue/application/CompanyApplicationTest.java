package com.ivandimitrov.revenue.application;

import com.ivandimitrov.revenue.RevenueApplication;
import com.ivandimitrov.revenue.adapter.repository.CompanyRepository;
import com.ivandimitrov.revenue.application.command.IndexCompanyCommand;
import com.ivandimitrov.revenue.application.port.CompanyPort;
import com.ivandimitrov.revenue.domain.Company;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompanyApplicationTest {

    private final CompanyPort companyPort = mock(CompanyRepository.class);

    private final CompanyApplicationService companyApplicationService = new CompanyApplicationService(companyPort);

    @Test
    @Transactional
    public void indexCompany() {
        final var companyName = "test_application";
        final var companyValue = 400;
        final var testCompany = Company.builder()
                .name(companyName)
                .value(companyValue)
                .build();
        when(companyPort.save(any(Company.class))).thenReturn(testCompany);

        final var command = IndexCompanyCommand.builder()
                .name(companyName)
                .value(companyValue)
                .build();

        final var captor = ArgumentCaptor.forClass(Company.class);
        companyApplicationService.index(command);

        verify(companyPort).save(captor.capture());
        Assert.assertEquals(captor.getValue().getName(), companyName);
        Assert.assertEquals(captor.getValue().getValue(), (Integer) companyValue);
    }
}