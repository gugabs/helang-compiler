package ast;

import java.util.List;

public class VarList {

	private List<Ident> id;
	
	public VarList(List<Ident> id) {
		super();
		this.id = id;
	}
	
	public void eval() {
		
		
	}
	
	public void genC() {
		/*
		 * VarList ::= { "var" Int Ident ";" }
		 * */
		id.forEach(item -> {
			System.out.print("int ");
			item.genC();
			System.out.println(";");
		});
	}
}
