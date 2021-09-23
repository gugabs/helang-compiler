public class Main {
	public static void main(String[] args) {
	  char[] expr = ("for 10 5").toCharArray();
		
		Compiler compiler = new Compiler(expr);
		compiler.compile();
	}
}
