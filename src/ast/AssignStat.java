package ast;

public class AssignStat extends Stat {

	private Ident id;
	private Expr expr;
	
	
	public AssignStat(Ident id, Expr expr) {
		super();
		this.id = id;
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
