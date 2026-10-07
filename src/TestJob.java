

public class TestJob extends PipelineWork implements Reportable {

    private int TestCount;

    public TestJob(Sting jobName, int durationSeconds int testCount) {

        super(jobName, durationSeconds, int testCount);
        if (testCount < 0) {
            throw new IllegalArgumentException("Automated check count cannot be below zero");
        }
    }

}
