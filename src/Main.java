import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

// Core Runtime Engine that coordinates program workflows and processes user choices.
//=================================================================================
public class Main {

    // Shared internal list infrastructure holding the polymorphic PipelineWork items
    private static final ArrayList<PipelineWork> jobRegistry = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Instantiates the separate standalone menu service component helper
    private static final MenuInterface menuService = new MenuInterface();

    public static void main(String[] args) {

        // Seeds initial mock history records for demonstration targets
        seedInitialWorkflowHistory();
        boolean systemRunning = true;
        System.out.println("=== System Initialized: Deployment Automation Management Environment ===");

        // Continuous application processing execution loop
        while (systemRunning) {
            // Uses the instantiated separate utility helper class to present option strings
            menuService.printMenu();
            int selectedOption = fethSafeIntegerInput();

            // Evaluates and resolves target operations based on inputs
            switch (selectedOption) {
                case 1:
                    handleJobCreationFlow();
                    break;
                case 2:
                    handleJobRemovalFlow();
                    break;
                case 3:
                    handleDurationSearchQuery();
                    break;
                case 4:
                    executePolymorphicComplianceAudits();
                    break;
                case 5:
                    displayAnalyticalAggregates();
                    break;
                case 6:
                    // Triggers the toggle language state method in the helper object
                    menuService.toggleLanguage();
                    break;
                case 7:
                    // Safely alters the loop control flag to shut down core engine execution
                    System.out.println("Shutting down core processes, Session closed cleanly");
                    systemRunning = false;
                    break;
                default:
                    System.out.println("Error Alert: Input option does not correspond to active menu entries");
            }
        }
    }

    // Input collection helper with error trapping to catch format mismatches
    private static int fethSafeIntegerInput() {
        while (true) {
            try {
                String consoleRawInput = scanner.nextLine();
                if (consoleRawInput.trim().isEmpty()) continue;
                return Integer.parseInt(consoleRawInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format type. Numerical entries only. Please Re-enter");
            }
        }
    }

    // Seeds initial baseline sample tasks polymorphically into the generic registry collection
    private static void seedInitialWorkflowHistory() {
        jobRegistry.add(new BuildJob("Primary Core Build", 45, "Java JDK 20"));
        jobRegistry.add(new TestJob("Regression Suite Delta", 120, 333));
        jobRegistry.add(new DeployJob("Production Cluster Alpha", 90, "AWS Staging Pool"));
    }

    // Workflow path that handles parsing user definitions and initializing sub-tasks
    private static void handleJobCreationFlow() {
        System.out.println("\nSelect Type: 1-Build | 2-Test | 3-Deploy");
        int choice = fethSafeIntegerInput();
        System.out.println("Assign Unique Task Name: ");
        String name = scanner.nextLine();
        System.out.println("Set Targeted Runtime Expectation (Seconds): ");
        int duration = fethSafeIntegerInput();

        try {
            if (choice == 1) {
                System.out.println("Specify Target Compiler: ");
                String comp = scanner.nextLine();
                BuildJob b = new BuildJob(name, duration, comp);
                b.executeWork();
                jobRegistry.add(b);
            } else if (choice == 2) {
                System.out.println("Specify Test Count: ");
                int count = fethSafeIntegerInput();
                TestJob t = new TestJob(name, duration, count);
                t.executeWork();
                jobRegistry.add(t);
            } else if (choice == 3) {
                System.out.println("Specify Target Environment: ");
                String env = scanner.nextLine();
                DeployJob d = new DeployJob(name, duration, env);
                d.executeWork();
                jobRegistry.add(d);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Operations Aborted: " + e.getMessage());
        }
    }

    // Drops specific records matching identifiers from target structures
    private static void handleJobRemovalFlow() {
        System.out.println("Input Exact Identifier Name to Drop: ");
        String target = scanner.nextLine();
        boolean removed = jobRegistry.removeIf(j -> j.getJobName().equalsIgnoreCase(target));
        if (removed) System.out.println("Target asset match resolved and removed.");
        else System.out.println("No matching pipeline task found. ");
    }

    // Evaluates task data and displays custom strings polymorphically via körtidspolymorfism
    private static void handleDurationSearchQuery() {
        System.out.println("Define Minimum Runtime Target Threshold (seconds): ");
        int limit = fethSafeIntegerInput();
        for (PipelineWork j : jobRegistry) {
            if (j.getDurationSeconds() >= limit) System.out.println(" -> " + j.getDetails());
        }
    }

    // Scans tasks polymorphically using interface evaluation structures
    private static void executePolymorphicComplianceAudits() {
        for (PipelineWork j : jobRegistry) {
            // Evaluates if the current item is an instance conforming to the Reportable blueprint
            if (j instanceof Reportable) ((Reportable) j).generateReport();
        }
    }

    // Evaluates numerical items and prints mathematical process aggregates
    private static void displayAnalyticalAggregates() {
        if (jobRegistry.isEmpty()) return;
        int total = 0;
        for (PipelineWork j : jobRegistry) total += j.getDurationSeconds();
        System.out.println("Total Tracked Steps:" + jobRegistry.size());
        System.out.println("Cumulative Pipeline Core Runtime: " + total + "s");
        System.out.printf("Statistical Average Node Process Duration: %.2f seconds.%n", (double) total / jobRegistry.size());
    }
}
