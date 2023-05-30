#!/bin/bash
javac -d bin src/sivatagiVizhalozat/*.java
java -cp bin sivatagiVizhalozat.Main $1
