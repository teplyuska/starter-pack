#!/usr/bin/env bash

TOMCAT_ROOT=/opt/tomcat/apache-tomcat-8.5.6

cp ../rest-api/target/rest-api-1.0.war $TOMCAT_ROOT/webapps/rest-api.war

$TOMCAT_ROOT/bin/catalina.sh jpda start

tail -f $TOMCAT_ROOT/logs/catalina.out