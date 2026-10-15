# CI/CD Pipeline Simulator

**Author:** Christoffer Frez

---

## Project Idea
A console-based simulator of a CI/CD pipeline. It manages different types of pipeline jobs responsible for building, testing, and deploying software.

---

## Architecture & Component Breakdown

### Superclass
* **Name:** `PipelineWork`
* **Common Fields:**
    * `String jobName` – The name of the job.
    * `int durationSeconds` – How long the job takes to run.
    * `String status` – The current status of the job.
* **Common Methods:**
    * `getJobName()` – Gets the name of the job.
    * `getDurationSeconds()` – Gets the duration of the job.
    * `getStatus()` – Gets the current status.
    * `setStatus()` – Changes the status of the job.
    * `executeWork()` – Abstract method because each type of job works differently.
    * `getDetails()` – Abstract method that returns info about the job.

### Subclasses

#### 1. BuildJob
* **Description:** Simulation of compiling the source code.
* **Added Field:** `String compilerVersion` – Stores the version of the compiler being used.
* **Overridden Methods:**
    * `executeWork()` – Simulates compiling the source code.
    * `getDetails()` – Shows info about the build.

#### 2. TestJob
* **Description:** Simulation of running automated tests on the code.
* **Added Field:** `int testCount` – Stores the number of tests being run.
* **Interface Implemented:** `Reportable` (allows it to create a status report).
* **Overridden Methods:**
    * `executeWork()` – Simulates running the tests.
    * `getDetails()` – Shows information about the tests.
    * `generateReport()` – Creates a report about the testing.

#### 3. DeployJob
* **Description:** Simulation of deploying the finished software to a specific environment.
* **Added Field:** `String targetEnvironment` – Stores where the software is being deployed (e.g., Production).
* **Interface Implemented:** `Reportable` (allows it to create a status report).
* **Overridden Methods:**
    * `executeWork()` – Simulates the deployment.
    * `getDetails()` – Shows information about the deployment.
    * `generateReport()` – Creates a report about the deployment.

---

### Interface
* **Name:** `Reportable`
* **Method(s):** `void generateReport()`
* **Implemented By:** `TestJob`, `DeployJob`
* **Purpose:** This interface is used so that both `TestJob` and `DeployJob` can create their own unique reports using the exact same contract method.

---

## System Features & Menu Entries
The program features a centralized menu with options to manipulate and evaluate all pipeline jobs simultaneously:

* **Add a Pipeline Work Unit:** Allows the user to choose and initialize a `BuildJob`, `TestJob`, or `DeployJob` and add it to the generic tracking collection.
* **Remove a Work Unit:** Takes a job name string from the console, searches the collection, and drops the matching item.
* **Filter Works by Duration:** Filters the list by a minimum duration input and displays all jobs matching or exceeding that target threshold.
* **Run Compliance:** Loops through the collection and triggers `generateReport()` for only the jobs that implement the `Reportable` interface.
* **View Fleet Analytics:** Calculates and displays cumulative process statistics, including total pipeline runtime and the statistical average duration of all nodes.
* **Bonus (Language Interface Toggle):** Includes a toggle translation mechanism that allows users to seamlessly switch the interface text between complex Developer terminology and Simple English.

---

## Error Scenarios & Safety Traps
1. **Invalid Configuration Data:** If a user tries to instantiate a job with a blank name or a negative duration value, the constructors catch it instantly and throw an `IllegalArgumentException`.
2. **Job Failure Fault Traps:** If a job encounters unexpected issues or runs into corrupted parameters during execution, the system flags the failure state and traps runtime loop crashes safely using robust error boundaries.

---

## Motivation
The motivation behind this project was to design a clean, manageable way to simulate real-world DevOps tasks using object-oriented programming concepts in Java. In modern software engineering, automated pipelines are critical for shipping code quickly and safely, but the background processes can be hard to visualize. Building this simulator helped me practicalize core coding principles like polymorphism and abstract classes, while also exploring how a centralized controller interface handles different sub-task logic smoothly. Creating the dual-language menu toggle also highlights how important accessible user interface design is, even for backend console utilities.
