package pl.mikus.learning.jobapp.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();
    Optional<Job> createJob(Job job);
    Optional<Job> findJob(Long id);
    Optional<Job> updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
