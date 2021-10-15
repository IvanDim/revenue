package com.ivandimitrov.revenue.adapter.repository;

import com.ivandimitrov.revenue.RevenueApplication;
import com.ivandimitrov.revenue.domain.Company;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevenueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyRepositoryIntegrationTests {

    @Autowired
    private CompanyRepository companyRepository;

    // Test inserting new record and fetching it
    @Test
    @Transactional
    public void saveCompany_ok() {
        final var companyListBefore = companyRepository.findAll();

        final var company = Company.builder()
                .name("test")
                .value(100)
                .build();
        companyRepository.saveAndFlush(company);

        final var companyListAfter = companyRepository.findAll();
        Assert.assertTrue(companyListAfter.size() > companyListBefore.size());
    }
}