package ast;
import java.util.Map;

import lexer.Symbol;

public class UnaryExpr extends Expr {
    private Expr expr;
    private Symbol op;
    
    public UnaryExpr( Expr expr, Symbol op ) {
        this.expr = expr;
        this.op = op;
    }
	@Override
	public int eval(Map<String, Integer> memory) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void genC() {
		System.out.print(this.op);
		expr.genC();
	}

}


