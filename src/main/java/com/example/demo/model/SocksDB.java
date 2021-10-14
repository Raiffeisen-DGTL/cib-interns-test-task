package com.example.demo.model;

import java.sql.*;

public class SocksDB {
    private static final String url = "jdbc:mysql://localhost/socksDB";
    private static final String jdbc = "com.mysql.cj.jdbc.Driver";
    private static final String username = "mysql";
    private static final String password = "mysql";

    public static Socks selectByColorAndCotton(String color, int cottonPart) {

        Socks ans = null;
        try{
            Class.forName(jdbc).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM socks WHERE color=? and cottonPart=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, color);
                    preparedStatement.setInt(2, cottonPart);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int sock_id = resultSet.getInt(1);
                        String sock_color=resultSet.getString(2);
                        byte sock_cottonPart=resultSet.getByte(3);
                        int quantity=resultSet.getInt(4);

                        ans = new Socks(sock_id, sock_color, sock_cottonPart, quantity);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return ans;
    }
    public static int selectByColorAndCotton(String color, int cottonPart, String operation) {

        int sum_quantity=0;
        try{
            Class.forName(jdbc).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT sum(quantity) FROM socks WHERE color=? and cottonPart";

                if(operation.equals("equal")) sql+="=?";
                else if (operation.equals("moreThan")) sql+=">=?";
                else if (operation.equals("lessThan")) sql+="<=?";
                sql+=" group by color";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, color);
                    preparedStatement.setInt(2, cottonPart);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        sum_quantity = resultSet.getInt(1);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return sum_quantity;
    }


    public static void insert(Socks sock) {
        if(sock.getCottonPart()>=0 && sock.getCottonPart()<=100){
            try{
                Class.forName(jdbc).getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection(url, username, password)){

                    String sql = "INSERT INTO socks (color, cottonPart, quantity) Values ( ?, ?, ?)";
                    try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                        preparedStatement.setString(1, sock.getColor());
                        preparedStatement.setInt(2, sock.getCottonPart());
                        preparedStatement.setInt(3, sock.getQuantity());

                    }
                }
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    }

    public static void update(Socks sock) {

        try{
            Class.forName(jdbc).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE socks SET color = ?, cottonPart = ?, quantity = ? WHERE ID = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, sock.getColor());
                    preparedStatement.setInt(2, sock.getCottonPart());
                    preparedStatement.setInt(3, sock.getQuantity());
                    preparedStatement.setInt(4, sock.getId());

                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
