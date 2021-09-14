public class Main {
	public static void main(String[] args) {
		char[] expr = ("+ 10 10").toCharArray();
		
		Compiler compiler = new Compiler();
		compiler.compile(expr);
	}
}
