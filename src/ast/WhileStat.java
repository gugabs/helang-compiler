package ast;

import java.util.Map;

public class WhileStat extends Stat {

	private Expr right;
	private StatList statList;

	public WhileStat(Expr right, StatList statList) {
		super();
		this.right = right;
		this.statList = statList;
	}

	@Override
	public int eval( Map<String, Integer> memory ) {

		//while(right.eval()) statList.eval();
		return 1;
	}

	@Override
	public void genC() {

		System.out.print("while(");
		right.genC();
		System.out.println(") {");
		System.out.print(" ");
		statList.genC();
		System.out.println("}");
	}

}
