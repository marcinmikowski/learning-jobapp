package pl.mikus.learning.jobapp.review.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mikus.learning.jobapp.review.Review;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "company", target = "company", ignore = true)
    Review toDto(Review review);

    List<Review> toDto(List<Review> review);

    default Optional<Review> toDto(Optional<Review> review) {
        return review.map(this::toDto);
    }
}
