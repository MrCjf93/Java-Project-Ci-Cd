

public class TestJob extends PipelineWork implements Reportable {

    private int TestCount;

    public TestJob(Sting jobName, int durationSeconds int testCount) {

        super(jobName, durationSeconds, int testCount);
        if (testCount < 0) {
            throw new IllegalArgumentException("Automated check count cannot be below zero");

        } this.TestCount = testCount;

    }

    @Override
    public void executeWork() {
        System.out.println("Running Analysis: Checking codebase metrics against " + testCount + " discrete unit verifications...");
        setStatus("Success");


    }
    @Override
    public String getDetails(){
        return "[TESTING TASK] Identifier: " + getJobName() + " || Verified Scenarios: " + testCount +
                "units | Target duration: " + getDurationSeconds() + "s || Status: " + getStatus();
    }

    @Override
    public void generateReport() {
        System.out.println(" >>> Compliance Feedback [Test asset: " + getJobName() + "] -> Passed " +
                testCount + " Automated build checkpoints smoothly! ");
    }

}
