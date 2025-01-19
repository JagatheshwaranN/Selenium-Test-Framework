# Selenium Test Framework

## Overview
This repository contains a Selenium WebDriver Java-based Automation Framework designed to facilitate the testing of web applications. 
The framework is adaptable to meet the requirements of any automation project.

## Features
- Selenium WebDriver for automated browser interaction.
- TestNG for managing test execution, grouping, and reporting.
- Page Object Model (POM) design pattern for maintaining reusable and clean test code.
- Data-Driven Testing using Excel file for passing test data.
- Parallel Execution with TestNG to run tests across multiple browsers and configurations.
- Screenshots capture during test execution for failure analysis.
- Logging using Log4j for enhanced test result reporting.
- Extent Report for customized reproting.
- AES encryption for encryption and decryption of sensitive data.

## Prerequisites
Before running the framework, make sure to have the following installed:
- Java 11 or higher
- Maven (for dependency management)
- IDE (IntelliJ Idea or Eclipse)

## Getting Started

### 1. Clone the Repository
To get started with the framework, first clone the repository:

```bash
git clone https://github.com/your-username/selenium-java-automation-framework.git
```

### 2. Set Up the Project
The project is managed by Maven. To download all necessary dependencies, navigate to the project directory and run:
``` bash
mvn clean install
```
### 3. Run Tests
Alternatively, you can run specific TestNG XML suites for targeted test execution:
``` bash
mvn test -DsuiteXmlFile=testng.xml
```
### 4. Generating Reports
- Extent will generate an HTML report in the ```/resources/report``` directory after test execution.
- TestNG will automatically generate an HTML report in the ```/test-output``` directory after test execution.
- Additionally, screenshots will be captured for any failed test and stored in the ```/resources/screenshots``` folder.

