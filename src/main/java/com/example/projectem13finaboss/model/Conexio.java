package com.example.projectem13finaboss.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexio {
    public static Connection getConnection(){
        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/music");

            Connection conn = dataSource.getConnection();
            return conn;
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
