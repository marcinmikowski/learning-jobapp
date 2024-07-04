package pl.mikus.learning.jobapp.review.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mikus.learning.jobapp.company.Company;
import pl.mikus.learning.jobapp.company.CompanyRepository;
import pl.mikus.learning.jobapp.company.CompanyService;
import pl.mikus.learning.jobapp.review.Review;
import pl.mikus.learning.jobapp.review.ReviewRepository;
import pl.mikus.learning.jobapp.review.ReviewService;
import pl.mikus.learning.jobapp.review.mapper.ReviewMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Transactional(readOnly = true)
    @Override
    public List<Review> findAll(Long companyId) {
        return reviewMapper.toDto(reviewRepository.findAllByCompanyId(companyId));
    }

    @Override
    public Optional<Review> createReview(Long companyId, Review review) {
        Optional<Company> company = companyService.findCompany(companyId);
        return reviewMapper.toDto(
                company.map(c -> reviewRepository.save(review.toBuilder().id(null).company(c).build()))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Review> findReview(Long companyId, Long reviewId) {
        return reviewMapper.toDto(reviewRepository.findByCompanyIdAndId(companyId, reviewId));
    }

    @Override
    public Optional<Review> updateReview(Long companyId, Long reviewId, Review review) {
        Optional<Review> reviewFound = reviewRepository.findByCompanyIdAndId(companyId, reviewId);
        return reviewMapper.toDto(reviewFound.map(r -> reviewRepository.save(
                r.toBuilder().title(review.getTitle())
                        .description(review.getDescription())
                        .score(review.getScore())
                        .build()
        )));
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Optional<Review> reviewFound = reviewRepository.findByCompanyIdAndId(companyId, reviewId);
        return reviewFound.map(review -> {
            reviewRepository.delete(review);
            return true;
        }).orElse(false);
    }
}
