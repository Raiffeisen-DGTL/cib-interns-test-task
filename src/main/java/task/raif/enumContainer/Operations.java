package task.raif.enumContainer;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operations {
    LESS_THAN("lessThan"),
    MORE_THAN("moreThan"),
    EQUAL("equal");

    private static final Map<String, Operations> titleMap = Arrays.stream(Operations.values())
                                                                  .collect(Collectors.toMap(Operations::getTitle,
                                                                                            Function.identity()));
    private String title;

    private Operations(String title) {
        this.title = title;
    }

    public static Optional<Operations> byTitle(String operation) {
        return Optional.ofNullable(titleMap.get(operation));
    }

    private String getTitle() {
        return title;
    }

}
