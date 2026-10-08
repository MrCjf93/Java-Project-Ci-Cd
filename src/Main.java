import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    private static final ArrayList<PipelineWork> jobRegistry = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        seedInitialWorkflowHistory();
        boolean systemRunning = true;
        System.out.println("=== System Initialized: Deployment Automation Management Environment ===");

        while (systemRunning) {
            printMenuInterface();
            int selectedOption = fethSafeIntegerInput();

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
                    System.out.println("Shutting down core processes, Session closed cleanly");
                    systemRunning = false;
                    break;
                default:
                    System.out.println("Error Alert: Input option does not correspond to active menu entries");

            }


        }
    }

    private static void printMenuInterface() {
        System.out.println("\n----------------------------------------------");
        System.out.println("============= Menu Entries =============");
        System.out.println("1) Instantiation: Append New Task Unit to Registry");
        System.out.println("2) Revocation: Remove Specific Task Unit via Id Name");
        System.out.println("3) Query Engine: Filter Task Assets by Runtime Duration");
        System.out.println("4) Compliance Audit: Run Structural Reportable Checks");
        System.out.println("5) Telemetry Analytics: Viwq Cumulative Core Metrics");
        System.out.println("6) Close Application Runtime Engine");
        System.out.println("Action Target Index:");


    }

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

    private static void seedInitialWorkflowHistory() {
        jobRegistry.add(new BuildJob("Primary Core Build", 45, "Java JDK 20"));
        jobRegistry.add(new TestJob("Regression Suite Delta", 120,333));
        jobRegistry.add(new DeployJob("Production Cluster Alpha", 90, "AWS Staging Pool"));

    }

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

    private static void handleJobRemovalFlow() {
        System.out.println("Input Exact Identifier Name to Drop: ");
        String target = scanner.nextLine();
        boolean removed = jobRegistry.removeIf(j -> j.getJobName().equalsIgnoreCase(target));
        if (removed) System.out.println("Target asset match resolved and removed.");
        else System.out.println("No matching pipeline task found. ");
    }

    private static void handleDurationSearchQuery() {
        System.out.println("Define Minimum Runtime Target Threshold (seconds): ");
        int limit = fethSafeIntegerInput();
        for (PipelineWork j : jobRegistry) {
            if (j.getDurationSeconds() >= limit) System.out.println(" -> " + j.getDetails());
        }
    }

    private static void executePolymorphicComplianceAudits() {
        for (PipelineWork j : jobRegistry) {
            if (j instanceof Reportable) ((Reportable) j).generateReport();
        }
    }

    private static void displayAnalyticalAggregates() {
        if (jobRegistry.isEmpty()) return;
        int total = 0;
        for (PipelineWork j : jobRegistry) total += j.getDurationSeconds();
        System.out.println("Total Tracked Steps:" + jobRegistry.size());
        System.out.println("Cumulative Pipeline Core Runtime: " + total + "s");
        System.out.printf("Statistical Average Node Process Duration: %.2f seconds.%n", (double) total /
                jobRegistry.size());
    }
}