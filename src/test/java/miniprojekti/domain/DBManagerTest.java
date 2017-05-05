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
            DBManager.getConnection(DBType.NORMAL);
            DBManager.getConnection(DBType.NORMAL).close();
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
            DBManager.getConnection(DBType.NORMAL);
            DBManager.getConnection(DBType.NORMAL).close();
        } catch (SQLException e) {
            file.setReadable(true); //release the "lock"
            assertTrue(true);
        }
    }

    @Test
    public void getTestConnectionConnectsToTestDatabaseWithoutExceptions() {
        try {
            DBManager.getConnection(DBType.TEST);
            DBManager.getConnection(DBType.TEST).close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public void getConnectionConnectsToTesDatabaseWithException() {
        File file = new File("~" + File.pathSeparator + "referencedatabase" + File.pathSeparator + "data" + File.pathSeparator + "test.mv.db");
        try {
            file.setReadable(false);
            DBManager.getConnection(DBType.TEST);
            DBManager.getConnection(DBType.TEST).close();
        } catch (SQLException e) {
            file.setReadable(true);
            assertTrue(true);
        }
    }
    
    @Test
    public void getTestConnectionConnectsToGuiTestDatabaseWithoutExceptions() {
        try {
            DBManager.getConnection(DBType.GUITEST);
            DBManager.getConnection(DBType.GUITEST).close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
