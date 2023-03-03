package com.example.demo.utility;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.example.demo.constant.Constant.*;
@Component
public class DBConnection {
    private Logger logger = Logger.getLogger(DBConnection.class.getName());
    private Connection connection;
    private static DBConnection instance;

    private DBConnection() {
        createConnection();
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        } else try {
            if (instance.getConnection().isClosed()) {
                instance = new DBConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    public Connection createConnection() {
        try {
            Driver.class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DBURL, USERNAME, DIGIT);
            logger.log(Level.INFO,"Connection successful");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }


}