package ru.danilarassokhin.raiffeisensocks.repository;

import org.slf4j.LoggerFactory;
import ru.danilarassokhin.raiffeisensocks.functional.NoParamFunctional;
import ru.danilarassokhin.raiffeisensocks.functional.OneParamFunctional;
import ru.danilarassokhin.raiffeisensocks.service.impl.LoggerService;

/**
 * Contains operations for cotton part comparison
 */
public enum CottonPartEqualityOperations {

    moreThan(() -> LoggerFactory.getLogger(CottonPartEqualityOperations.class)
                    .info("Counting socks with countWhereCottonPartMoreThan named query..."),
                            "countWhereCottonPartMoreThan",
            (count) -> LoggerFactory.getLogger(CottonPartEqualityOperations.class)
                    .info("Found " + count + " socks by countWhereCottonPartMoreThan named query")
    ),
    lessThan("countWhereCottonPartLessThan", LoggerService::warn),
    equal("countWhereCottonPartIs", LoggerService::info)
    ;

    /**
     * Name of named query in {@link ru.danilarassokhin.raiffeisensocks.model.Socks}
     */
    private final String queryName;

    /**
     * Called before socks counting
     */
    private final NoParamFunctional before;

    /**
     * Called after socks counting with socks count param if
     */
    private final OneParamFunctional<Long> after;

    CottonPartEqualityOperations(NoParamFunctional before, String queryName, OneParamFunctional<Long> after) {
        this.queryName = queryName;
        this.before = before;
        this.after = after;
    }

    CottonPartEqualityOperations(NoParamFunctional before, String queryName) {
        this.queryName = queryName;
        this.before = before;
        this.after = null;
    }

    CottonPartEqualityOperations(String queryName) {
        this.queryName = queryName;
        this.before = null;
        this.after = null;
    }

    CottonPartEqualityOperations(String queryName, OneParamFunctional<Long> after) {
        this.queryName = queryName;
        this.before = null;
        this.after = after;
    }

    public String getQueryName() {
        return queryName;
    }

    public void before() {
        if(before != null) {
            before.action();
        }
    }

    public void after(Long count) {
        if(after != null) {
            after.action(count);
        }
    }
}
