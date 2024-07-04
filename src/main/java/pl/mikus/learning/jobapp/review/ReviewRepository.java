package pl.mikus.learning.jobapp.review;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikus.learning.jobapp.job.Job;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCompanyId(Long companyId);
    Optional<Review> findByCompanyIdAndId(Long companyId, Long reviewId);
}
