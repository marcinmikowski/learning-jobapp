package pl.mikus.learning.jobapp.job;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Job {
    private final Long id;
    private final String title;
    private final String description;
    private final String minSalary;
    private final String maxSalary;
    private final String location;
}
