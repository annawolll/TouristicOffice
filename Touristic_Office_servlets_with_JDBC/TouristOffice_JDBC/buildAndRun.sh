#!/bin/sh
mvn clean package && docker build -t pl.polsl.lab/JavaServlets .
docker rm -f JavaServlets || true && docker run -d -p 9080:9080 -p 9443:9443 --name JavaServlets pl.polsl.lab/JavaServlets