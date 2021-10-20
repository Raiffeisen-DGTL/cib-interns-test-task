package ru.art.sockstore.dataBase;

import ru.art.sockstore.model.Sock;

import java.sql.*;
import java.util.ArrayList;

public class SockConnection {
    public final int GET_SUPER_QUERY_ERR = -100;
    public final String SUCCESS = "Success";
    private Connection conn;

    public SockConnection() {
        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/socks",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sock> getAll() {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select * from socks");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Sock> socks = new ArrayList<>();
            while (resultSet.next()) {
                socks.add(new Sock(
                        resultSet.getInt("id"),
                        resultSet.getString("color"),
                        resultSet.getInt("cotton_part"),
                        resultSet.getInt("quantity")
                ));

            }
            statement.close();
            return socks;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Sock getById(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select * from socks\n" +
                    "where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Sock sock = new Sock(
                    resultSet.getInt("id"),
                    resultSet.getString("color"),
                    resultSet.getInt("cotton_part"),
                    resultSet.getInt("quantity"));
            statement.close();

            return sock;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getSuperQuery(String color, Operation operation, Integer cottonPart){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select sum(quantity) as sum from socks\n" +
                            "where (cotton_part "+ operation.getSign() + " ?) and (color = ?)\n");
            statement.setInt(1, cottonPart);
            statement.setString(2, color);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Integer result = resultSet.getInt("sum");
            statement.close();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return GET_SUPER_QUERY_ERR;
        }
    }

    public boolean newSock(Sock newSock) {

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select id, quantity from socks \n" +
                            "where (color = ?) and (cotton_part = ?)\n");
            statement.setString(1, newSock.getColor());
            statement.setInt(2, newSock.getCottonPart());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                statement = conn.prepareStatement(
                        "update socks set quantity = ? where id = ?");
                statement.setInt(1,
                        resultSet.getInt("quantity") + newSock.getQuantity());
                statement.setInt(2, resultSet.getInt("Id"));

            } else {
                statement = conn.prepareStatement(
                        "insert into socks (color, cotton_part, quantity) \n" +
                                "values (?, ?, ?)");
                statement.setString(1, newSock.getColor());
                statement.setInt(2, newSock.getCottonPart());
                statement.setInt(3, newSock.getQuantity());
            }
            statement.executeUpdate();
            statement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String deleteSock(Sock deletedSock) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select sum(quantity) as sumQty from socks \n" +
                            "where (color = ?) and (cotton_part = ?)\n" +
                            "group by color, cotton_part");
            statement.setString(1, deletedSock.getColor());
            statement.setInt(2, deletedSock.getCottonPart());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next() ||
                    resultSet.getInt("sumQty") < deletedSock.getQuantity()) {
                statement.close();
                return "Not enough socks";
            } else {
                statement = conn.prepareStatement(
                        "select id, quantity from socks \n" +
                                "where (color = ?) and (cotton_part = ?)\n");
                statement.setString(1, deletedSock.getColor());
                statement.setInt(2, deletedSock.getCottonPart());
                resultSet = statement.executeQuery();

                while (resultSet.next() && deletedSock.getQuantity() > 0) {
                    statement = conn.prepareStatement(
                            "update socks set quantity = ? " +
                                    "where id = ?");
                    int qty;
                    if ((qty = (deletedSock.getQuantity() - resultSet.getInt("quantity"))) >= 0) {
                        statement.setInt(1, 0);
                        deletedSock.setQuantity(qty);
                    } else {
                        statement.setInt(1, -qty);
                        deletedSock.setQuantity(deletedSock.getQuantity() + qty);
                    }
                    statement.setInt(2, resultSet.getInt("id"));
                    statement.executeUpdate();
                }
                statement = conn.prepareStatement(
                        "delete from socks " +
                                "where quantity = 0");
                statement.executeUpdate();

                statement.close();
                return SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Internal error";
        }

    }


}
