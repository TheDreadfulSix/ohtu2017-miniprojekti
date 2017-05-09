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
        ds.setURL("jdbc:h2:~/referencedatabase/references;" +
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
    public static Connection getConnection(DBType dbt) throws SQLException {
        try {
            if (null == dbt) {
            } else switch (dbt) {
                case NORMAL:
                    if (manager == null) {
                        manager = new DBManager();
                    }
                    break;
                case GUITEST:
                    manager = new DBManager();
                    initializeTestDatabase(true);
                    break;
                case TEST:
                    manager = new DBManager();
                    initializeTestDatabase(false);
                    break;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Problem creating H2 database connection", ex);
        }
        return ds.getConnection();
    }

    private static void initializeTestDatabase(Boolean forGuiTests) {
        if (forGuiTests) {
            ds.setURL("jdbc:h2:~/referencedatabase/data/guiTest;" +
                "INIT=RUNSCRIPT FROM 'classpath:create.sql'");
        } else {
            ds.setURL("jdbc:h2:~/referencedatabase/data/test;" +
                "INIT=RUNSCRIPT FROM 'classpath:create.sql'\\;RUNSCRIPT FROM 'classpath:initializeTest.sql'");
        }
        ds.setUser("sa");
        ds.setPassword("");
    }
}
