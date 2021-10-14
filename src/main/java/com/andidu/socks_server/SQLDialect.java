package com.andidu.socks_server;

import org.hibernate.dialect.Dialect;

import java.sql.Types;

public class SQLDialect extends Dialect {

    public SQLDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.CHAR, "char");
    }

    public String getAddColumnString() {
        return "add column";
    }
}