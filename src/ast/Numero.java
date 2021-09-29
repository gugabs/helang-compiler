package ast;

import java.util.Map;

public class Numero extends Expr {
	private int number;

	public Numero(int number) {
		super();
		this.number = number;
	}

	@Override
	public int eval(Map<String, Integer> memory) {
		return 1;
	}

	@Override
	public void genC() {
		System.out.print(number);
	}

	
	
}
