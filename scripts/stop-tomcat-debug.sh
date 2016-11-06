#!/usr/bin/env bash

TOMCAT_ROOT=/opt/tomcat/apache-tomcat-8.5.6

$TOMCAT_ROOT/bin/catalina.sh stop

tail -f $TOMCAT_ROOT/logs/catalina.out