package ast;

import java.util.Map;

public class Ident extends Expr {

	private static String name;
	
	public Ident(String name) {
		super();
		this.name = name;
	}

	
	@Override
	public int eval(Map<String, Integer> memory) {
		return memory.get(name);
	}


	@Override
	public void genC() {
		System.out.print(name);
	}


	public static String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
