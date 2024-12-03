package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ParkingGarageManager.Garage;

public class GarageTest {

    private Garage garage;

    @Before
    public void setUp() {
        garage = new Garage(5); // Initialize a Garage with capacity of 5
    }

    @Test
    public void testParkVehicle() {
        assertTrue(garage.parkVehicle()); // Test parking a vehicle
    }

    @Test
    public void testRemoveVehicle() {
        garage.parkVehicle(); // Park a vehicle first
        assertTrue(garage.removeVehicle()); // Test removing the vehicle
    }

    @Test
    public void testCheckSpace() {
        assertTrue(garage.checkSpace()); // Verify space availability
    }

    @Test
    public void testGetCapacity() {
        assertEquals(5, garage.getCapacity()); // Check the capacity
    }

    @Test
    public void testGetSpacesTaken() {
        garage.parkVehicle(); // Park one vehicle
        assertEquals(1, garage.getSpacesTaken()); // Verify the space taken count
    }
}
