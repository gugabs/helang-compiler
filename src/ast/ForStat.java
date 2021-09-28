package ast;

public class ForStat extends Stat {

	private Ident id;
	private Expr left;
	private Expr right;
	private StatList statList;
	
	
	
	
	public ForStat(Ident id, Expr left, Expr right, StatList statList) {
		super();
		this.id = id;
		this.left = left;
		this.right = right;
		this.statList = statList;
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
