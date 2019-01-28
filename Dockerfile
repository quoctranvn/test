FROM openjdk:8-jre-slim

# Install needed packages
RUN apt-get update && apt-get install -y libglib2.0-0
RUN apt-get update && apt-get install -y libsm6
RUN apt-get update && apt-get install -y libx11-6

WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/FW-Auto-Java-1.0.jar FW-Auto-Java-1.0.jar
ADD  target/FW-Auto-Java-1.0-tests.jar FW-Auto-Java-1.0-tests.jar
ADD  target/libs libs
# Add the suite xmls
ADD  suites suites

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome
# SUITE_NAME  = Login, Home
# SELENIUM_HUB = selenium hub hostname

ENTRYPOINT java -cp FW-Auto-Java-1.0.jar:FW-Auto-Java-1.0-tests.jar:libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $SUITE_NAME