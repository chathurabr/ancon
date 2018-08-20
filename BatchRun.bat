set projectLocation=C:\Users\chathurar\IdeaProjects\ancon

cd %projectLocation%

set classpath=%projectLocation%\bin;%projectLocation%\lib\*

java org.testng.TestNG %projectLocation%\TenantsTest.xml

pause