
public class TestJob extends PipelineWork implements Reportable {
    private int testCount;

    public TestJob(String jobName, int durationSeconds, int testCount) {
        super(jobName, durationSeconds);
        if (testCount < 0) {
            throw new IllegalArgumentException("Automated check count cannot be below zero");
        }
        this.testCount = testCount;
    }

    @Override
    public void executeWork() {
        System.out.println("Running Analysis: Checking codebase metrics against " + testCount + " discrete unit verifications...");

        try { Thread.sleep(getDurationSeconds() * 10L); } catch (InterruptedException ignored) {}

        // Tests have a 20% chance to fail if there are unit tests present
        if (testCount > 0 && Math.random() < 0.20) {
            setStatus("FAILED: Assertions Broken");
            System.out.println(" -> Test Failure: Unit verification assertions failed!");
        } else {
            setStatus("SUCCESS");
            System.out.println(" -> All tests passed successfully.");
        }
    }

    @Override
    public String getDetails() {
        return "[TESTING TASK] Identifier: " + getJobName() + " || Verified Scenarios: " + testCount + " units | Target duration: " + getDurationSeconds() + "s || Status: " + getStatus();
    }

    @Override
    public void generateReport() {
        String outcome = getStatus().startsWith("FAILED") ? "CRITICAL OUTCOME: Failed checkpoints!" : "Passed " + testCount + " Automated build checkpoints smoothly!";
        System.out.println(" >>> Compliance Feedback [Test asset: " + getJobName() + "] -> " + outcome);
    }
}
