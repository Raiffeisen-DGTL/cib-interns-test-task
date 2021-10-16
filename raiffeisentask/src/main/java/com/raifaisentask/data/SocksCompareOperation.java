package com.raifaisentask.data;

public enum SocksCompareOperation {
    MORE_THAN("moreThan",">"),
    LESS_THAN("lessThan","<"),
    EQUAL("equal","=");

    private String value;
    private String opSign;
    private SocksCompareOperation(String value,String opSign){
        this.value = value;
        this.opSign = opSign;
    }

    public static String signFromString(String op) throws BadRequestException {
        for (SocksCompareOperation operationEnum : SocksCompareOperation.values()) {
            if (operationEnum.value.equals(op)) return operationEnum.opSign;
        }
        throw new BadRequestException("Данная операция сравнения не поддерживается: "+op);
    }
}
