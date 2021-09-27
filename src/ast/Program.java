package ast;

import java.util.List;

public class Program {

	private List<Stat> statList;
	private VarList varlist;
	
	public Program(List<Stat> statList, VarList varlist) {
		super();
		this.statList = statList;
		this.varlist = varlist;
	}
	
	
	public void eval() {
	}
	public void genC() {
	}
	
	
}
