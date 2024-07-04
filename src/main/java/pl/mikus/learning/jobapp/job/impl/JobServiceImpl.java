package pl.mikus.learning.jobapp.job.impl;

import org.springframework.stereotype.Service;
import pl.mikus.learning.jobapp.job.Job;
import pl.mikus.learning.jobapp.job.JobService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
class JobServiceImpl implements JobService {
    private static final List<Job> jobs = new CopyOnWriteArrayList<>();
    private static final AtomicLong integer = new AtomicLong(1);

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job createJob(Job job) {
        final Job jobToSave = job.toBuilder().id(integer.getAndIncrement()).build();
        jobs.add(jobToSave);
        return jobToSave;
    }

    @Override
    public Optional<Job> findJob(Long id) {
        return jobs.stream().filter(j -> j.getId().equals(id)).findFirst();
    }

    @Override
    public boolean deleteJob(Long id) {
        return jobs.removeIf(j -> j.getId().equals(id));
    }

    @Override
    public Optional<Job> updateJob(Long id, Job job) {
        return findJob(id).map(j -> {
            var r = job.toBuilder().id(j.getId()).build();
            jobs.set(jobs.indexOf(j), r);
            return r;
        });
    }
}
