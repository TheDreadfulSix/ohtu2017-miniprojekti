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
    
    protected Connection conn;
    protected PreparedStatement statement;
    protected ResultSet results;
    
    protected void initializeQuery(String sql) {
        conn = null;
        statement = null;
        results = null;
        conn = connect();
        statement = query(sql);
    }
    
    protected Connection connect() {
        try {
            return DBManager.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected PreparedStatement query(String sql) {
        try {
            return conn.prepareStatement(sql);
        } 
        catch (SQLException e) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
    }
    
    protected void implementQuery() {
        try {
            statement.execute();
            results = statement.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, "Kyselyssä on virhe. Palauttaa null-arvon.", ex);
        }
    }
    
    protected void close() {
        try { results.close(); } catch (Exception e) {}
        try { statement.close(); } catch (Exception e) {}
        try { conn.close(); } catch (Exception e) {}
    }
}
