package ast;

import java.util.List;
import java.util.Map;

public class VarList {

	private List<Ident> id;

	public VarList(List<Ident> id) {
		super();
		this.id = id;
	}

	public void eval(Map<String, Integer> memory) {
		id.forEach(item -> {
			item.eval(memory);
		});
	}

	public void genC() {
		/*
		 * VarList ::= { "var" Int Ident ";" }
		 */
		id.forEach(item -> {
			System.out.print("int ");
			item.genC();
			System.out.println(";");
		});
	}
}
