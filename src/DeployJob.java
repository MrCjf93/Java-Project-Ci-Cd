

public class DeployJob extends PipelineWork implements Reportable {
    private String targetEnvironment; // Fixed original typo here too!

    public DeployJob(String jobName, int durationSeconds, String targetEnvironment) {
        super(jobName, durationSeconds);
        if (targetEnvironment == null  || targetEnvironment.trim().isEmpty()){
            throw new IllegalArgumentException("Deployment target requires valid infrastructure tags.");
        }
        this.targetEnvironment = targetEnvironment;
    }

    @Override
    public void executeWork() {
        System.out.println("Initiating fleet transition: Provisioning application artifacts onto " + targetEnvironment + " Hosts... ");

        try { Thread.sleep(getDurationSeconds() * 10L); } catch (InterruptedException ignored) {}

        // Deployments have a tiny 5% chance to fail due to network drops
        if (Math.random() < 0.05) {
            setStatus("FAILED: Infrastructure Timeout");
            System.out.println(" -> Deployment Failure: Target host connection lost!");
        } else {
            setStatus("SUCCESS");
            System.out.println(" -> Deployment transition finalized safely.");
        }
    }

    @Override
    public String getDetails() {
        return "[Deployment Task] Identifier: " + getJobName() + " || Topology Profile:  " + targetEnvironment + " || Target duration: " + getDurationSeconds() + "s || Status : " + getStatus();
    }

    @Override
    public void generateReport() {
        System.out.println(" >>> Environment Profile: [Release Asset: " + getJobName() + "] -> infrastructure path configuration on " + targetEnvironment + " is [" + getStatus() + "]");
    }
}
