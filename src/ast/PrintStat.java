package ast;

import java.util.Map;

public class PrintStat extends Stat {
	private Expr expr;

	public PrintStat(Expr expr) {
		super();
		this.expr = expr;
	}

	@Override
	public int eval( Map<String, Integer> memory ) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void genC() { 
		System.out.print("printf(\"%d\", ");
		expr.genC();
		System.out.println(");");
	}

}
