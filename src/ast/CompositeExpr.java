package ast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lexer.Symbol;

public class CompositeExpr extends Expr {

	private Expr left;
	private List<Symbol> op;
	private List<Expr> right;

	/*
	 * Três construtores: Se tiver apenas left. Se right tiver só um. Se tiver mais
	 * de uma expr no lado direito
	 */
	public CompositeExpr(Expr left) {
		super();
		this.left = left;
	}

	public CompositeExpr(Expr left, List<Symbol> op, List<Expr> right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public int eval(Map<String, Integer> memory) {
		return 1;
	}

	@Override
	public void genC() {
		System.out.print("(");
		left.genC();
		if (op != null) {
			Iterator<Symbol> op_it = op.iterator();
			Iterator<Expr> expr_it = right.iterator();
			while (expr_it.hasNext()) {
				System.out.print(" ");
				op_it.next().genC();
				System.out.print(" ");
				expr_it.next().genC();
			}
		}
		System.out.print(")");
	}

}
