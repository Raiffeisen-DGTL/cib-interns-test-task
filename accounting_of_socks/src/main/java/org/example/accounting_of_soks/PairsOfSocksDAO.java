package org.example.accounting_of_soks;

import java.sql.*;

public class PairsOfSocksDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/warehouse_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "fir2424";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void load_income(String color, int cotton_part, int quantity) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, quantity FROM pairs_of_socks WHERE color =? AND cotton_part =?");
            preparedStatement.setString(1, color);
            preparedStatement.setInt(2, cotton_part);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement =
                        connection.prepareStatement("INSERT INTO pairs_of_socks(color, cotton_part, quantity) VALUES(?, ?, ?)");
                preparedStatement.setString(1, color);
                preparedStatement.setInt(2, cotton_part);
                preparedStatement.setInt(3, quantity);

            }
            else {
                int old_quantity = resultSet.getInt("quantity");
                int old_id = resultSet.getInt("id");
                preparedStatement =
                        connection.prepareStatement("UPDATE pairs_of_socks SET quantity=? WHERE id=?");
                preparedStatement.setInt(1, old_quantity + quantity);
                preparedStatement.setInt(2, old_id);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int load_outcome(String color, int cotton_part, int quantity){

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, quantity FROM pairs_of_socks WHERE color =? AND cotton_part =?");
            preparedStatement.setString(1, color);
            preparedStatement.setInt(2, cotton_part);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int old_quantity = resultSet.getInt("quantity");

                if (old_quantity < quantity)
                    return -1;
                else{
                    int old_id = resultSet.getInt("id");
                    preparedStatement =
                            connection.prepareStatement("UPDATE pairs_of_socks SET quantity=? WHERE id=?");
                    preparedStatement.setInt(1, old_quantity - quantity);
                    preparedStatement.setInt(2, old_id);

                    preparedStatement.executeUpdate();
                }
            }
            else
                return 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    public static int show_information(String color, String operation, int cottonPart){

        PreparedStatement preparedStatement = null;
        try {
            switch (operation){
                case "moreThan":
                    preparedStatement =
                            connection.prepareStatement("SELECT quantity FROM pairs_of_socks WHERE color =? AND cotton_part >?");
                    break;
                case "lessThan":
                     preparedStatement =
                            connection.prepareStatement("SELECT quantity FROM pairs_of_socks WHERE color =? AND cotton_part <?");
                    break;
                case "equal":
                    preparedStatement =
                            connection.prepareStatement("SELECT quantity FROM pairs_of_socks WHERE color =? AND cotton_part =?");
                    break;
            }

            preparedStatement.setString(1, color);
            preparedStatement.setInt(2, cottonPart);

            ResultSet resultSet = preparedStatement.executeQuery();

            int sum = 0;
            while(resultSet.next())
                sum += resultSet.getInt("quantity");

            return sum;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
