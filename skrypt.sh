#!bin/bash

while  java mysql init -eq 0 ; do
	sleep 300s
done
