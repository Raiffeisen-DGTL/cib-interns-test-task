package socks_accounting.payload;

/**
 * Enum for operation type in "GET /api/socks" method.
 * "NO" value means that operation is incorrect.
 */
public enum Operation {
    MORETHAN, LESSTHAN, EQUAL, NO
}
