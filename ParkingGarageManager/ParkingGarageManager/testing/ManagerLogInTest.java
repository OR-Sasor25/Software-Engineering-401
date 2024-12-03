package testing;

import ParkingGarageManager.ManagerLogIn;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManagerLogInTest {

    private ManagerLogIn manager;

    @Before
    public void setUp() {
        manager = new ManagerLogIn("admin", "password123", 1); // Initialize a ManagerLogIn object
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals("admin", manager.getId());
        assertEquals("password123", manager.getPassword());
        assertEquals("inactive", manager.getStatus());
        assertEquals(1, manager.getGarageID());
    }

    @Test
    public void testSetStatus() {
        manager.setStatus("active");
        assertEquals("active", manager.getStatus());
    }

    @Test
    public void testValidateLogin_Valid() {
        manager.validateLogin(true);
        assertEquals("active", manager.getStatus());
    }

    @Test
    public void testValidateLogin_Invalid() {
        manager.validateLogin(false);
        assertEquals("inactive", manager.getStatus());
    }

    @Test
    public void testSelectGarage_Active() {
        manager.validateLogin(true); // Log in the manager
        manager.selectGarage(2); // This will print a message; manually observe console output
    }

    @Test
    public void testSelectGarage_Inactive() {
        manager.validateLogin(false); // Ensure manager is not logged in
        manager.selectGarage(3); // This will print "Log in first!" in the console
    }
}
