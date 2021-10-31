package dawin.york.rafftest.socks.tos;

public enum OperationType {
    MORE_THAN(">"),
    LESS_THAN("<"),
    EQUAL("=");

    OperationType(String operator){
        this.operator = operator;
    }

    private final String operator;

    public String getOperator() {
        return operator;
    }
}


