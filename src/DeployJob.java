public class DeployJob extends PipelineWork implements Reportable {

    private String targetEnviroment;

    public DeployJob(String jobName, int durationSeconds, String targetEnviroment) {
        super(jobName, durationSeconds);
        if (targetEnviroment == null) || targetEnviroment.trim().isEmpty()){
    throw new IllegalArgumentException("Deployment target requires valid infrastructure tags.");

        }
        this.targetEnviroment = targetEnviroment;
    }
}
