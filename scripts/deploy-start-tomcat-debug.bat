set tomcat-root=C:\Tools\apache-tomcat-8.5.6
set CATALINA_HOME=%tomcat-root%
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_92

REM RD /S /Q %tomcat-root%\webapps\rest-api-1.0
REM DEL /F /Q /A %tomcat-root%\webapps\rest-api-1.0.war

copy ..\rest-api\target\rest-api-1.0.war %tomcat-root%\webapps\rest-api.war

%tomcat-root%\bin\catalina.bat jpda start