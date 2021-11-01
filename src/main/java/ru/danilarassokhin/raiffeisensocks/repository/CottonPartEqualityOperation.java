package ru.danilarassokhin.raiffeisensocks.repository;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import ru.danilarassokhin.raiffeisensocks.functional.SpecificationFunctional;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

/**
 * Contains operations for cotton part comparison.
 */
public enum CottonPartEqualityOperation {

  /**
   * Count socks with cotton part is more than given.
   */
  MORE_THAN("moreThan", (value) ->
      (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("cottonPart"), value)
  ),

  /**
   * Count socks with cotton part is less than given.
   */
  LESS_THAN("lessThan", CottonPartEqualityOperation::lessThan),

  /**
   * Count socks with given cotton part.
   */
  EQUAL("equal", CottonPartEqualityOperation::equal),

  /**
   * Empty operation with no specification.
   */
  EMPTY("", null);

  private static final Map<String, CottonPartEqualityOperation> values;

  static {
    values = Arrays.stream(
        CottonPartEqualityOperation.values()
    ).collect(
        Collectors.toMap(
            CottonPartEqualityOperation::getCode, Function.identity()
        )
    );
  }

  private final SpecificationFunctional<Socks, Byte> specification;
  private final String code;

  /**
   * Basic operation constructor.
   *
   * @param code          Operation code to be used in requests
   * @param specification SQL query specification to filter valid cotton part
   */
  CottonPartEqualityOperation(String code, SpecificationFunctional<Socks, Byte> specification) {
    this.specification = specification;
    this.code = code;
  }

  /**
   * Gets all exist codes.
   *
   * @return Set of all exist codes
   */
  public static Set<String> codes() {
    return values.keySet();
  }

  /**
   * Searches for
   * {@link ru.danilarassokhin.raiffeisensocks.repository.CottonPartEqualityOperation} by it's code.
   *
   * @param code Code of operation to search
   * @return Optional
   *     {@link ru.danilarassokhin.raiffeisensocks.repository.CottonPartEqualityOperation}
   */
  public static Optional<CottonPartEqualityOperation> getByCode(String code) {
    return Optional.ofNullable(
        values.get(code)
    );
  }

  private static Specification<Socks> equal(Byte value) {
    return (root, query, criteriaBuilder)
        -> criteriaBuilder.equal(root.get("cottonPart"), value);
  }

  private static Specification<Socks> lessThan(Byte value) {
    return (root, query, criteriaBuilder)
        -> criteriaBuilder.lessThan(root.get("cottonPart"), value);
  }

  /**
   * Creates specification from given value.
   *
   * @param value Value to use in specification
   * @return Specification to search socks with cotton part
   */
  public Specification<Socks> create(Byte value) {
    return specification.create(value);
  }

  /**
   * Checks if operation has any specification.
   *
   * @return true if specification is not null
   */
  public boolean hasSpecification() {
    return specification != null;
  }

  /**
   * Returns operation code to be used in requests.
   *
   * @return Operation code
   */
  public String getCode() {
    return code;
  }
}
