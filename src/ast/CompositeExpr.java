package ast;
import java.util.List;
import lexer.Symbol;

public class CompositeExpr extends Expr {

	private Expr left;
	private List<Symbol> op;
	private List<Expr> right;
	
	/*
	 * Três construtores: Se tiver apenas left. Se right tiver só um. Se tiver mais de uma expr no lado direito
	 * */
	public CompositeExpr(Expr left, List<Symbol> op, List<Expr> right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
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
