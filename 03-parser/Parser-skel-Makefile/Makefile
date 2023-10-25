# Bash aliases are not expanded inside non-interactive shells, so we have
# to manually define the meaning of "ANTLR4".
CLASSPATH="lib/antlr-4.13.0-complete.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:.:build:generated"
ANTLR4=java -jar lib/antlr-4.13.0-complete.jar
.PHONY: run clean build test

build: Test.class

# Delete the auto-generated files
clean:
	rm -fr build generated

# Test.java is hardcoded to read the input from "program.txt".
# Make sure that the CLASSPATH contains the current folder and the antlr4 jar.
run: Test.class
	java -cp $(CLASSPATH) Test

runTest1: Test.class
	java -cp $(CLASSPATH) Test 1

runTest3: Test.class
	java -cp $(CLASSPATH) Test 3

Test.class: src/Test.java generated/CPLangParser.java generated/CPLangLexer.java
	javac -cp $(CLASSPATH) $^ -d build/

generated/CPLangParser.java: CPLangParser.g4 generated/CPLangLexer.java
	$(ANTLR4) -visitor -listener CPLangParser.g4 -o generated

generated/CPLangLexer.java: CPLangLexer.g4
	$(ANTLR4) $^ -o generated

# Rules for using JUnit-based testing framework
test: AutoTester.class
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore AutoTester

AutoTester.class: src/AutoTester.java src/Test.java generated/CPLangParser.java
	javac -cp $(CLASSPATH) $^ -d build/
