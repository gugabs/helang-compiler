package main;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import ast.Program;

public class Main {
  public static void main(String[] args) {
    char[] expr = readFile(args[1]);

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

  private static char[] readFile(String filePath) {
    StringBuffer input = new StringBuffer();

    try {
      File programInput = new File(filePath);
      Scanner reader = new Scanner(programInput);
      while (reader.hasNextLine()) {
        String data = reader.nextLine();
        input.append(data);
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error: file not found.");
      e.printStackTrace();
    }

    return input.toString().toCharArray();
  }

  private static void error(String errorMessage) {
    throw new RuntimeException(errorMessage);
  }
}
