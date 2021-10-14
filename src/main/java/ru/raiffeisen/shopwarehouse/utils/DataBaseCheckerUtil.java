package ru.raiffeisen.shopwarehouse.utils;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataBaseCheckerUtil {
    static DataSource dataSources;

    public static boolean isDataBaseConnected() throws SQLException {
        try {
            String schema = dataSources.getConnection().getCatalog();
            if(!schema.equals(null)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void connectionDataBase(DataSource dataSource) {
        dataSources = dataSource;
    }
}
