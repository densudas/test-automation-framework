# Test Automation Framework

This is a test automation framework designed to streamline and standardize the testing processes for your project.

## Development Plan

Please refer to the existing [Project Development Plan](./ProjectDevelopmentPlan.md) document for details about the
roadmap and planned enhancements for this framework.

## Architecture

The framework is built using Java, with test cases authored in the `src/test/java` directory. Tests leverage
functionality provided by utility classes in `src/main/java/io/github/densudas/utils`. The structure of the project
follows standard Java project conventions.

## Setup

1. Clone the project from the repository.
2. Import the project into your favourite IDE.
3. Install the dependencies defined in `build.gradle.kts`.
4. Configure test parameters via `src/main/resources/config.properties` to match your test environment.

## Code Guidelines

Tests are written in Java using TestNG. Utils classes should be used wherever possible to ensure consistency. For key
implementation details refer to comments in the source code where needed.

## Framework Components

- **SimpleTest.java** is an example test case demonstrating how to write and structure your tests.
- **Configurations.java** contains implementation for handling various framework configurations needed for test
  execution.
- **TestListener.java** defines what to do before and after test execution, like setup and teardown operations.
- **DriverFactory.java** is a utility for managing WebDriver instances.
- **LocatorMatcher.java** is a utility for matching page elements based on different locator strategies.

## Testing

To run the test framework, use the command `./gradlew test` in your terminal. Ensure that your test environment is
configured properly via `config.properties`.

## Version Control

Our version control system is Git, hosted on GitLab. For best practices related to Git, refer to our `.gitlab-ci.yml`
and make sure to follow the commit rules defined there.

## Contributing

We welcome contributions! Please ensure that any changes are tested fully before opening a merge request.

## Contact

If you need additional information about this project, please reach out to the project manager or use the contact
details provided in the GitLab project overview.
