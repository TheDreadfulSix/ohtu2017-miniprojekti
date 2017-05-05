/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for executing queries to the database.
 */
public abstract class BaseDAO {

    public DBType dbt = DBType.NORMAL;
    
    protected Connection conn;
    protected PreparedStatement statement;
    protected ResultSet results;

    /**
     * Initialize variables needed for the query.
     *
     * @param sql query that is passed to the database
     */
    protected void initializeQuery(String sql) {
        conn = null;
        statement = null;
        results = null;
        conn = connectToDatabase();
        statement = prepareQuery(sql);
    }

    /**
     * Method for setting the DAO to use test database.
     */
    public void useTestDatabase(Boolean GuiTests) {
        if (GuiTests) {
            dbt = DBType.GUITEST;
        } else {
            dbt = DBType.TEST;
        }
    }

    /**
     * Establish connection to the database via DBManager.
     *
     * @return Connection to database
     */
    protected Connection connectToDatabase() {
        try {
            return DBManager.getConnection(this.dbt);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Prepare SQL query to the database.
     *
     * @param sql query to prepare
     *
     * @return PreparedStatement to pass to the database
     */
    protected PreparedStatement prepareQuery(String sql) {
        try {
            return conn.prepareStatement(sql);
        } 
        catch (SQLException e) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes the query to the database.
     */
    protected void implementQuery() {
        try {
            statement.execute();
            results = statement.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, "Kyselyssä on virhe. Palauttaa null-arvon.", ex);
        }
    }

    /**
     * Closes the database connection.
     */
    protected void close() {
        try { results.close(); } catch (Exception e) {}
        try { statement.close(); } catch (Exception e) {}
        try { conn.close(); } catch (Exception e) {}
    }
}
