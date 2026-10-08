

// Subclass that handles the Deployment also extends pipeline and implements reportable
public class DeployJob extends PipelineWork implements Reportable {

    // Stores the destination environment name for example ("Production" or "testing")
    private String targetEnviroment;

    // constructor to set up deployment job name , duration and target destination
    public DeployJob(String jobName, int durationSeconds, String targetEnviroment) {

       //calls the superclass pipelineWork constructor
        super(jobName, durationSeconds);

        //Error handling that doesn't allow the target environment string cant be null or whitespace
        if (targetEnviroment == null  || targetEnviroment.trim().isEmpty()){
    throw new IllegalArgumentException("Deployment target requires valid infrastructure tags.");

        }
        this.targetEnviroment = targetEnviroment;
    }

    // Override the executeWork method to handle the deployment logic
    @Override
    public void executeWork(){
        // Message that says: We are uploading the code files to the target server
        System.out.println("Initiating fleet transition: Provisioning application artifacts onto "
                + targetEnviroment + " Hosts... ");
        // changes the status of the job to Success
        setStatus("Success");


    }
    //Overrides getDetails method to return a formatted info string about this deployment
    @Override
    public String getDetails(){
        return "[Deployment Task] Identifier: " + getJobName() +
                " || Topology Profile:  " + targetEnviroment + " || Target duration: "
                + getDurationSeconds() + "s || Status : " + getStatus();

    }
    // override the generateReport method required by Reportable interface
    @Override
    public void generateReport(){
        // Print out a final summary showing which server group is currently active
        System.out.println(" >>> Environment Profile: [Release Assset: "
                + getJobName() + "] -> infrastructure path active on host pool " + targetEnviroment + ".");
    }
}
