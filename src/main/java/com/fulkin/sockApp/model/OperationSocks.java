package com.fulkin.sockApp.model;

public enum OperationSocks {
    equal("="),
    moreThan(">"),
    lessThan("<");

    String symbol;

    OperationSocks(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

