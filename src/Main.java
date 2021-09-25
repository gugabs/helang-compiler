public class Main {
  public static void main(String[] args) {
    char[] expr = ("while (5 + 5) { print 10; }").toCharArray();

    Compiler compiler = new Compiler(expr);
    compiler.compile();
  }
}
