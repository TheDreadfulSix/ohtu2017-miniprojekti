package miniprojekti.domain;

import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
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
            DBManager.getTestConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}
