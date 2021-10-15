package com.ivandimitrov.revenue.adapter.repository;

import com.ivandimitrov.revenue.application.port.CompanyPort;
import com.ivandimitrov.revenue.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyPort {
}
