# WebMVC test example

### What is covered in this example?
You can find the following examples:
1. How to mock DB for tests. There is configured MySQL DB for main project and in memory H2 DB for tests.
1. How to send POST request to you controller and validate the response
1. How to send GET request, receive the response object and validate the response

### How to launch?
1. Replace your MySQL connection properties in the `src/main/resources/application.properties`
1. Run project

### How to check tests works?
1. Run all tests from `src/test/java

### Important
1. PLease note we are not using service layer in this example. 
This example is required to show of how to test the controller layer.
1. Please note we are performing the `business logic` in the controller. 
Don't do it in your test tasks or in real projects.
