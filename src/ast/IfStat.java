package ast;

public class IfStat extends Stat {

	private Expr left;
	private StatList statList;
	private StatList statElse;
	
	/*
	 * Um construtor para caso tenha else e outro caso não tenha
	 * */
	public IfStat(Expr left, StatList statList) {
		super();
		this.left = left;
		this.statList = statList;
	}
	
	public IfStat(Expr left, StatList statList, StatList statElse) {
		super();
		this.left = left;
		this.statList = statList;
		this.statElse = statElse;
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
