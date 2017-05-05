package miniprojekti.domain;

import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.fail;

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

    @Test
    public void getTestConnectionConnectsToTestDatabaseWithoutExceptions() {
        try {
            DBManager.getTestConnection(false);
            DBManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void getTestConnectionConnectsToGuiTestDatabaseWithoutExceptions() {
        try {
            DBManager.getTestConnection(true);
            DBManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
