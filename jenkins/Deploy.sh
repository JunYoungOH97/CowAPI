#!/bin/bash

DEPLOY_PATH=/home/ec2-user/jenkins/CowAPI/server/build/libs

echo ">>> Project deploy"
cd $DEPLOY_PATH

sudo chmod +x server-0.0.1-SNAPSHOT.jar
java -jar server-0.0.1-SNAPSHOT.jar &> /dev/null &