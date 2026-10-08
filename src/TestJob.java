

public class TestJob extends PipelineWork implements Reportable {

    // Stores the number of tests this job will run
    private int TestCount;

    // Constructor to initialize job name and duration and test count
    public TestJob(Sting jobName, int durationSeconds int testCount) {

       //Sends data to pipelineWork constructor
        super(jobName, durationSeconds, int testCount);

        //Validation rule / error handling: Doesnt allow testCount to be 0 or below
        if (testCount < 0) {
            throw new IllegalArgumentException("Automated check count cannot be below zero");

        } this.TestCount = testCount;

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
