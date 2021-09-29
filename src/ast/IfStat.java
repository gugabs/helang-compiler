package ast;

import java.util.Map;

public class IfStat extends Stat {

	private Expr left;
	private StatList statList;
	private StatList statElse;
	
	/*
	 * Um construtor para caso tenha else e outro caso n�o tenha
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
	public int eval( Map<String, Integer> memory ) {
		return 1;
	}



	@Override
	public void genC() {
		/*
		 * "if" Expr StatList ["else" StatList ]
		 * */
		//if() {} else {}
		System.out.print("if (");
		left.genC();
		System.out.println(") {");
		statList.genC();
		if(statElse == null) {
			System.out.println("}");
		} else {
			System.out.print("} else {");
			statElse.genC();
			System.out.println("}");
		}
			
	}

}
