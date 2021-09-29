package ast;

import java.util.Map;

public class Numero extends Expr {
	private int number;

	public Numero(int number) {
		super();
		this.number = number;
	}

	@Override
	public void eval(Map<String, Integer> memory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genC() {
		System.out.print(number);
	}

	
	
}
