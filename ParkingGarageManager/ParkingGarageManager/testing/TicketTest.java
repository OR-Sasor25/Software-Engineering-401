package ParkingGarageTests;

import ParkingGarageManager.Ticket;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketTest {

    private Ticket ticket;

    @Before
    public void setUp() {
        // Initialize a Ticket object
        ticket = new Ticket();
    }

    @Test
    public void testGetTicketID() {
        int initialTicketID = ticket.getTicketID();
        Ticket anotherTicket = new Ticket(); // Create another ticket to increment the ID
        assertEquals(initialTicketID + 1, anotherTicket.getTicketID());
    }

    @Test
    public void testGetPrice() {
        assertEquals(0.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testSetPrice() {
        ticket.setPrice(30.0);
        assertEquals(30.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testIsPaidStatus() {
        assertFalse(ticket.isPaidStatus());
    }

    @Test
    public void testSetPaidStatus() {
        ticket.setPaidStatus(true);
        assertTrue(ticket.isPaidStatus());
    }

    @Test
    public void testGetTimeArrived() {
        assertNotNull(ticket.getTimeArrived());
        LocalDateTime.parse(ticket.getTimeArrived(), DateTimeFormatter.ISO_DATE_TIME); // Verify format
    }

    @Test
    public void testSetTimeArrived() {
        String newTime = "2024-12-01T12:00:00";
        ticket.setTimeArrived(newTime);
        assertEquals(newTime, ticket.getTimeArrived());
    }

    @Test
    public void testCalculatePriceWithRate() {
        // Set time arrived to 2 hours ago
        LocalDateTime timeTwoHoursAgo = LocalDateTime.now().minusHours(2);
        ticket.setTimeArrived(timeTwoHoursAgo.format(DateTimeFormatter.ISO_DATE_TIME));

        ticket.calculatePrice(10.0); // $10/hour rate
        assertEquals(20.0, ticket.getPrice(), 0.01); // Should calculate as 2 hours * $10/hour
    }

    @Test
    public void testPrintTicket() {
        // Test the output of printTicket (manually observe the console output)
        ticket.setPaidStatus(true);
        ticket.printTicket();
    }
}

