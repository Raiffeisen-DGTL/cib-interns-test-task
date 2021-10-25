package ru.simbial.cibinternstesttask.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CottonPartComparisonOperation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");
    /*DEFAULT("");*/

    private final String label;

    CottonPartComparisonOperation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> getLabels() {
        return Arrays.stream(CottonPartComparisonOperation
                .values())
                .map(CottonPartComparisonOperation::getLabel)
                .collect(Collectors.toList());
    }

    public static CottonPartComparisonOperation getByLabel(String label) {
        for(CottonPartComparisonOperation v : values())
            if(v.getLabel().equalsIgnoreCase(label)) return v;
        throw new IllegalArgumentException();
    }

}
