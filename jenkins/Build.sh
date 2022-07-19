#!/bin/bash

BUILD_PATH=/home/ec2-user/jenkins/CowAPI/server

echo ">>> Project Build"
cd $BUILD_PATH

chmod +x ./gradlew
./gradlew assemble