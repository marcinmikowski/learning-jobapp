package pl.mikus.learning.jobapp.review;

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
import pl.mikus.learning.jobapp.job.Job;

@Slf4j
@RestController
@RequestMapping("/companies/{companyId}/reviews")
@LogTimeExecution
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.findAll(companyId));
    }

    @PostMapping
    public ResponseEntity<?> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        return ResponseEntity.of(reviewService.createReview(companyId, review));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return ResponseEntity.of(reviewService.findReview(companyId, reviewId));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long companyId,
                                          @PathVariable Long reviewId,
                                          @RequestBody Review review) {
        return ResponseEntity.of(reviewService.updateReview(companyId, reviewId, review));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteJobById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if (reviewService.deleteReview(companyId, reviewId)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

}
