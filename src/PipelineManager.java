import java.util.ArrayList;
import java.util.Scanner;

public class PipelineManager {

    private final ArrayList<PipelineWork> jobRegistry = new ArrayList<>();
    private final ArrayList<String> executionHistoryLog = new ArrayList<>(); //  History logger
    private final Scanner scanner = new Scanner(System.in);
    private boolean useSimpleEnglish;

    public PipelineManager() {
        this.useSimpleEnglish = false;
        seedInitialWorkflowHistory();
    }

    public void startApplication() {
        boolean systemRunning = true;
        System.out.println("=== System Initialized: Deployment Automation Management Environment ===");

        while (systemRunning) {
            try {
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
                    case 8:
                        displayExecutionHistoryLogs();
                        break;
                    default:
                        System.out.println("Error Alert: Input option does not correspond to active menu entries");
                }
            } catch (Exception e) {
                System.out.println("Critical Error Alert: A system fault occurred -> " + e.getMessage());
                System.out.println("Recovering engine state... Returning safely to top menu.");
            }
        }
    }

    private void toggleLanguage() {
        this.useSimpleEnglish = !this.useSimpleEnglish;
        System.out.println("Language interface toggled successfully!");
    }

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
            System.out.println("8) History Logs: View past execution records");
        } else {
            System.out.println("1) Instantiation: Append New Task Unit to Registry");
            System.out.println("2) Revocation: Remove Specific Task Unit via Id Name");
            System.out.println("3) Query Engine: Filter Task Assets by Runtime Duration");
            System.out.println("4) Compliance Audit: Run Structural Reportable Checks");
            System.out.println("5) Telemetry Analytics: View Cumulative Core Metrics");
            System.out.println("6) Toggle Language: Translate this to normal English");
            System.out.println("7) Close Application Runtime Engine");
            System.out.println("8) Persistent Journal: Audit Executed Sequence History Logs");
        }
        System.out.println("Action Target Index:");
    }

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

    private void seedInitialWorkflowHistory() {
        // Seeds baseline executions directly to history logs so it isn't empty on startup
        executionHistoryLog.add("Seeded System Default -> Build [Primary Core Build] - Status: SUCCESS");
        executionHistoryLog.add("Seeded System Default -> Test [Regression Suite Delta] - Status: SUCCESS");
        executionHistoryLog.add("Seeded System Default -> Deploy [Production Cluster Alpha] - Status: SUCCESS");

        jobRegistry.add(new BuildJob("Primary Core Build", 45, "Java JDK 20"));
        jobRegistry.get(0).setStatus("SUCCESS");
        jobRegistry.add(new TestJob("Regression Suite Delta", 120, 333));
        jobRegistry.get(1).setStatus("SUCCESS");
        jobRegistry.add(new DeployJob("Production Cluster Alpha", 90, "AWS Staging Pool"));
        jobRegistry.get(2).setStatus("SUCCESS");
    }

    private void handleJobCreationFlow() {
        System.out.println("\nSelect Type: 1-Build | 2-Test | 3-Deploy");
        int choice = fetchSafeIntegerInput();
        System.out.println("Assign Unique Task Name: ");
        String name = scanner.nextLine();

        boolean nameExists = jobRegistry.stream().anyMatch(j -> j.getJobName().equalsIgnoreCase(name.trim()));
        if (nameExists) {
            System.out.println("Operations Aborted: A pipeline task with this identifier name already exists.");
            return;
        }

        System.out.println("Set Targeted Runtime Expectation (Seconds): ");
        int duration = fetchSafeIntegerInput();

        try {
            PipelineWork job = null;
            if (choice == 1) {
                System.out.println("Specify Target Compiler: ");
                String comp = scanner.nextLine();
                job = new BuildJob(name, duration, comp);
            } else if (choice == 2) {
                System.out.println("Specify Test Count: ");
                int count = fetchSafeIntegerInput();
                job = new TestJob(name, duration, count);
            } else if (choice == 3) {
                System.out.println("Specify Target Environment: ");
                String env = scanner.nextLine();
                job = new DeployJob(name, duration, env);
            }

            if (job != null) {
                job.executeWork(); // Processes task with realistic sleep delays and failure possibilities
                jobRegistry.add(job);

                executionHistoryLog.add("User Spawned Run -> Name: [" + job.getJobName() + "] | Status: " + job.getStatus());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Operations Aborted: " + e.getMessage());
        }
    }

    private void handleJobRemovalFlow() {
        System.out.println("Input Exact Identifier Name to Drop: ");
        String target = scanner.nextLine();
        boolean removed = jobRegistry.removeIf(j -> j.getJobName().equalsIgnoreCase(target));
        if (removed) System.out.println("Target asset match resolved and removed from active list.");
        else System.out.println("No matching pipeline task found. ");
    }

    private void handleDurationSearchQuery() {
        if (jobRegistry.isEmpty()) {
            System.out.println("Query Matrix Empty: No tasks available to filter.");
            return;
        }
        System.out.println("Define Minimum Runtime Target Threshold (seconds): ");
        int limit = fetchSafeIntegerInput();
        for (PipelineWork j : jobRegistry) {
            if (j.getDurationSeconds() >= limit) System.out.println(" -> " + j.getDetails());
        }
    }

    private void executePolymorphicComplianceAudits() {
        if (jobRegistry.isEmpty()) {
            System.out.println("Audit Aborted: No tracking components exist in the active collection.");
            return;
        }
        for (PipelineWork j : jobRegistry) {
            if (j instanceof Reportable) ((Reportable) j).generateReport();
        }
    }

    private void displayAnalyticalAggregates() {
        if (jobRegistry.isEmpty()) return;
        int total = 0;
        int failedCount = 0;
        for (PipelineWork j : jobRegistry) {
            total += j.getDurationSeconds();
            if (j.getStatus().startsWith("FAILED")) {
                failedCount++;
            }
        }
        System.out.println("Total Tracked Steps: " + jobRegistry.size());
        System.out.println("Cumulative Pipeline Core Runtime: " + total + "s");
        System.out.printf("Statistical Average Node Process Duration: %.2f seconds.%n", (double) total / jobRegistry.size());
        System.out.println("Failed Pipeline Tasks currently in list: " + failedCount); // Added failure count metric
    }

    
    private void displayExecutionHistoryLogs() {
        System.out.println("\n============= PERSISTENT HISTORICAL PIPELINE RUN LOGS =============");
        if (executionHistoryLog.isEmpty()) {
            System.out.println("No entries stored in history journal.");
            return;
        }
        for (String logEntry : executionHistoryLog) {
            System.out.println(" [LOG] " + logEntry);
        }
    }
}
