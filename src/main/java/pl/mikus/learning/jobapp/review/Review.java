package pl.mikus.learning.jobapp.review;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.mikus.learning.jobapp.company.Company;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Double score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;
}
