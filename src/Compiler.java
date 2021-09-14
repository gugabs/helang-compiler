public class Compiler {
	int tokenPos;
	char[] input;
	
	public void compile(char[] expr) {
		this.tokenPos = 0;
		this.input = expr;
		
		for (int i = 0; i < expr.length; i++) {
			String output = String.format("pos[%d]: %c\n", i, expr[i]);
			System.out.println(output);
		}
	}
}
