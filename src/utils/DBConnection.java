/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bilal Sayed
 */
public class DBConnection {

    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U07cq8";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    //Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    private static final String username = "U07cq8"; //Username
    private static final String password = "53688990408"; //password

    /**
     *
     * @return
     */
    public static Connection startConnection() {
        if (conn != null) {
            return conn;
        }
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection) DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful!");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    /**
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Closed.");
    }
}
