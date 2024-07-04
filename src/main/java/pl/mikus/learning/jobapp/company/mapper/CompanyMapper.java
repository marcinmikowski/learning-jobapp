package pl.mikus.learning.jobapp.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mikus.learning.jobapp.company.Company;
import pl.mikus.learning.jobapp.job.Job;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<Company> toDto(List<Company> companies);

    Company toDto(Company company);

    @Mapping(source = "company", target = "company", ignore = true)
    Job toJob(Job job);

    List<Job> toJobDto(List<Job> job);

    default Optional<Company> toDto(Optional<Company> company) {
        return company.map(this::toDto);
    }
}
