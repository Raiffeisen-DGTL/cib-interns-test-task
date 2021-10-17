package com.github.furetur.raiffeisentask.restData;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.lang.Nullable;

public enum OperationType {
  MORE_THAN("moreThan"),
  LESS_THAN("lessThan"),
  EQUAL("equal");

  private static final Map<String, OperationType> BY_REST_VALUE =
      Arrays.stream(OperationType.values())
          .collect(Collectors.toMap(OperationType::getRestValue, x -> x));

  private final String restValue;

  public String getRestValue() {
    return this.restValue;
  }

  OperationType(String restValue) {
    this.restValue = restValue;
  }

  public static @Nullable OperationType getByRestValue(String restValue) {
    return BY_REST_VALUE.get(restValue);
  }
}
