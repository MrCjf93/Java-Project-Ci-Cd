
// This interface will grant the ability to generate a report.
//Any subclass that needs to generate a summary / log will implement this interface.
//Any subclass that implements this interface needs to provide its own report logic.

public interface Reportable {

    // Handles the creation and saving of the report.
    void generateReport();
}
