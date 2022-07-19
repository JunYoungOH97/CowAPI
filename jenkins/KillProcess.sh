#!/bin/bash

echo ">>> Project kill"

CURRENT_PID=$(ps -ef | grep java | grep server-0.0.1-SNAPSHOT.jar | awk '{print $2}')

echo "Running PID: {$CURRENT_PID}"
sudo kill -9 $CURRENT_PID
sleep 5
