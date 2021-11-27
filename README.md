# Simple War project 

A simple war application template. Fork and go. 

### Implementing Logback logging framework:
 The Logback architecture is comprised of three classes  
 - Logger: Is a context for logging messages
 - Appender: Places messages in their final destination (i.e. console, file) 
 - Layout: How the message should look in the console or file.


Run `Gradle assemble` to create a war artifact. Deploy to tomcat and you are golden.

I'm using Jenkins to build and deploy to my local tomcat.  



Display the welcome page "http://localhost:8080/welcome.jsp"



