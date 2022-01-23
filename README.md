Required applications and plugins:
1. IntelliJ
2. Java

Steps to run the testcase:
1. After downloading the ZIP from git, extract all the files into a folder
2. Open IntelliJ and import the project folder to IntelliJ
3. Make sure you have accurate webdriver version of chrome driver which matches with local chrome, if not download it from the chromeDriver
website and replace it with chromedriver.exe under "Project->testResources->Chrome->chromedriver.exe"
4. Open testng.xml, right click and choose to run(...\testng.xml)
5. See the result

Files in project: 
All the required methods and locators are present under src->main->test->java->TestCases->E2EActions
TestCases-Aspire_E2E files consists of methods which will take care of order of execution
java->utils->BrowserUtils files consisits of reusable methods that can interact with browser and locators
testResources folder contains required webdrivers, as of now only a chrome driver is created
Make sure all the dependencies are avaiable when running testng.xml from pom.xml, update the dependencies before running the testcase.