[![Build Status](https://travis-ci.org/leosilvadev/gchat.svg?branch=master)](https://travis-ci.org/leosilvadev/gchat)

# GChat
Tiny-chat example using Gradle, Groovy, GroovyTemplate, Java and Spring (Data, MVC and Websocket)
	
	All the commands bellow use the gradlew, for windows you have to use gradlew.bat



### Build instructions
`GChat` uses a Gradle-based build system. Building the code yourself should be a straightforward case of (you need to have gradle configured at your machine!):

	git clone https://github.com/leosilvadev/gchat.git
	cd gchat
	./gradlew test

### Testing
The project is testing using Spock, GEB, Jasmine (sooner) and JaCoCo for test coverage report
To run the tests run the command for linux/mac:

	./gradlew test
	
The testing report are generated at:

	~gchat/build/reports/tests/index.html
	
The coverage report are generated at:

	~gchat/build/reports/coverage/test/html/index.html
	
## How to run?
Since GChat is built using SpringBoot and Gradle, it's really easy to run, run the command:

	./gradlew run
	
You can check the application at:

	http://localhost:8080/
