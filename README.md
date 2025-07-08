# Test Automation Framework

A comprehensive test automation framework that demonstrates the integration and usage of various testing technologies.

## Technologies Used

### Maven
This project uses [Maven](https://maven.apache.org/) as the build automation tool. Maven manages dependencies, builds the project, and runs tests.

Key features:
- Dependency management
- Project build lifecycle
- Test execution

See the [pom.xml](pom.xml) file for the Maven configuration.

### Selenium
[Selenium WebDriver](https://www.selenium.dev/) is used for browser automation and web testing.

Key features demonstrated:
- WebDriver setup with WebDriverManager
- Chrome options configuration
- Navigation and element interaction
- Explicit waits
- JavaScript execution

See [SeleniumFeaturesTest.java](src/test/java/io/github/densudas/selenium/SeleniumFeaturesTest.java) for examples.

### RestAssured
[RestAssured](https://rest-assured.io/) is used for testing RESTful APIs.

Key features demonstrated:
- GET, POST, PUT, DELETE requests
- Request and response specifications
- Response validation
- JSON path extraction
- Request and response logging

See [RestAssuredFeaturesTest.java](src/test/java/io/github/densudas/restassured/RestAssuredFeaturesTest.java) for examples.

### Docker
This project includes a [Dockerfile](Dockerfile) for containerization, which:
- Uses Selenium's standalone Chrome image
- Installs Maven and JDK 21
- Builds and runs tests in a containerized environment

### CI/CD Integration

#### GitLab CI
The project includes a [.gitlab-ci.yml](.gitlab-ci.yml) file for GitLab CI/CD integration, which:
- Builds the project
- Runs tests
- Caches Maven dependencies

#### GitHub Actions
The project includes a [GitHub Actions workflow](.github/workflows/maven.yml) for GitHub CI/CD integration, which:
- Builds the project
- Runs tests
- Caches Maven dependencies

#### Jenkins
The project includes a [Jenkinsfile](Jenkinsfile) for Jenkins CI/CD integration, which:
- Uses a Docker container with Maven and JDK 21
- Builds the project
- Runs tests
- Collects and publishes test reports

## Getting Started

### Prerequisites
- JDK 21
- Maven 3.9+
- Docker (optional, for containerized execution)

### Running Tests
```bash
# Run all tests
mvn test

# Run Selenium tests only
mvn test -Dtest=SeleniumFeaturesTest

# Run RestAssured tests only
mvn test -Dtest=RestAssuredFeaturesTest
```

### Running in Docker
```bash
# Build the Docker image
docker build -t test-automation-framework .

# Run tests in Docker
docker run test-automation-framework
```
