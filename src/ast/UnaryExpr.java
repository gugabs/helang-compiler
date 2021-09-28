package ast;
import lexer.Symbol;

public class UnaryExpr extends Expr {
    private Expr expr;
    private Symbol op;
    
    public UnaryExpr( Expr expr, Symbol op ) {
        this.expr = expr;
        this.op = op;
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


