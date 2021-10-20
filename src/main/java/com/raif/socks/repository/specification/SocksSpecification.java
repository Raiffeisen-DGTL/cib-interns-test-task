package com.raif.socks.repository.specification;

import com.raif.socks.entity.SocksEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocksSpecification {
    public Specification<SocksEntity> getQuantity(String color, String operation, int cottonPart) {
        return (socks, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(socks.get("color"), color));
            switch (operation) {
                case "moreThan":
                    predicates.add(criteriaBuilder.greaterThan(socks.get("cottonPart"), cottonPart));
                    break;
                case "lessThan":
                    predicates.add(criteriaBuilder.lessThan(socks.get("cottonPart"), cottonPart));
                    break;
                case "equal":
                    predicates.add(criteriaBuilder.equal(socks.get("cottonPart"), cottonPart));
                    break;
                default:
                    throw new IllegalArgumentException("Operation not supported: " + operation);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
