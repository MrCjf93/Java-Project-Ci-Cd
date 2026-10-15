
public class BuildJob extends PipelineWork {
    private String compilerVersion;

    public BuildJob(String jobName, int durationSeconds, String compilerVersion) {
        super(jobName, durationSeconds);
        if (compilerVersion == null || compilerVersion.trim().isEmpty()) {
            throw new IllegalArgumentException("compilerVersion cannot be null or empty");
        }
        this.compilerVersion = compilerVersion;
    }

    @Override
    public void executeWork() {
        System.out.println("Executing build: Compiling standard source components using " + compilerVersion + "...");

        // Simulating delay based on duration (10ms per simulated second)
        try { Thread.sleep(getDurationSeconds() * 10L); } catch (InterruptedException ignored) {}

        // Builds have a 10% chance to fail due to a "syntax error"
        if (Math.random() < 0.10) {
            setStatus("FAILED: Compilation Error");
            System.out.println(" -> Build Failure: Syntax errors caught during compilation!");
        } else {
            setStatus("SUCCESS");
            System.out.println(" -> Build completed successfully.");
        }
    }

    @Override
    public String getDetails() {
        return "[Build-Task] Identifier: " + getJobName() + " Compiler Env: " + compilerVersion + " | Target Duration: " + getDurationSeconds() + "s | Status: " + getStatus();
    }
}
