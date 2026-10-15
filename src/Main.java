// Core Runtime Engine that simply initializes and boots the pipeline management environment.
public class Main {
    public static void main(String[] args) {
        // Instantiate the controller manager and hand over execution control
        PipelineManager manager = new PipelineManager();
        manager.startApplication();
    }
}
