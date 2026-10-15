import java.util.ArrayList;
import java.util.Scanner;

public class PipelineManager {

    // Shared internal list infrastructure holding the polymorphic PipelineWork items
    private final ArrayList<PipelineWork> jobRegistry = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private boolean useSimpleEnglish;

    public PipelineManager() {
        this.useSimpleEnglish = false; // Starts at false so that the Dev terminology is the default
        seedInitialWorkflowHistory();  // Seeds initial mock history records on startup
    }

    // Core application loop that drives the entire program workflow
    public void startApplication() {
        boolean systemRunning = true;
        System.out.println("=== System Initialized: Deployment Automation Management Environment ===");

        while (systemRunning) {
            printMenu();
            int selectedOption = fetchSafeIntegerInput();

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
                    toggleLanguage();
                    break;
                case 7:
                    System.out.println("Shutting down core processes, Session closed cleanly");
                    systemRunning = false;
                    break;
                default:
                    System.out.println("Error Alert: Input option does not correspond to active menu entries");
            }
        }
    }

    // Toggle method to switch the language state back and forth
    private void toggleLanguage() {
        this.useSimpleEnglish = !this.useSimpleEnglish;
        System.out.println("Language interface toggled successfully!");
    }

    // Prints out the menu selections onto the console based on the current active flag
    private void printMenu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("============= Menu Entries =============");

        if (useSimpleEnglish) {
            System.out.println("1) Add: Create and add a new task to the list");
            System.out.println("2) Delete: Remove a task by typing its name");
            System.out.println("3) Search: Find tasks based on how long they take to run");
            System.out.println("4) Reports: Run a check and print status reports for the tasks");
            System.out.println("5) Statistics: View total times and summaries of all tasks");
            System.out.println("6) Toggle Language: Switch back to Developer Terminology");
            System.out.println("7) Exit: Close the program");
        } else {
            System.out.println("1) Instantiation: Append New Task Unit to Registry");
            System.out.println("2) Revocation: Remove Specific Task Unit via Id Name");
            System.out.println("3) Query Engine: Filter Task Assets by Runtime Duration");
            System.out.println("4) Compliance Audit: Run Structural Reportable Checks");
            System.out.println("5) Telemetry Analytics: View Cumulative Core Metrics");
            System.out.println("6) Toggle Language: Translate this to normal English");
            System.out.println("7) Close Application Runtime Engine");
        }
        System.out.println("Action Target Index:");
    }

    // Input collection helper with error trapping to catch format mismatches
    private int fetchSafeIntegerInput() {
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
    private void seedInitialWorkflowHistory() {
        jobRegistry.add(new BuildJob("Primary Core Build", 45, "Java JDK 20"));
        jobRegistry.add(new TestJob("Regression Suite Delta", 120, 333));
        jobRegistry.add(new DeployJob("Production Cluster Alpha", 90, "AWS Staging Pool"));
    }

    // Workflow path that handles parsing user definitions and initializing sub-tasks
    private void handleJobCreationFlow() {
        System.out.println("\nSelect Type: 1-Build | 2-Test | 3-Deploy");
        int choice = fetchSafeIntegerInput();
        System.out.println("Assign Unique Task Name: ");
        String name = scanner.nextLine();
        System.out.println("Set Targeted Runtime Expectation (Seconds): ");
        int duration = fetchSafeIntegerInput();

        try {
            if (choice == 1) {
                System.out.println("Specify Target Compiler: ");
                String comp = scanner.nextLine();
                BuildJob b = new BuildJob(name, duration, comp);
                b.executeWork();
                jobRegistry.add(b);
            } else if (choice == 2) {
                System.out.println("Specify Test Count: ");
                int count = fetchSafeIntegerInput();
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
    private void handleJobRemovalFlow() {
        System.out.println("Input Exact Identifier Name to Drop: ");
        String target = scanner.nextLine();
        boolean removed = jobRegistry.removeIf(j -> j.getJobName().equalsIgnoreCase(target));
        if (removed) System.out.println("Target asset match resolved and removed.");
        else System.out.println("No matching pipeline task found. ");
    }

    // Evaluates task data and displays custom strings polymorphically via runtime polymorphism
    private void handleDurationSearchQuery() {
        System.out.println("Define Minimum Runtime Target Threshold (seconds): ");
        int limit = fetchSafeIntegerInput();
        for (PipelineWork j : jobRegistry) {
            if (j.getDurationSeconds() >= limit) System.out.println(" -> " + j.getDetails());
        }
    }

    // Scans tasks polymorphically using interface evaluation structures
    private void executePolymorphicComplianceAudits() {
        for (PipelineWork j : jobRegistry) {
            if (j instanceof Reportable) ((Reportable) j).generateReport();
        }
    }

    // Evaluates numerical items and prints mathematical process aggregates
    private void displayAnalyticalAggregates() {
        if (jobRegistry.isEmpty()) return;
        int total = 0;
        for (PipelineWork j : jobRegistry) total += j.getDurationSeconds();
        System.out.println("Total Tracked Steps:" + jobRegistry.size());
        System.out.println("Cumulative Pipeline Core Runtime: " + total + "s");
        System.out.printf("Statistical Average Node Process Duration: %.2f seconds.%n", (double) total / jobRegistry.size());
    }
}

