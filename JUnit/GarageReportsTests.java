package ParkingGarageTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import useME.GarageReports;

public class GarageReportsTest {

    private GarageReports report;

    @Before
    public void setUp() {
        // Initialize the GarageReports object
        report = new GarageReports();
    }

    @Test
    public void testGetDate() {
        assertNotNull(report.getDate());
    }

    @Test
    public void testGetID() {
        assertNotNull(report.getID());
    }
}
