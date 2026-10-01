
// Superclass, Main blueprint for all the subclass jobs.
// Since its abstract it has to be inherited.
//======================================================
public abstract class PipelineWork {

   // Shared / Common fields.
    private String jobName;
    private int durationSeconds;
    private String status;

    // Constructor to set up job and defensive validation rules

    public PipelineWork(String jobName, int durationSeconds){

        // First validation rule regarding the name having to be valid
        if(jobName == null || jobName.trim().isEmpty()){
            throw new IllegalArgumentException("Job name cant be null or empty");
        }
        // Second validation rule regarding duration so that the number has to be above 0
        if(durationSeconds < 0){
            throw new IllegalArgumentException("Duration seconds cant be negative");

        }
        this.jobName = jobName;
        this.durationSeconds = durationSeconds;
        this.status = "PENDING"; // Start of every job by default
    }

    //Abstract methods that have to be implemented in the subclasses
    //Reason is that every job does a different thing and they need a way to print their own specs and info regarding details and stats

    public abstract void executeWork();
    public abstract String getDetails();

    // Getters and setters that are shared in the subclasses

    public String getJobName() {
        return jobName;
    }
    public int getDurationSeconds() {
        return durationSeconds;
    }
    public String getStatus() {
        return status;
    }

    // Setter for status due to it changing from pending -> pending -> running -> completed
    public void setStatus(String status) {
        this.status = status;
    }

}
