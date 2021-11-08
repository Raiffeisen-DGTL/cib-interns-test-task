package com.raiffeisendgtl.ApiSocks.components;

import org.springframework.stereotype.Component;

@Component
public class FinderFactory {

    public static FinderOperation createFinder(String operationStr) {
        Operation operation = Operation.convertFromString(operationStr);
        if (operation == Operation.lessThan) {
            return new FinderLessThan();
        }
        if (operation == Operation.equal) {
            return new FinderEqual();
        }
        if (operation == Operation.moreThan) {
            return new FinderMoreThan();
        }
        return null;
    }

}
