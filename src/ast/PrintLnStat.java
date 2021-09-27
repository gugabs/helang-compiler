package ast;

public class PrintLnStat extends Stat {

	private Expr expr;
	
	
	
	public PrintLnStat(Expr expr) {
		super();
		this.expr = expr;
	}
	
	
	
	@Override
	public void eval() {
		// TODO Auto-generated method stub

	}



	@Override
	public void genC() {
		// TODO Auto-generated method stub

	}

}
