package ast;

import java.util.List;

public class Program {

	private StatList statList;
	private VarList varlist;

	public Program(StatList statList, VarList varlist) {
		super();
		this.statList = statList;
		this.varlist = varlist;
	}

	public void eval() {
	}

	public void genC() {

		System.out.println("#include<stdio.h>\nint main(){");

		/*
		 * statList.forEach(item -> item.genC(pw)); 
		 * varList.forEach(item  -> item.genC(pw));
		 */

		System.out.println("return 0;\n}");

	}

}
