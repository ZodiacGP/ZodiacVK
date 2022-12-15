package org.zodiac.common.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.zodiac.common.constant.CompositeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractSpecificationBuilder<T> {
    private final CompositeType compositeType = CompositeType.AND;
    private final List<Specification<T>> specifications = new ArrayList<>();

    public void addIfNotNull(Object parameter, Specification<T> specification) {
        if (parameter != null &&
                (!(parameter instanceof Collection) || !CollectionUtils.isEmpty((Collection<?>) parameter))) {
            specifications.add(specification);
        }
    }

    public Specification<T> build() {
        return specifications
                .stream()
                .reduce((first, second) -> this.compositeType == CompositeType.AND ? first.and(second) : first.or(second))
                .orElse((root, query, cb) -> cb.conjunction());
    }
}