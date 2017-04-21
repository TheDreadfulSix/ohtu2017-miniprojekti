/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcDataSource;
import javax.naming.NamingException;

/**
 * Class for managing database connections.
 */
public class DBManager {
    
    private static JdbcDataSource ds;
    private static DBManager manager;

    public DBManager() throws NamingException {
        ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./data/references;" +
                "INIT=RUNSCRIPT FROM 'classpath:create.sql'");
        ds.setUser("sa");
        ds.setPassword("");
    }

    /**
     * If database connection exists, return it. Otherwise create it.
     * 
     * @return Connection to the database
     * 
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (manager == null) {
            try {
                manager = new DBManager();
            } catch (Exception ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Problem creating H2 database connection", ex);
            }
        }
        return ds.getConnection();
    }

    /**
     * If test database connection exists, return it. Otherwise create it.
     *
     * @return Connection to the database
     *
     * @throws java.sql.SQLException
     */
    public static Connection getTestConnection() throws SQLException {
        if (manager == null) {
            try {
                manager = new DBManager();
                initializeTestDatabase();
            } catch (Exception ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Problem creating H2 database connection", ex);
            }
        }
        return ds.getConnection();
    }

    /**
     * Closes the database connection. This is method is only used for tests.
     *
     * @throws java.sql.SQLException
     */
    public static void closeConnection() throws SQLException {
        try {
            ds.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeTestDatabase() {
        ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:~/data/test;" +
                "INIT=RUNSCRIPT FROM 'classpath:create.sql'");
        ds.setUser("sa");
        ds.setPassword("");
    }
}
