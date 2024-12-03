package ParkingGarageTests;

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
        assertTrue("Should be able to park a vehicle", garage.parkVehicle());
    }

    @Test
    public void testRemoveVehicle() {
        garage.parkVehicle(); // Park a vehicle first
        assertTrue("Should be able to remove a parked vehicle", garage.removeVehicle());
    }

    @Test
    public void testCheckSpace() {
        assertTrue("Space should be available initially", garage.checkSpace());
    }

    @Test
    public void testGetCapacity() {
        assertEquals("Capacity should match the initialized value", 5, garage.getCapacity());
    }

    @Test
    public void testGetSpacesTaken() {
        garage.parkVehicle(); // Park one vehicle
        assertEquals("Spaces taken should reflect the number of parked vehicles", 1, garage.getSpacesTaken());
    }

    @Test
    public void testParkVehicleWhenFull() {
        // Fill up the garage
        for (int i = 0; i < 5; i++) {
            assertTrue("Should be able to park until full", garage.parkVehicle());
        }
        // Attempt to park when full
        assertFalse("Should not be able to park when the garage is full", garage.parkVehicle());
        assertEquals("Spaces taken should equal the garage's capacity", 5, garage.getSpacesTaken());
    }

    @Test
    public void testRemoveVehicleWhenEmpty() {
        assertFalse("Should not be able to remove a vehicle from an empty garage", garage.removeVehicle());
        assertEquals("Spaces taken should remain 0 when garage is empty", 0, garage.getSpacesTaken());
    }

    @Test
    public void testCheckSpaceWhenFull() {
        for (int i = 0; i < 5; i++) {
            garage.parkVehicle();
        }
        assertFalse("Space should not be available when the garage is full", garage.checkSpace());
    }

    @Test
    public void testCheckSpaceWhenEmpty() {
        assertTrue("Space should be available when the garage is empty", garage.checkSpace());
    }
}
