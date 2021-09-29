package main;
import java.util.HashMap;
import java.util.Map;

import ast.Program;

public class Main {
  public static void main(String[] args) {
    char[] expr = ("var Int i;\r\n"
    		+ "var Int soma;\r\n"
    		+ "var Int n;\r\n"
    		+ "soma = 0;\r\n"
    		+ "i = 0;\r\n"
    		+ "while i < n { \r\n"
    		+ "  soma = soma + 1;\r\n"
    		+ "}\r\n"
    		+ "print soma;\r\n"
    		+ "").toCharArray();
    

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
       	    ast.eval();
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
