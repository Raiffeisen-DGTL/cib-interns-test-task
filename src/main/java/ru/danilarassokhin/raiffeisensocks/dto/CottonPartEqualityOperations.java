package ru.danilarassokhin.raiffeisensocks.dto;

/**
 * Contains operations for cotton part comparison
 */
public enum CottonPartEqualityOperations {

    moreThan("countWhereCottonPartMoreThan"),
    lessThan("countWhereCottonPartLessThan"),
    equal("countWhereCottonPartIs")
    ;

    /**
     * Name of named query in {@link ru.danilarassokhin.raiffeisensocks.model.Socks}
     */
    private final String queryName;

    CottonPartEqualityOperations(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryName() {
        return queryName;
    }
}
