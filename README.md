# Automated testing: course project

This project is written in order to practise different types of testing technologies and tools to execute these. Project consists of the application described below and the following test suites: integration tests, mock and stub tests, unit tests.

We're testing an application that is able to read city names from a file and return a separate file that offers the following:

- current temperature
- daily averages for 3 days of weather forecast: temperature, humidity, and barometric pressure. NB! current day is not counted for, so the forecast starts from tomorrow
- coordinates in the form lat, lon, eg "59.44,24.75"
- temperature units in Celsius

Also, Selenium UI tests are added, in order to test TalTech Universitys' homepage functionalities.

## Getting Started

For development purposes the recommended IDEA to use: IntelliJ


### Prerequisites

* The latest version of the [Java 11 OpenJDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Import POM dependencies
* [Chrome Browser](https://www.google.com/chrome/) - UI Selenium tests are run with [ChromeDriver](https://chromedriver.chromium.org)
* [Maven](https://maven.apache.org/install.html) 


### Installing

In order to run tests you will need to install [Apache Maven](https://maven.apache.org/install.html) 

For example install Maven with *brew* on Mac OS

```
brew install maven
```


## Running all the tests

To run all the test from command line, make sure you're in *course-project* directory where you can see POM file.

Run tests with maven and clean test target folder after.

```
mvn test clean
```

### Running integration tests

Tests to validate that the OpenWeatherMap API integration is acting as intended.
For example the current weather request returns an HTTP Status Code 200, for the wrong (not recognized) city, the API returns an error with an error message.

```
mvn clean test -Dtest=WeatherApiTest
```

### Running mock tests

Mock and test OpenWeatherMap API dependencies and read from / write to file functionality

```
mvn clean test -Dtest=WeatherApiMockTest
```

## Running stub tests


```
mvn clean test -Dtest=WeatherWiseStubTest
```

## Running UI tests

Automated tests for user TalTech homepage interface testing

```
mvn clean test -Dtest=TalTechHomepageTest
```

## Running Unit tests


```
mvn clean test -Dtest=WeatherWiseUnitTest
```


## Built With

* [Java](https://docs.oracle.com/en/java/) - The development language used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Selenium](https://selenium.dev) - Used for automating web applications for testing purposes
* [JUnit](https://junit.org/junit4/) - Framework to write repeatable tests

## Authors

* **Sigrid Narep** 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* German Mumma - the lead of the course
