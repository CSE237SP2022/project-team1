#!/bin/bash

cd src
cd counterClass
javac -classpath .. Counter.java
java -classpath .. counterClass.Counter

