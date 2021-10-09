package ru.strelchm.raiffeisenchallenge.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.Specification;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.domain.SockColor;

@Data
@Accessors(chain = true)
public class SockCriteria {
    private SockColor color;
    private SockCompareOperation compareOperation;
    private Integer comparedCottonPart;

    public Specification<Sock> getSockColorSpecification() {
        return (root, query, criteriaBuilder) -> color == null ? null : criteriaBuilder.equal(root.get("color"), color);
    }

    public Specification<Sock> getCompareCottonPartSpecification() {
        return (root, query, criteriaBuilder) -> {
            if (compareOperation == null) {
                return null;
            }
            switch (compareOperation) {
                case EQUAL:
                    return criteriaBuilder.equal(root.get("cottonPart"), comparedCottonPart);
                case LESS_THAN:
                    return criteriaBuilder.lessThan(root.get("cottonPart"), comparedCottonPart);
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(root.get("cottonPart"), comparedCottonPart);
                default:
                    throw new UnsupportedOperationException("Unsupported compareOperation: " + compareOperation);
            }
        };
    }
}
