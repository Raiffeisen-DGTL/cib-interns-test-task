package ru.danilarassokhin.raiffeisensocks.functional;

import org.springframework.data.jpa.domain.Specification;

/**
 * Creates {@link org.springframework.data.jpa.domain.Specification}.
 *
 * @param <T> {@link javax.persistence.Entity} to use
 *            in {@link org.springframework.data.jpa.domain.Specification}
 * @param <V> Value type to use
 *            in {@link org.springframework.data.jpa.domain.Specification} predicate
 */
@FunctionalInterface
public interface SpecificationFunctional<T, V> {
  /**
   * Creates {@link org.springframework.data.jpa.domain.Specification}
   * with {@code value}.
   *
   * @param value Value to use in specification predicate
   * @return Specification to use in repository
   */
  Specification<T> create(V value);
}
