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
    		+ "  if i%2 == (0+0*0/1) {\r\n"
    		+ "	soma = (--soma) + i*i;\r\n"
    		+ "  }\r\n"
    		+ "  i = i + 1;\r\n"
    		+ "}\r\n"
    		+ "somaFor = 0;\r\n"
    		+ "for k in 0..100 {\r\n"
    		+ "  if i%2 == 0 {\r\n"
    		+ "	somaFor = somaFor + k*k;\r\n"
    		+ "  }\r\n"
    		+ "}\r\n"
    		+ "println soma;\r\n"
    		+ "println somaFor;\r\n"
    		+ "").toCharArray();
    
    Compiler compiler = new Compiler(expr);
    new Program ast = compiler.compile();

	ast.genc();

    if(compiler.token == Symbol.EOF)
      System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
  }
}
