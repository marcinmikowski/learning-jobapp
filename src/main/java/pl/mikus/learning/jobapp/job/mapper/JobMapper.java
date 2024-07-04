package pl.mikus.learning.jobapp.job.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mikus.learning.jobapp.company.Company;
import pl.mikus.learning.jobapp.job.Job;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toDto(Job job);

    List<Job> toDto(List<Job> jobs);

    @Mapping(source = "jobs", target = "jobs", ignore = true)
    Company toDto(Company company);

    default Optional<Job> toDto(Optional<Job> job) {
        return job.map(this::toDto);
    }
}
