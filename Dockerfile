# we are extending everything from tomcat:9.0 image ...
FROM tomcat:9.0
MAINTAINER Ismail
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY /target/eazyschool10-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/