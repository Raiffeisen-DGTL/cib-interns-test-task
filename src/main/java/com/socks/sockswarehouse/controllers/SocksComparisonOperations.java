package com.socks.sockswarehouse.controllers;

import java.util.HashMap;
import java.util.Map;

public class SocksComparisonOperations {
    private Map<String, String> operations;

    public SocksComparisonOperations() {
        this.operations = new HashMap<>();
        operations.put("moreThan", ">");
        operations.put("lessThan", "<");
        operations.put("equal", "=");
    }

    public String getOperator(String description) throws Exception {
        try {
            return operations.get(description);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }
}
