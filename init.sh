#!/bin/bash

mvn clean package -DskipTests

spark-submit \
  --class "App" \
  --master local[2] \
  target/sparktest-1.0-SNAPSHOT-jar-with-dependencies.jar
