# API Test Automation Project
This is a Java-based API test automation project for the Restful Booker API service. 
[Link to the API DOC](https://restful-booker.herokuapp.com/apidoc/index.html)

# Technologies
    Java 17
    RestAssuered
    Maven
    Junit
    Allure
    Jackson

# Test Execution
    Tests are executed using JUnit. The test classes are located in the src/test/java directory and are annotated with @Test tag.
    @Description tag used to describe general test purpouse
    To run tests execute maven command: 
**_`mvn clean test`_**

# Report
    TAF uses use Allure for test reporting. Here's how reporting works in this project:
    Generation: After running the tests, Allure generates XML files containing test execution results.

    Presentation: You can generate HTML reports from the XML files using the Allure command-line tool. 
    To do this, run the following command:
allure serve target/allure-results
