# Simple War project 

A simple war application template. Fork and go. 

### Implementing Logback logging framework:
 The Logback architecture is comprised of three classes  
 - Logger: Is a context for logging messages
 - Appender: Places messages in their final destination (i.e. console, file) 
 - Layout: How the message should look in the console or file.

Run `Gradle assemble` to create a war artifact. Deploy to tomcat and you are golden.

### Random thoughts from Kent's brain.
This "simple" project is using the following technologies:
- [Jenkins](https://www.jenkins.io/)
- [Jenkins Pipeline](https://www.jenkins.io/doc/book/pipeline/)
- [Tomcat](https://tomcat.apache.org/)
- [Groovy](http://groovy-lang.org/single-page-documentation.html)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/features/)
- [Github](https://github.com/kentjones/)
- [Spock Testing Framework](https://spockframework.org/spock/docs/2.1/introduction.html)
- [MySQL](https://www.mysql.com/)

Using `Jenkins` to build and deploy a `simple.war` on a `Tomcat` instance running on `LAPTOP`.
- Develop/write code on `DESKTOP` using `Groovy` and `IntelliJ IDEA`
- Push source to `Github`
- `Jenkins` will monitor source changes every minute running on `LAPTOP`
- Build / Test / Deploy to Tomcat using `Jenkins Pipeline`
 
Testing is done using the Spock testing framework. 

`Git Commands: git push github main`

How to setup auto-deploy in Jenkins:
- add user `tomcat` with password to `conf\tomcat-users.xml`
- create user credentials to be used in `jenkinsfile`.  

Verify `simple.war` was deployed, see welcome page: "http://localhost:8080/welcome.jsp"



