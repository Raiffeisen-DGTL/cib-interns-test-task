package com.app.raiffeisen.database;


import com.app.raiffeisen.socks.SocksData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;

import static com.app.raiffeisen.database.Values.*;

public class DataBaseController {

    private static DataBaseController INSTANCE = null;

    private Connection connection;


    public DataBaseController() {
        try {
            connection = DriverManager.getConnection(urlConnect, dataBaseLogin, dataBasePassword);
        } catch (SQLException ignored) { }

    }


    public ResponseEntity<String> changeSocksCount(SocksData socksData) {
        if (socksData.getQuantity() <= 0 || socksData.getCottonPart() < 0 || socksData.getCottonPart() > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            String task;

            ResultSet tableOfElements = getSocksTableByParams(socksData.getColor(), "=", socksData.getCottonPart());

            if (tableOfElements == null || !tableOfElements.next()) {
                task = "insert into " + dataBaseName +
                        "(color, cottonpart, quantity) VALUES ('" +
                        socksData.getColor() + "', " + socksData.getCottonPart() + ", " + socksData.getQuantity() + ");";

            } else {
                task =
                        "UPDATE " + dataBaseName +
                        " SET quantity = quantity + " + socksData.getQuantity() +
                        " where color = '" + socksData.getColor() +
                        "' and cottonpart = " + socksData.getCottonPart() + ";";
            }

            sendStatement(task, false);

            if (socksData.getQuantity() < 0) {
                task = "delete from " + dataBaseName + " where quantity <= 0";
                sendStatement(task, false);
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (SQLException throwables) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> countSocks(String color, String operation, String cottonPart) {
        long sum = 0;

        switch (operation) {
            case "moreThan":
                operation = ">";
                break;

            case "lessThan":
                operation = "<";
                break;

            case "equal":
                operation = "=";
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        int intCottonPart;
        try {
            intCottonPart = Integer.parseInt(cottonPart);
            if (intCottonPart < 0 || intCottonPart > 100) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        try {
            ResultSet result = getSocksTableByParams(color, operation, intCottonPart);
            if (result != null) {
                while (result.next()) {
                    long quantity = result.getLong(3);
                    sum += quantity;

                }
            }
        } catch (SQLException throwables) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(sum + "", HttpStatus.OK);
    }

    private ResultSet getSocksTableByParams(String color, String operation, int cottonPart) throws SQLException {
        String task =
                        "select * from " + dataBaseName +
                        " where color = '" + color +
                        "' and cottonpart " + operation + " " + cottonPart;


        return (ResultSet) sendStatement(task, true);
    }


    private Object sendStatement(String task, boolean isSelecting) throws SQLException {
        Statement statement = connection.createStatement();
        return (isSelecting ? statement.executeQuery(task) : statement.executeUpdate(task));

    }



    public static DataBaseController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseController();
        }
        return INSTANCE;
    }

}
