package miniprojekti.domain;

import java.io.File;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.fail;
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
    
    @Test
    public void getConnectionConnectsToDatabaseWithException() {
        File file = new File("~" + File.pathSeparator + "referencedatabase" + File.pathSeparator + "references.mv.db");
        try {
            file.setReadable(false); //"locks" file so that connection gives error.
            DBManager.getConnection();
            DBManager.getConnection().close();
        } catch (SQLException e) {
            file.setReadable(true); //release the "lock"
            assertTrue(true);
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
    
    public void getConnectionConnectsToTesDatabaseWithException() {
        File file = new File("~" + File.pathSeparator + "referencedatabase" + File.pathSeparator + "data" + File.pathSeparator + "test.mv.db");
        try {
            file.setReadable(false);
            DBManager.getTestConnection(false);
            DBManager.getConnection().close();
        } catch (SQLException e) {
            file.setReadable(true);
            assertTrue(true);
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
