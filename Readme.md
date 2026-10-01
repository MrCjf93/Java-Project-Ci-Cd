CI/CD Pipeline Simulator

Author: Christoffer Frez

Project Idea

Console-based simulator of a CI/CD pipeline. It manages different types of pipeline jobs,   building, testing, and deploying software.

Superclass

Name: PipelineWork

Common fields:

String jobName – the name of the job.

int durationSeconds – how long the job takes to run.

String status – the current status of the job.

Common methods:

getJobName() – gets the name of the job.

getDurationSeconds() – gets the duration of the job.

getStatus() – gets the current status.

setStatus() – changes the status of the job.

executeWork() – executes the job. abstract method because each type of job works differently.

getDetails() – returns info about the job. Also an abstract method.

Subclasses
BuildJob

BuildJob: Simulation of compiling the source code.

Also has an added field called compilerVersion, that stores the version of the compiler being used.

Overridden methods:

executeWork() – simulates compiling the source code.

getDetails() – shows info about the build.

TestJob

TestJob simulation of running automated tests on the code.

added field called testCount, stores the number of tests being run. That also implements the Reportable interface so that it can create a report.

Overridden methods:

executeWork() – simulates running the tests.

getDetails() – shows information about the tests.

generateReport() – creates a report about the testing.

DeployJob

DeployJob Simulation of deploying the finished software to a specific environment.

Added field targetEnvironment, which stores where the software is being deployed, for example Production.

Overridden methods:

executeWork() – simulates the deployment.

getDetails() – shows information about the deployment.

generateReport() – creates a report about the deployment.

Interface

Name: Reportable

Method(s):

void generateReport()

Implemented by:

TestJob

DeployJob

The interface is used so TestJob and DeployJob can create their own reports using the same method.

Menu

The program has a menu with different options that work with all of the pipeline jobs.

Add a Pipeline Work Unit
user can create a BuildJob, TestJob, or DeployJob and add it to the collection.

Remove a Work Unit
Enters the name of a job, and the program searches for it and removes it from the collection.

Filter Works by Duration
enter a minimum duration, and the program displays all jobs that have the same or longer duration.

Run Compliance
Runs through the collection and calls generateReport() for the jobs that implement the Reportable interface.

View Fleet Analytics
calculates and displays the total runtime and the average duration of all jobs in the collection.

Error Scenarios
1. Invalid / Wrong info when creating a job

If the user tries to create a job with an empty name or a duration of 0 or less, the program should throw an IllegalArgumentException.

2. A job fails during execution

If the user tries to execute or evaluate a job that has failed or contains corrupted info the program should throw the custom checked exception JobFailedException.

Motivation

Nothing for now. Will be added during this or next week.