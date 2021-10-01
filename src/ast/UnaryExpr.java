package ast;

import java.util.Map;

import lexer.Symbol;

public class UnaryExpr extends Expr {
  private Expr expr;
  private Symbol op;

  public UnaryExpr(Expr expr, Symbol op) {
    this.expr = expr;
    this.op = op;
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    int eval = 0;
    if (op == Symbol.NOT) {
      eval = expr.eval(memory);
      if (eval != 0) {
        eval = 0;
      } else {
        eval = 1;
      }
    } else if (op == Symbol.PLUS) {
      eval = +expr.eval(memory);
      return eval;
    } else if (op == Symbol.MINUS) {
      eval = -expr.eval(memory);
    } else {
      throw new RuntimeException("Unknow");
    }

    return eval;
  }

  @Override
  public void genC() {
    System.out.print(this.op);
    expr.genC();
  }

}
