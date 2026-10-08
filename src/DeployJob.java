|


public class DeployJob extends PipelineWork implements Reportable {

    private String targetEnviroment;

    public DeployJob(String jobName, int durationSeconds, String targetEnviroment) {
        super(jobName, durationSeconds);
        if (targetEnviroment == null) || targetEnviroment.trim().isEmpty()){
    throw new IllegalArgumentException("Deployment target requires valid infrastructure tags.");

        }
        this.targetEnviroment = targetEnviroment;
    }
    @Override
    public void executeWork(){
        System.out.println("Initiating fleet transition: Provisioning application artifacts onto "
                + targetEnviroment + " Hosts... ");
        setStatus("Success");


    }
    @Override
    public String getDetails(){
        return "[Deployment Task] Identifier: " + getJobName() +
                " || Topology Profile:  " + targetEnviroment; + " || Target duration: "
                + getDurationSeconds() + "s || Status : " + getStatus();

    }

    @Override
    public void generateReport(){
        System.out.println(" >>> Environment Profile: [Release Assset: "
                + getJobName() + "] -> infrastructure path active on host pool " + targetEnviroment + ".");
    }
}
