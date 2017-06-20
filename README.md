# hybris_selenium_test
hybris selenium test

System Requirement:
- JDK 7 or higher
- Maven 3.0 or higher


This project was an attempt to learn to perform automated testing for web applications using Selenium web-driver. As I was new to Selenium
i first tried to go through some blogs and Selenium documentation to get to know the concepts and also tried to stick to JAVA as the scripting
language to start with(this approach helps out on the IDE for suggestions and autocomplete).

To build selenium web-driver scripts we basically need:
- Selenium Web-Driver API - Java SDK.
- Browser driver - i user the chrome driver available as a part of the repo.

Below are the steps to get started:
- start the hybris server with the OOTB B2C recipe installed. I used the hybris version 6.4.
- download the repo and import the maven project using any IDE of choice.
- change the path for the browser driver located in the file hybris_selenium_test/src/main/resources/hybristester.properties to the path on your 
  file system.
- choose the hybris_selenium_test/src/test/java/hybris/GuestCheckoutFlowTest.java JUnit test class and run the performGuestCheckoutFlowTest()
  test.
  
  
  Instructions for Build:
	
	To build with JUnit tests: 
	 >mvn clean package

	To skip running JUnit test while building 
	>mvn clean package -Dmaven.test.skip=true
	
Instructions for execution:

JUnit test execution:
	>mvn test
  or just to execute the guest checkout
  >mvn -Dtest=GuestCheckoutFlowTest test

  
  I have also added a screencast for the GuestCheckoutFlow that i have created for the OOTB B2C recipe. 
  
  Will keep working on this project to refactor the code as time allows.
