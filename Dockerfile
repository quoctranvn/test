FROM openjdk:8-jre-slim

# Add the project jar & copy dependencies
ADD  target/FW-Auto-Java-1.0.jar FW-Auto-Java-1.0.jar
ADD  target/FW-Auto-Java-1.0-tests.jar FW-Auto-Java-1.0-tests.jar
ADD  target/libs libs

# Add the suite xmls
ADD  suites suites

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome
# SUITE_NAME  = login-page / home-page
# SELENIUM_HUB = selenium hub hostname

ENTRYPOINT java -cp FW-Auto-Java-1.0.jar;FW-Auto-Java-1.0-tests.jar;libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG suites/$SUITE_NAME