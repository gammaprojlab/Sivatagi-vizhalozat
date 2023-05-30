@echo off
javac -d bin src\sivatagiVizhalozat\*.java
java -cp bin sivatagiVizhalozat.Main
rd /s /q bin

