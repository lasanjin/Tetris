#!/bin/bash

javac -cp "tetris/src/" tetris/src/Main.java
java -cp "tetris/src/" Main

# delete .class files
find -name '*.class' -delete
