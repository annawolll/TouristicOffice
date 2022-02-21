@echo off
call mvn clean package
call docker build -t pl.polsl.lab/JavaServlets .
call docker rm -f JavaServlets
call docker run -d -p 9080:9080 -p 9443:9443 --name JavaServlets pl.polsl.lab/JavaServlets