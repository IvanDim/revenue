package com.ivandimitrov.revenue.domain;

import com.ivandimitrov.revenue.application.command.IndexCompanyCommand;
import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;

public class CompanyTest {

    @Test
    @Transactional
    public void indexCompany() {
        final var companyName = "test_application";
        final var companyValue = 400;
        final var command = IndexCompanyCommand.builder()
                .name(companyName)
                .value(companyValue)
                .build();

        final var actualCompany = Company.saveOn(command);

        Assert.assertEquals(actualCompany.getName(), companyName);
        Assert.assertEquals(actualCompany.getValue(), (Integer) companyValue);
    }
}