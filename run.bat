@echo off
javac -d out -cp resources src/main/*.java src/main/cellstates/*.java src/main/gui/*.java
if %errorlevel% equ 0 (
    java -cp out main.Main
) else (
    echo Compilation failed.
)