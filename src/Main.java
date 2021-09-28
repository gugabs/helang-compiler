import java.util.HashMap;
import java.util.Map;

import ast.Program;

public class Main {
  public static void main(String[] args) {
    char[] expr = ("var Int i;\r\n"
    		+ "var Int soma;\r\n"
    		+ "var Int somaFor;\r\n"
    		+ "var Int n;\r\n"
    		+ "var Int verd;\r\n"
    		+ "n = 100;\r\n"
    		+ "soma = 0;\r\n"
    		+ "i = 0;\r\n"
    		+ "verd = 2;\r\n"
    		+ "while i < n && !!verd { \r\n"
    		+ "  i = i + 1;\r\n"
    		+ "}\r\n"
    		+ "println soma;\r\n"
    		+ "println somaFor;"
    		+ "").toCharArray();
    
    Compiler compiler = new Compiler(expr);
    Program ast = compiler.compile();
    Map<String, Integer> memory = new HashMap<>();
    
    ast.eval();
	ast.genC();

  }
}
