# Inmeta-Technical-Exercise
Combined API and web-app created as part of review process at [inmeta](https://inmeta.no/).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* PostgreSQL
* Gradle
* Node.js
* Java

### Installing
`git clone "https://github.com/torland-klev/inmeta-technical-exercise.git"`

For the web app:
  * `cd inmeta-technical-exercise\web`
  * `npm install`
  * `npm start`
  
For the API:

Create and connect to the PostgreSQL database. If you change any of these values, ensure that those changes are reflected in application.properties.
  * Start SQL shell (psql)
  * Press enter 4 times
  * Enter password for root user if set
  * `create database inmeta_technical_exercise;`
  * `create user inmeta with encrypted password 'inmeta';`
  * `grant all privileges on database inmeta_technical_exercise to inmeta;`
  * `\c inmeta_technical_exercise inmeta`
  * Enter password 'inmeta'
  
Build and run the API.
  * `cd inmeta-technical-exercise\api`
  * `gradle wrapper`
  * `gradlew bootRun`


## Deployment

API
* `gradlew build`
* `java -jar build/libs/inmeta-technical-exercise-rest-service-0.1.0.jar`

WEB
* `npm build`

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Backend Framework
* [ReactJS](https://reactjs.org/) - Frontend Framework
* [Gradle](https://gradle.org/) - Dependency Management

## Contributing

No pull requests will be accepted at this time.

## Authors

* **Henrik Torland Klev** - *Initial work* - [torland-klev](https://github.com/torland-klev)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* [PurpleBoth](https://github.com/PurpleBooth) for .md-templates
