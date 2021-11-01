package ru.danilarassokhin.raiffeisensocks.repository;

import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

/**
 * Repository to perform operations with {@link ru.danilarassokhin.raiffeisensocks.model.Socks}.
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId>,
    JpaSpecificationExecutor<Socks> {

  /**
   * Creates specification for socks color.
   *
   * @param color Socks color to search
   * @return Specification of socks color
   */
  static Specification<Socks> ofColor(String color) {
    return (root, query, criteriaBuilder)
        -> criteriaBuilder.equal(root.get("color"), color);
  }

  /**
   * Gets socks for given color and cotton part.
   *
   * @param color      Socks color
   * @param cottonPart Socks cotton part
   * @return Optional socks count for given condition
   */
  Optional<Socks> findByColorAndCottonPartIs(String color, byte cottonPart);

}
