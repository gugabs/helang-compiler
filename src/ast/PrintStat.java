package ast;

public class PrintStat extends Stat {
	private Expr expr;

	public PrintStat(Expr expr) {
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
