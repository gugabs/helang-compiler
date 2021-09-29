package ast;

import java.util.Map;

public class PrintLnStat extends Stat {

	private Expr expr;
	
	
	
	public PrintLnStat(Expr expr) {
		super();
		this.expr = expr;
	}
	
	
	
	@Override
	public void eval( Map<String, Integer> memory ) {
		// TODO Auto-generated method stub

	}



	@Override
	public void genC() { 
		System.out.print("printf(\" %d \\n\" ,");
		expr.genC();
		System.out.println(");");
	}

}
