# availability-reporter
Check availability of websites

![img.png](img.png)

## User
* In-memory user added
* User name : admin. Password: admin
* User can log in and access the application
* http://localhost:5001/app/dashboard

## Configuration
*  Endpoints can be added : src/main/resources/endpoints.yaml
*  Periodic call configuration :  application.properties , monitor.call.period=10000

## Main Features
*  Reads a list of web pages (HTTP URLs) and corresponding page content requirements from a configuration file.
   * Implemented : Yes
*  Periodically makes an HTTP request to each page.
    * Implemented : Yes
*  Verifies that the page content received from the server matches the content requirements.
    * Implemented : Yes
*  Measures the time it took for the web server to complete the whole request.
    * Implemented : Yes
*  Writes a log file that shows the progress of the periodic checks.
    * Implemented : Yes
*  (OPTIONAL) Implement a single-page HTTP server interface in the same process that shows (HTML) each monitored web site and their current (last check) status.
    * Implemented : Yes
* Structure your code : Yes
* Use best practises : Yes
* Use naming conventions : yes
* Show understanding of software development concepts : yes

## Features
*  Monitoring matrix : monitoring_matrix.csv
*  Visual representation of monitoring report in dashboard

## Technical
* Language: Java 11
* Framework for backend and rest endpoint : Spring Boot
* Framework for DAO : Not used in this case, can be used Spring Data JPA
* jsoup used for DOM parsing.
* Lomobk used to reduce code footprint
* log4j/slf4j added for logging
* Build Environment:Maven
* Server: Inbuilt Tomcat
* Front End : Thymeleaf as Template Engine,BottStrap for styling

## Improvement Scope :
Improvement scope is  there, however those are out the assignment scope.

## Instruction to run :
Before running this application you should have to confirm following:
* JDK 11 installed. <pre>java --version</pre>
* maven installed.  <pre>mvn --version</pre>
* Migration script (*.sql) : Not required 

Then run the following command on terminal:
* Application will start on 5001 port. (http://localhost:5001/app)
<pre>
    git clone https://github.com/asraful/availability-reporter.git  [change as you needed]
    cd availability-reporter
    mvn spring-boot:run
</pre>



##Desing Consideration :

![monitor](https://user-images.githubusercontent.com/284564/200200084-816a4050-5f2d-4979-9ed3-c2f7e6f36368.png)

##Other design consideration 

AWS cloudwatch could be another choice to gather the log through log-group and utilizing cloudwatch
#Data collection policy consideration 

In this application, its possibly required to check with data policy and other laws .

https://gdpr-info.eu/
