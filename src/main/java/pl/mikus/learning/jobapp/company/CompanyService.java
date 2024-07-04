package pl.mikus.learning.jobapp.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();
    Optional<Company> createCompany(Company company);
    Optional<Company> findCompany(Long id);
    Optional<Company> updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);
}
