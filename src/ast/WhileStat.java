package ast;

public class WhileStat extends Stat {

	private Expr right;
	private StatList statList;
		
	public WhileStat(Expr right, StatList statList) {
		super();
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
