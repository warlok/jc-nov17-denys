package com.kademika.day14.store;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * Created by dean on 8/4/15.
 */
public class Connector {

    final ComboPooledDataSource CDPS = new ComboPooledDataSource();

    public Connector() {
        try {
            CDPS.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        CDPS.setJdbcUrl("jdbc:mysql://arrow.wnet.ua:3306/market");
        CDPS.setUser("dean");
        CDPS.setPassword("Dean_2004");
        CDPS.setMaxStatements(180);
    }

    public Connection getConnection() {
        Connection result = null;
        try {
            result = CDPS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void closeConnections() {
        CDPS.close();
    }

//    public static void main(String[] args) {
//
//        Connector connector = new Connector();
//
//        try {
//            Connection con = connector.getConnection();
//            PreparedStatement stmt = con.prepareStatement(
//                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
//            stmt.setString(1,"tiger");
//            ResultSet result = stmt.executeQuery();
//            result.next();
//            System.out.println(result.getDouble(1));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            connector.closeConnections();
//        }

//        try (Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/store","dean","Dean_2004")
//        ) {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("INSERT INTO `store`.`Customers` (`name`) VALUES ('leha')");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}
