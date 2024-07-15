## Setup and Execution

### Prerequisites
- Java JDK 8 or higher
- Maven
- Chrome Browser
- ChromeDriver

1. Navigate to the src/test/java/DefinitioQA directory and open the DemoQAUITests.java file.
2. Update the ChromeDriver path in the setup() method if necessary:
	System.setProperty("webdriver.chrome.driver", "<path-to-chromedriver>");
3. Run the UI tests using Maven:
	mvn test -Dtest=DemoQAUITests
4. Run the API tests using Maven:
	mvn test -Dtest=ReqresAPITests