package miniprojekti.domain;

import java.io.File;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;


import static junit.framework.TestCase.fail;
import org.junit.After;
import static org.junit.Assert.assertTrue;

/**
 * Tests if DBManager establishes H2 database connections succesfully.
 */
public class DBManagerTest {

    @Test
    public void getConnectionConnectsToDatabaseWithoutExceptions() {
        try {
            DBManager.getConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    //Test for Exception. Needs some sort of powerMockito or something else.
    /*@Test
    public void getConnectionConnectsToDatabaseWithException() {
        try {
            DBManager.getConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            assertTrue(true);
        }
    }*/

    @Test
    public void getTestConnectionConnectsToTestDatabaseWithoutExceptions() {
        try {
            DBManager.getTestConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    //Test for Exception. Needs some sort of powerMockito or something else.
    /*@Test
    public void getConnectionConnectsToTestDatabaseWithException() {
        try {
            DBManager.getTestConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            assertTrue(true);
        }
    }*/
}
