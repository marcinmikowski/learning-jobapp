package pl.mikus.learning.jobapp.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();

    Job createJob(Job job);

    Optional<Job> findJob(Long id);

    boolean deleteJob(Long id);

    Optional<Job> updateJob(Long id, Job job);
}
