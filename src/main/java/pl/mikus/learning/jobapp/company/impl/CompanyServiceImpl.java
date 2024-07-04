package pl.mikus.learning.jobapp.company.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mikus.learning.jobapp.company.Company;
import pl.mikus.learning.jobapp.company.CompanyRepository;
import pl.mikus.learning.jobapp.company.CompanyService;
import pl.mikus.learning.jobapp.company.mapper.CompanyMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Company> findAll() {
        return companyMapper.toDto(companyRepository.findAll());
    }

    @Override
    public Optional<Company> createCompany(Company company) {
        Company savedCompany = companyMapper.toDto(companyRepository.save(company.toBuilder().id(null).build()));
        return companyRepository.findById(savedCompany.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Company> findCompany(Long id) {
        return companyMapper.toDto(companyRepository.findById(id));
    }

    @Override
    public Optional<Company> updateCompany(Long id, Company company) {
        companyRepository.findById(id)
                .map(_ -> companyRepository.save(company.toBuilder().id(id).build()));
        return companyMapper.toDto(companyRepository.findById(id));
    }

    @Override
    public boolean deleteCompany(Long id) {
        return companyRepository.findById(id).map(j -> {
            companyRepository.delete(j);
            return true;
        }).orElse(false);
    }
}
