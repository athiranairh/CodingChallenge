Steps to follow:

1. Clone this repo

git clone https://github.com/athiranairh/CodingChallenge.git




2. Create postgres SQL database and schema and update application.proerties with corresponding entries

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=admin

spring.datasource.hikari.schema=stockmarket



3. Build and run the app using -

mvn spring-boot:run



App runnning Location: http://localhost:8080




The following APIs are available:

GET /api/dataSet/dataSets/{Id}

GET /api/dataSet/allDataSets?search=(stock:'<searchString>')

POST /api/dataSet/dataSet/

POST /api/dataSet/allDataSets/

POST /api/dataSet/allDataSetsFromCSV/?file




Test data in csv format and sample REST commands are available inside sample data folder




Note: The application needs to be updated with proper exception handling code, logging, security mechanisms to be production ready.
