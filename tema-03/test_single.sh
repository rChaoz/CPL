#!/usr/bin/env bash

CLASSPATH=../antlr-4.13.0-complete.jar:out/production/tema-03
java -cp $CLASSPATH cool.compiler.Compiler tests/tema3/$TEST.cl > tests/tema3/$TEST.s

echo 5 | spim -exception_file trap.handler.nogc -file tests/tema3/$TEST.s > tests/tema3/$TEST.out

diff tests/tema3/$TEST.ref tests/tema3/$TEST.out