package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.components.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FinderFactoryTests {

    @Test
    public void createFinderLessThan() {
        String operation = "lessThan";

        FinderOperation finderLessThan = FinderFactory.createFinder(operation);

        assertTrue(finderLessThan instanceof FinderLessThan);
    }

    @Test
    public void createFinderEqual() {
        String operation = "equal";

        FinderOperation finderEqual = FinderFactory.createFinder(operation);

        assertTrue(finderEqual instanceof FinderEqual);
    }

    @Test
    public void createFinderMoreThan() {
        String operation = "moreThan";

        FinderOperation finderMoreThan= FinderFactory.createFinder(operation);

        assertTrue(finderMoreThan instanceof FinderMoreThan);
    }

    @Test
    public void createFinderOperationIfOperatorIsIncorrect() {
        String operation = "lessTHAN";
        SocksErrorCode resultErrorCode = SocksErrorCode.INCORRECT_PARAMS;

        try {
            FinderFactory.createFinder(operation);
        } catch (SocksException e) {
            assertEquals(resultErrorCode, e.getError());
        }
    }

}
