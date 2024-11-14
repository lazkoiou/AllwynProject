###########
# Project #
###########

Allwyn Project - API Automation Testing Assessment: Online Bookstore

Deliverables:
-------------

1. Source Code: on https://github.com/lazkoiou/AllwynProject
2. Test Report:
    - screenshots on path "deliverables/report/screenshots/"
    - report on path "deliverables/report/allureReport/" that
        I created locally after running the tests that can be run by following
        instructions below on allure (after installing allure you can open
        the results with the command 'allure serve /path/to/allure-results')
3. CI/CD files:
    - Jenkinsfile: on project level directory
    - .github/workflows/maven.yml: on project level directory

Some notes regarding my approach:
---------------------------------

Since the data is not saved (e.g. when I POST /api/v1/Books to create a new book)
and the data are changing (e.g. when I GET /api/v1/Books/{id} twice, I get different data),
the approach that I followed was not to find a way to make tests pass to provide a green report.
Instead, I followed what I would normally do i.e. use POST to create a new book, then GET to get
the book that was created and when asserting the fields I make the test fail - as it should.
I marked the tests that fail with the comments "Treating it as a bug" in such cases.

########################
# Project dependencies #
########################

- Apache Maven (exact version installed: 3.5.3)
- Java 17 (exact version installed: 17.0.6)


####################
# What is included #
####################

Helpers and DTOs are located in:    src/main/java
Tests are located in:               src/test/java/fakeRestApis/books
Suites are located in:              src/test/resources/suites/api
Properties are located in:          src/main/resources/


##############
# How to run #
##############

Open a terminal and type:

```
mvn clean test
```

All tests should run. Expected results should be:
Tests run: 11, Failures: 5, Errors: 0, Skipped: 0


###########
# Jenkins #
###########

After installing Jenkins, some jenkins configurations are required to run the project pipeline

Download the following plugins in "Dashboard/Manage Jenkins/Tools":

- Maven Integration, (suggested) Version 3.23
- Pipeline Maven Integration Plugin
- Allure Jenkins Plugin, Version2.31.1

Additionally, make sure that the following tools have been configured
in "Dashboard/Manage Jenkins/Tools":

- Maven installations (suggested):
    Name: maven
    Install automatically: Yes
    Install from Apache version: 3.5.3

- Allure report (suggested):
    Name: allure
    Install automatically: Yes
    Install from Maven Central: 2.29.0

After all dependencies have been installed, we need to:
 1. Create a pipeline (New Item > Pipeline)
 2. Check the GitHub project checkmark and fill it with
    the project's git (https://github.com/lazkoiou/AllwynProject/)
 3. In the Pipeline section, configure it to be
        Definition: Pipeline script from SCM
        SCM: Git
        Repository URL: https://github.com/lazkoiou/AllwynProject/

If all the steps have been completed, the Jenkins pipeline should run the tests
and create and attach an allure report. You can find a screenshot of this in the
deliverables folder "deliverables/report/screenshots"

Lastly, to complete the CI/CD, so that a Jenkins build is performed after each merge
with the master branch. We have 2 options:
1. Create a GitHub hook for scm polling
2. Poll SCM (used this approach for the sake of this project)
    - we can set the regex as 'H * * * *' to check every hour if any merge has occurred


##################
# Allure reports #
##################

1) Download Allure from GitHub: (suggested version: 2.20)
https://github.com/allure-framework/allure2/releases

2) Add the path "...\allure-2.20.1\bin" to the system variables
(Confirm that it has been added by typing in the cmd)
```
allure --version
```

3) Run tests with
```
mvn clean test
```

4) After the tests have finished running, the allure report should have been created.
You can view it by executing
```
allure serve
```