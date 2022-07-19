#!/bin/bash

PROJECT_PATH=/home/ec2-user/jenkins/CowAPI

echo ">>> git pull"
cd $PROJECT_PATH

sudo git pull origin main