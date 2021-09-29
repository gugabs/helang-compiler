package ast;

import java.util.Map;

public class PrintLnStat extends Stat {

	private Expr expr;
	
	
	
	public PrintLnStat(Expr expr) {
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
		System.out.print("printf(\" %d \\n\" ,");
		expr.genC();
		System.out.println(");");
	}

}
