
public class MenuInterface {

    private boolean useSimpleEnglish;

    public MenuInterface() {
        this.useSimpleEnglish = false; // Starts at false so that the Dev terminology is the default
    }
    //Toggle method to switch the language state back and forth
    public void toggleLanguage() {
        // Flips true to false or false to true cleanly every single time it gets triggered
        this.useSimpleEnglish = !this.useSimpleEnglish;
        System.out.println("Language interface toggled successfully!");
    }
    // Prints out the menu selections onto the console based on the current active flag
    public void printMenu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("============= Menu Entries =============");

        // Checks the condition to print out normal non-dev English terms
        if (useSimpleEnglish) {
            System.out.println("1) Add: Create and add a new task to the list");
            System.out.println("2) Delete: Remove a task by typing its name");
            System.out.println("3) Search: Find tasks based on how long they take to run");
            System.out.println("4) Reports: Run a check and print status reports for the tasks");
            System.out.println("5) Statistics: View total times and summaries of all tasks");
            System.out.println("6) Toggle Language: Switch back to Developer Terminology");
            System.out.println("7) Exit: Close the program");
        }
        // Fallback option that displays the core system developer language entries
        else {
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
}


