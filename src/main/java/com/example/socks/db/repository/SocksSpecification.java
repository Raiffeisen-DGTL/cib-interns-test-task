package com.example.socks.db.repository;

import com.example.socks.db.entity.Socks;
import org.springframework.data.jpa.domain.Specification;

public class SocksSpecification {

    public static Specification<Socks> socksByCottonPart(final int cottonPart) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cottonPart"), cottonPart);
        }

    public static Specification<Socks> socksByColor(final String color) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Socks> cottonPartMoreThan(final int cottonPart) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("cottonPart"), cottonPart);
    }
    public static Specification<Socks> cottonPartLessThan(final int cottonPart) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("cottonPart"), cottonPart);
    }
}
