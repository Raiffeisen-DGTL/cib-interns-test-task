package ru.art.sockstore.dataBase;

public enum Operation {
    moreThan(">="),
    lessThan("<="),
    equal("=");

    private final String sign;

    Operation(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
