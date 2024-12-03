package testing;

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
    public void testGetCarTracker() {
        assertEquals("Car tracker should be 0 initially", 0, report.getCarTracker());
    }

    @Test
    public void testUpdatePaymentSummary() {
        report.updatePaymentSummary(100.0f);
        assertEquals("Total earnings: 100.0", report.getSummary());
        assertEquals("Car tracker should increment to 1", 1, report.getCarTracker());
    }

    @Test
    public void testToString() {
        String output = report.toString();
        assertTrue(output.contains("Details of the report:"));
        assertTrue(output.contains("Date - " + report.getDate()));
        assertTrue(output.contains("ID - " + report.getID()));
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
