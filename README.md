# java_http_server

This server can be used to make:

Simple Get, Head, Put, Post and Options requests

and will send responses for the above as well as:

404 Not Found and 405 Method Not Allowed responses

for bogus requests.

The server will also produce a 418 response and reponses for image file contents for `.gif`, `.jpeg` and `.png`.

You will need to install Gradle and Maven.

#To run the cob test suite for Fitnesse tests

Follow the instructions at: https://github.com/8thlight/cob_spec

Navigate into your cloned cob_spec folder then type:

`mvn package`

`java -jar fitnesse.jar -p 9090`

You can find the test suite and run the tests at http://localhost:9090/HttpTestSuite.

#To run this server

Clone this repo, navigate into the folder and run:

`gradle build`

The Fitnesse tests will run your server or you can do this manually on the command line with the command:

`java -jar /Users/path/to/server.jar -p 5000`

#To run the unit tests

You can run the unit tests with

`gradle test`
