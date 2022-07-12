#!/bin/bash

DEPLOY_PATH=/home/ec2-user/CowAPI

echo ">>> git pull"
cd $DEPLOY_PATH

git pull origin dev