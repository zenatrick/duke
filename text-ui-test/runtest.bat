@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del data\duke.txt
del ACTUAL.txt

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\seedu\duke\Duke.java ..\src\main\java\seedu\duke\command\*.java ..\src\main\java\seedu\duke\common\*.java ..\src\main\java\seedu\duke\exception\*.java ..\src\main\java\seedu\duke\task\*.java ..\src\main\java\seedu\duke\storage\*.java ..\src\main\java\seedu\duke\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin seedu/duke/Duke < input.txt > ACTUAL.txt

REM compare the output to the expected output
FC ACTUAL.txt EXPECTED.txt