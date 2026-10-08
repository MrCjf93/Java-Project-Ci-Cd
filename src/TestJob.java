
// Subclass that handles the test part before deployment
// extends pipelineWork but also implements reportable interface to be able to generate a report.
public class TestJob extends PipelineWork implements Reportable {

    // Stores the number of tests this job will run
    private int testCount;

    // Constructor to initialize job name and duration and test count
    public TestJob(String jobName, int durationSeconds, int testCount) {

       //calls the superclass pipelineWork constructor
        super(jobName, durationSeconds);

        //Validation rule / error handling: Doesnt allow testCount to be 0 or below
        if (testCount < 0) {
            throw new IllegalArgumentException("Automated check count cannot be below zero");

        } this.testCount = testCount;

    }
    // Overrides executeWork method from pipelineWork
    @Override
    public void executeWork() {
        System.out.println("Running Analysis: Checking codebase metrics against " + testCount + " discrete unit verifications...");
        // Changes the job status to Success
        setStatus("Success");


    }
    //Overrides getDetails method to return a formatted info string
    @Override
    public String getDetails(){
        return "[TESTING TASK] Identifier: " + getJobName() + " || Verified Scenarios: " + testCount +
                "units | Target duration: " + getDurationSeconds() + "s || Status: " + getStatus();
    }
    // Overrides generateReport method required by Reportable interface
    @Override
    public void generateReport() {
        System.out.println(" >>> Compliance Feedback [Test asset: " + getJobName() + "] -> Passed " +
                testCount + " Automated build checkpoints smoothly! ");
    }

}
