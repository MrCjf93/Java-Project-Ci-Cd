import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    private static final ArrayList<PipelineWork> jobRegistry = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        seedInitialWorkflowHistory();
        boolean systemRunning = true;
        System.out.println("=== System Initialized: Deployement Automation Management Enviroment ===");

        while (systemRunning) {
            printMenuInterface();
            int selectedOption = fethSafeIntegerInput();

            switch (selectedOption) {
                case 1: handleJobCreationFlow(); break;
                case 2: handleJobRemovalFlow(); Break;
                case 3: handleDurationSearchQuery(); break;
                case 4: executePolymorphicComplianceAudits();  break;
                case 5; displayAnalyticalAggregates(); break;
            } case 6:
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
    System.out.println("2) Add New Task Unit to Registry");
    System.out.println("3) Remove New Task Unit from Registry");
    System.out.println("4) Display Analytal Aggregates");
    System.out.println("5) Display Analytal Aggregates");
    System.out.println("6) Exit");



}
