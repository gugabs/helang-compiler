public class Main {
  public static void main(String[] args) {
    char[] expr = ("var int i;").toCharArray();

    Compiler compiler = new Compiler(expr);
    compiler.compile();
  }
}
