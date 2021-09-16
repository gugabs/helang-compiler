public class Compiler {
	int tokenPos;
	char[] input;
	
	String relOp;
	String addOp;
	String multOp;
	
	public Compiler(char[] expr) {
		relOp = new	String("< <= > >= == !=");
		addOp = new String("+ -");
		multOp = new String("* / %");
		
		this.tokenPos = 0;
		this.input = expr;
	}
	
	public void compile() {		
		for (int i = 0; i < this.input.length; i++) {
			String output = String.format("pos[%d]: %c\n", i, this.input[i]);
			System.out.println(output);
		}
		
		expr();
	}
	
	private void expr() {
		nextToken();
	}
	
	private void nextToken() {
		while (this.tokenPos < this.input.length && this.input[this.tokenPos] == ' ')
			this.tokenPos++;
		
		String token = Character.toString(this.input[this.tokenPos]);
		
		if (relOp.toString().contains(token) || addOp.toString().contains(token) || multOp.toString().contains(token)) {
			// Partir para a leitura de uma expressão
		} 
	}
	
	private void calculate(char operand, int n1, int n2) {
		switch (operand) {
			case '+':
				System.out.println(n1 + n2);
				break;
			case '-':
				System.out.println(n1 - n2);
				break;
			case '*':
				System.out.println(n1 * n2);
				break;
			case '/':
				System.out.println(n1 / n2);
				break;
			case '%':
				System.out.println(n1 % n2);
				break;
		}
	}
	
	private int getNumber() {
		StringBuffer number = new StringBuffer();
		
		while (tokenPos < this.input.length && this.input[tokenPos] != ' ') {
			number.append(this.input[tokenPos]);
			this.tokenPos++;		
		}
		
		return Integer.valueOf(number.toString());
	}
}
