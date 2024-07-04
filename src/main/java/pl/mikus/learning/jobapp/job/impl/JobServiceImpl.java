package pl.mikus.learning.jobapp.job.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mikus.learning.jobapp.company.Company;
import pl.mikus.learning.jobapp.company.CompanyRepository;
import pl.mikus.learning.jobapp.job.Job;
import pl.mikus.learning.jobapp.job.JobRepository;
import pl.mikus.learning.jobapp.job.JobService;
import pl.mikus.learning.jobapp.job.mapper.JobMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Job> findAll() {
        return jobMapper.toDto(jobRepository.findAll());
    }

    @Override
    public Optional<Job> createJob(Job job) {
        final Optional<Company> company = findCompanyForJob(job);
        if (company.isEmpty() && isCompanyIdForJobSet(job)) {
            return Optional.empty();
        }
        return jobMapper.toDto(Optional.of(
                jobRepository.save(
                        job.toBuilder().id(null).company(company.orElse(null)).build()
                ))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Job> findJob(Long id) {
        return jobMapper.toDto(jobRepository.findById(id));
    }

    @Override
    public Optional<Job> updateJob(Long id, Job job) {
        final Optional<Company> company = findCompanyForJob(job);
        if (company.isEmpty() && isCompanyIdForJobSet(job)) {
            return Optional.empty();
        }
        return jobMapper.toDto(jobRepository.findById(id)
                .map(_ -> jobRepository.save(
                        job.toBuilder().company(company.orElse(null)).id(id).build())
                ));
    }

    private Optional<Company> findCompanyForJob(Job job) {
        if (isCompanyIdForJobSet(job)) {
            return companyRepository.findById(job.getCompany().getId());
        }
        return Optional.empty();
    }

    private boolean isCompanyIdForJobSet(Job job) {
        return Objects.nonNull(job.getCompany()) && Objects.nonNull(job.getCompany().getId());
    }

    @Override
    public boolean deleteJob(Long id) {
        return jobRepository.findById(id).map(j -> {
            jobRepository.delete(j);
            return true;
        }).orElse(false);
    }
}
