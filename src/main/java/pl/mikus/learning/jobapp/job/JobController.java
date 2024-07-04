package pl.mikus.learning.jobapp.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mikus.learning.jobapp.common.LogTimeExecution;

@Slf4j
@RestController
@RequestMapping("/jobs")
@LogTimeExecution
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        return ResponseEntity.of(jobService.createJob(job));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findJobById(@PathVariable Long id) {
        return ResponseEntity.of(jobService.findJob(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return ResponseEntity.of(jobService.updateJob(id, job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable Long id) {
        if (jobService.deleteJob(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
