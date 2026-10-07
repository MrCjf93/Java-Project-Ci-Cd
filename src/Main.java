import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    import java.util.ArrayList;
    import java.util.Scanner;

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
                System.out.println("Shutting down, Session closed");
                systemRunning = false;
                break;
            default:
                System.out.println("Error, Option does not exist within the menu");

        }



    }
}
