package com.example.task.Specification;

import com.example.task.Other.Socks;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SocksSpecification {

    public static Specification<Socks> contains(String word){
        return (Specification<Socks>)(root,criteriaQuery,criteriaBuilder) -> criteriaBuilder.like(root.get("color"), "%" + word + "%");
        }

    public static Specification<Socks> cottonPartGreaterorEq(Integer value){
        return (Specification<Socks>) (root, criteriaQuery,criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cottonPart"),value);
        };
    }

    public static Specification<Socks> cottonPartLessorEq(Integer value){
        return (Specification<Socks>) (root, criteriaQuery,criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("cottonPart"),value);
        };
    }
}
