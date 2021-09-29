package ast;

import java.util.Map;

public class AssignStat extends Stat {

	private Ident id;
	private Expr expr;
	
	
	public AssignStat(Ident id, Expr expr) {
		super();
		this.id = id;
		this.expr = expr;
	}

	@Override
	public int eval( Map<String, Integer> memory ) {
		//int e = expr.eval(memory);
		//memory.put(id, e);
		return 1;
	}

	@Override
	public void genC() {
		/*
		 * AssignStat ::= Ident "=" Expr ";"
		 * */
		id.genC();
		System.out.print(" = ");
		expr.genC();
		System.out.println("; ");
	}

}
