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

		/*
		 * while(right.eval()) statList.eval();
		 */
	}

	@Override
	public void genC() {

		System.out.print("while(");

		right.genC();

		System.out.println(") {");

		statList.genC();

		System.out.println("}");
	}

}
