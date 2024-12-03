package ParkingGarageTests;

import ParkingGarageManager.GarageReports;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class GarageReportsTest {

    private GarageReports report;

    @Before
    public void setUp() {
        // Initialize the GarageReports object
        report = new GarageReports();
    }

    @Test
    public void testGetDate() {
        assertNotNull("Date should not be null", report.getDate());
        assertTrue("Date should match ISO format",
                report.getDate().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"));
    }

    @Test
    public void testGetID() {
        assertNotNull("ID should not be null", report.getID());
        assertTrue("ID should start with GR-", report.getID().startsWith("GR-"));
    }

    @Test
    public void testGetSummary() {
        assertNotNull("Summary should not be null", report.getSummary());
        assertEquals("Summary should start empty", "", report.getSummary());
    }

    @Test
    public void testSetCarTracker() {
        report.setCarTracker();
        assertEquals("Car tracker should increment to 1", 1, report.getCarTracker());
        report.setCarTracker();
        assertEquals("Car tracker should increment to 2", 2, report.getCarTracker());
    }

    @Test
    public void testUpdatePaymentSummary() {
        report.updatePaymentSummary(150.0f);
        assertEquals("Payment summary should update correctly", "Total earnings: 150.0", report.getSummary());
    }

    @Test
    public void testGetCarTracker() {
        assertEquals("Car tracker should be 0 initially", 0, report.getCarTracker());
        report.setCarTracker();
        assertEquals("Car tracker should be 1 after incrementing", 1, report.getCarTracker());
    }

    @Test
    public void testToString() {
        report.updatePaymentSummary(200.0f);
        report.setCarTracker();
        String output = report.toString();
        assertTrue(output.contains("Details of the report:"));
        assertTrue(output.contains("Date - " + report.getDate()));
        assertTrue(output.contains("ID - " + report.getID()));
        assertTrue(output.contains("Payment Summary - Total earnings: 200.0"));
        assertTrue(output.contains("How many cars entered for the day - 1"));
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        // Serialize the object
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        report.writeToStream(out);

        // Deserialize the object
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        GarageReports deserializedReport = GarageReports.readFromStream(in);

        // Verify deserialized object
        assertNotNull("Deserialized object should not be null", deserializedReport);
        assertEquals("Deserialized date should match", report.getDate(), deserializedReport.getDate());
        assertEquals("Deserialized ID should match", report.getID(), deserializedReport.getID());
        assertEquals("Deserialized summary should match", report.getSummary(), deserializedReport.getSummary());
        assertEquals("Deserialized car tracker should match", report.getCarTracker(), deserializedReport.getCarTracker());
    }
}
