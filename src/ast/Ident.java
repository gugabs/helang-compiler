package ast;

public class Ident extends Expr {

	private String name;
	//private int value; Ver memory que o Zé usa
	
	public Ident(String name) {
		super();
		this.name = name;
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
