package main;

import java.util.HashMap;
import java.util.Map;

import ast.Program;

public class Main {
  public static void main(String[] args) {
    char[] expr = ("var Int i; i = 40; while i < 10 { i = i + 1; println i; } println i;")
            .toCharArray();

    Compiler compiler = new Compiler(expr);
    Program ast = compiler.compile();
    Map<String, Integer> memory = new HashMap<>();

    if (args.length == 0)
      error("Input length is zero");
    switch (args[0]) {
    case "-gen" -> {
      ast.genC();
    }
    case "-run" -> {
      ast.eval(memory);
    }
    default -> {
      error("Error when reading input arguments");
    }
    }
  }

  private static void error(String errorMessage) {
    throw new RuntimeException(errorMessage);
  }
}
