1.setup java11. - Done
  -set JAVA_HOME till java root folder ex: jdk1.8.3 / jdk11. 
  -set path untill jdk1.8.3\bin
2.Maven -Done
  -Set MAVEN_HOME and PATH till bin folder.
3.github and gitbash - Done
   - gitbash comes with it automatically.
4.STS -Done
   -STS comes in a jar file. Double click on the file after JAVA_HOME and PATH variable setup.
    It will open. But sometimes it does not. SO install rar or winzip and unzip it then open the folder 'sts-4.14.0.RELEASE' and click on 'SpringToolSuite4' whose type is 'Application'.
5.Do the setup of the workspace and go through the concepts.
  1. Eureka server and cleint setup done.
6.Get the postman.

Git related information:
----------------------
How to cinfigure a project to a git repo first time:
---------------------------------------------------
Go to the root of the project in the git bash
>git init -b fitnesstrackermaster    //.git folder will be created in hidden
>git status
>git add <filename> or git add --all 
>git commit -m 'comment/description'
>git push


Go to github.com
click on new
create a repository (Ex: fitnesstracker)

git remote add <name> <url>
git remote add fitnesstrackermaster https://github.com/satyabrat929/fitnesstracker.git   //configure a remote repository

git push <name>
git push fitnesstrackermaster

//login with browser > provide your git credentials and login > the code will be pushed to git now and you can see a pull request there.


Maven related info
-------------------
Window + R - .m2 <enter> - shows the local repository.

Workspace path in my local :  /d/SpringbootMicroServices/springbootws

Steps to create the server:-
==========================
1.Install above mentioned S/Ws.
2.Create service-1 
  Name: appointment-details-service
  Open sts- New - Spring starter project - Name (Ex:order-service) ->next -> select 'spring web' and 'spring boot dev tools' .
  
3.Create service-2
  Name : bookingand-contact-service
  
4.Create config server
  Name : fitness-config-server
  pom.xml:
   Add properties:-
   --------------
    <properties>
		<spring-cloud.version>2021.0.0</spring-cloud.version>
    </properties>

  Add dependencies:
  ----------------
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-actuator</artifactId>
			<scope>runtime</scope>
   </dependency>
    <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
   </dependency>

 Add dependency Management:
 -------------------------
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

Add repository from where it would find the cloud dependency jar
---------------------------------------------------------------
<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
		</repository>
	</repositories>

Add Annotation to the main class:
--------------------------------
@EnableConfigServer
public class FitnessConfigServerApplication

Add the properties in the .properties file
------------------------------------------
#Server port
server.port = 8888
 
#Git repo location.
spring.cloud.config.server.git.uri=https://github.com/satyabrat929/config

N:B: spring-cloud version and spring-parent-starter version may need to change. If you are using upper version of springs.

How to enable service-1 to fetch config details from github via config-server (mentioned in step-4)
===================================================================================================
> Add the below dependencies and properties to the pom.xml file.
                <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

     <properties>
		
		<spring-cloud.version>2021.0.0-M1</spring-cloud.version> <!-- this is required for spring version 2.6.7 -->
	</properties>

> Add following properties in the properties file of service-1
------------------------------------------------------------
  spring.cloud.config.uri=http://localhost:8888  ##This is the port where config-server is running.
  management.endpoints.web.exposure.include=refresh ##properties got refreshed can be fetched instantly via API endpoint without config-server restart.

If you get the error :
--------------------
"org.springframework.cloud.commons.ConfigDataMissingEnvironmentPostProcessor$ImportException: No spring.config.import set"
Add below property to your application.properties file:
-------------------------------------------------------
spring.config.import=optional:configserver:

Q.URL to check whether the configuration getting read from the git or not??
Ans: In the github create a property file with name '<application name used in application.properties file>.properties
      Add 'server.port=8081' #verify whether the app is getting started on 8081 port or not
      Add   'message=abc' #use url localhost:8081/getMessage to fetch the value as 'abc' in postman.
	  
5.Create fitness-registry-server	(service-1,service-2 and fitness-gateway get registered) 
 -main class contains the annotation "@EnableEurekaServer"
 -application.yaml
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
server:
  port: 8761
 - pom.xml file needs to include below:
 <properties>
		<java.version>11</java.version>
		<maven-jar-plugin.version>3.2.0</maven-jar-plugin.version>
		<spring-cloud.version>2021.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId> <!--Enables this to act as a server-->
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

6.How to covert a service to eureka client so that it gets registered in the server (fitness-registry-server)
 - main class : @EnableEurekaClient
 - pom.xml
    <properties>
		<java.version>11</java.version>
		<spring-cloud.version>2021.0.2</spring-cloud.version> <!--#1 when spring-boot-starter-parent version is 2.6.7-->
	</properties>
	
	<dependency> <!-- #2 -->
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
  
 -application.yaml::::::
 ----------------------
  server:
  port: 8082
spring:
  h2:
    console:
      enabled: true
  
  application:
    name: BOOKINGAND-CONTACT-SERVICE  #Application name displayed on the eureka server (here fitness-regitry-server)
        
eureka:
  client:
    register-with-eureka: true  ## automatically it gets registered at the server.
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/   ##We have to give the eureka server url

  instance:
    hostname: localhost



Error message: 
=============
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue May 10 00:55:15 IST 2022
There was an unexpected error (type=Not Found, status=404).
No message available

7.Create "fitness-gateway" service.
 pom.xml
 ------
 <properties>
		<java.version>11</java.version>
		<spring-cloud.version>2021.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId> <!-- for making a service gateway-->
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId> <!--Register the gateway service as a client in eureka server -->
		</dependency>
	</dependencies>
	
	main class
	----------
	@EnableEurekaClient
	
	application.yaml
	----------------
spring:
  application:
    name: FITNESS-GATEWAY   #Name of the gateway service

  cloud:
    gateway:
      routes:
        - id: appointment-details-service         # id can be anything
          uri: lb://APPOINTMENT-DETAILS-SERVICE   #Name of the service-1
          predicates:
            - Path=/appointments/**               # context path of the appointment-details-service API
        - id: bookingand-contact-service
          uri: lb://BOOKINGAND-CONTACT-SERVICE
          predicates:
            - Path=/bookings/**
    
eureka:
  client:
    register-with-eureka: true                     #Register with Eureka server
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/

  instance:
    hostname: localhost
    
    
server:
  port: 8989
  
  
 8.Check the services are accessed via fitness-gateway or not
 ============================================================
 Use the below URLS:-
 -------------------
 select GET in postman
 use the url 'http://localhost:8989/bookings/dummy' - expected response 'dummy endpoint for booking and contact details' [Testing end points for BOOKINGAND-CONTACT-SERVICE]
 
 select GET in Postman
 use the url 'http://localhost:8989/appointments/dummy' - expected response 'test' [Testing end points for APPOINTMENT-DETAILS-SERVICE]

 9.Feign Ribbon configuration:
 =============================
  -Call this from the existing service.
    @Bean
	@LoadBalanced  //Loadbalanced is used if the restTemplate call is accessing the end point running on more than one node. Load balance happens in round robin fashion.
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}