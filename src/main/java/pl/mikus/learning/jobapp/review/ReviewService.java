package pl.mikus.learning.jobapp.review;

import pl.mikus.learning.jobapp.job.Job;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll(Long companyId);
    Optional<Review> createReview(Long companyId, Review review);
    Optional<Review> findReview(Long companyId, Long reviewId);
    Optional<Review> updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
