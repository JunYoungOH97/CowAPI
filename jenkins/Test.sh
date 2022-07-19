#!/bin/bash

BUILD_PATH=/home/ec2-user/jenkins/CowAPI/server

echo ">>> Project test"
cd $BUILD_PATH

./gradlew test