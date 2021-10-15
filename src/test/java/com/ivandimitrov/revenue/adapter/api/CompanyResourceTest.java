package com.ivandimitrov.revenue.adapter.api;

import com.ivandimitrov.revenue.RevenueApplication;
import com.ivandimitrov.revenue.application.CompanyApplicationService;
import com.ivandimitrov.revenue.domain.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevenueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyResourceTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private final CompanyApplicationService companyApplicationService = mock(CompanyApplicationService.class);

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    public void saveCompany() throws Exception {
        when(companyApplicationService.index(any())).thenReturn(new Company());

        // Send a message to the REST API
        String companyRequest = "{" +
                "\"name\":\"save_test\"," +
                "\"value\":100" +
                "}";

        this.mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(companyRequest))
                .andExpect(status().isOk());

    }
}