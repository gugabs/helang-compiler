package ast;

import java.util.Map;

public class PrintStat extends Stat {
	private Expr expr;

	public PrintStat(Expr expr) {
		super();
		this.expr = expr;
	}

	@Override
	public void eval( Map<String, Integer> memory ) {
		if(expr != null) 
			System.out.print(expr.eval(memory));	
	}

	@Override
	public void genC() { 
		System.out.print("printf(\"%d\", ");
		expr.genC();
		System.out.println(");");
	}

}
