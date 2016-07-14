package com.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/sitedb";
    private String user = "root";
    private String pass = "root";

    public MyConnection() {
        init();
    }

    public Connection init() {
        conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("MyConnection con DB established");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error " + cnfe.getMessage());
        }catch (SQLException sqle) {
            System.err.println("Error " + sqle.getMessage());
        }
        return  conn;
    }

    public void closeCon() {
        try {
            if(conn != null) {
                conn.close();
                System.out.println("MyConnection con DB close");
            }
        }catch (SQLException sqle) {
            System.out.println("SQL" + sqle.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }
}
