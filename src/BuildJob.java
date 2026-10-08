
// Subclass that handles the build step in the pipeline
public class BuildJob extends PipelineWork{

    //Stores the version of the compiler for the build.
    private String compilerVersion;


    // Constructor that initializes the BuildJob
    public BuildJob(String jobName, int durationSeconds, String compilerVersion) {

        // calls the superclass PipelineWork constructor
        super(jobName, durationSeconds);

        // Validation that prevents the program from creating a BuildJob without a valid compilerVersion
        if (compilerVersion == null || compilerVersion.trim().isEmpty()) {
            throw new IllegalArgumentException("compilerVersion cannot be null or empty");
        }

        this.compilerVersion = compilerVersion;

    }
    // Overrides the abstract method from PipelineWork.
    // Simulates the execution of the compilation stage of the pipeline.
    @Override
    public void executeWork() {
        System.out.println("Executing build: Compiling standard source components using " + compilerVersion + "...");

        // Updates the job status using the setter method inherited from PipelineWork ( The superclass ).
        setStatus("Successfully executed build: Compiling standard source components");

    }
    // Constructs a formatted summary string that is specific to this BuildJob
    // Combines subclass data the Compiler plus the superclass name , duration and status.
    @Override
    public String getDetails(){
        return "[Build-Task] Identifier: " + getJobName() + " Compiler Env: " + compilerVersion + " | Target Duration: " + getDurationSeconds() + "s | Status: " + getStatus();
    }
}
