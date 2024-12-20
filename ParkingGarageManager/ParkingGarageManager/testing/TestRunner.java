package ParkingGarageTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("Starting Test Suite...\n");

        // Array of test classes
        Class<?>[] testClasses = {
            TicketTest.class,
            GarageTest.class,
            GarageReportsTest.class,
            ManagerLogInTest.class // Add additional test classes here
        };

        int totalTests = 0;
        int totalFailures = 0;
        int totalIgnored = 0;
        long startTime = System.currentTimeMillis();

        // Loop through each test class and execute it
        for (Class<?> testClass : testClasses) {
            System.out.println("========================================");
            System.out.println("Running tests for: " + testClass.getSimpleName());

            long testStartTime = System.currentTimeMillis();

            Result result = JUnitCore.runClasses(testClass);

            long testEndTime = System.currentTimeMillis();

            // Print details for the current test class
            System.out.println("Test class: " + testClass.getSimpleName());
            System.out.println("Tests run: " + result.getRunCount());
            System.out.println("Failures: " + result.getFailureCount());
            System.out.println("Ignored: " + result.getIgnoreCount());
            System.out.println("Execution time: " + (testEndTime - testStartTime) + " ms");

            // Accumulate results
            totalTests += result.getRunCount();
            totalFailures += result.getFailureCount();
            totalIgnored += result.getIgnoreCount();

            // Print details of failed tests
            if (!result.wasSuccessful()) {
                System.out.println("\nFailed tests:");
                for (Failure failure : result.getFailures()) {
                    System.out.println("  Test: " + failure.getTestHeader());
                    System.out.println("  Reason: " + failure.getMessage());
                    System.out.println("  Stack Trace: " + failure.getTrace());
                }
            } else {
                System.out.println("All tests passed for: " + testClass.getSimpleName());
            }

            System.out.println(); // Blank line between test class results
        }

        long endTime = System.currentTimeMillis();

        // Print overall test suite summary
        System.out.println("========================================");
        System.out.println("Test Suite Completed.");
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
        System.out.println("Total tests run: " + totalTests);
        System.out.println("Total failures: " + totalFailures);
        System.out.println("Total ignored: " + totalIgnored);
        System.out.println("========================================");

        // Overall success message
        if (totalFailures == 0) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println("Some tests failed. Review the output above.");
            System.out.println("Detailed failure information provided for debugging.");
        }
    }
}

