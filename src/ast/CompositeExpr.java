package ast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lexer.Symbol;

public class CompositeExpr extends Expr {

  private Expr left;
  private List<Symbol> op;
  private List<Expr> right;

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
    int resultado = left.eval(memory);

    if (op != null) {
      Iterator<Symbol> op_it = op.iterator();
      Iterator<Expr> expr_it = right.iterator();

      while (expr_it.hasNext()) {
        Symbol currOp = op_it.next();
        Expr currExpr = expr_it.next();

        if (currOp == Symbol.PLUS) {
          resultado = resultado + currExpr.eval(memory);
        } else if (currOp == Symbol.MINUS) {
          resultado = resultado - currExpr.eval(memory);
        } else if (currOp == Symbol.MULT) {
          resultado = resultado * currExpr.eval(memory);
        } else if (currOp == Symbol.DIV) {
          resultado = resultado / currExpr.eval(memory);
        } else if (currOp == Symbol.MOD) {
          resultado = resultado % currExpr.eval(memory);
        } else if (currOp == Symbol.GREATER) {
          resultado = resultado > currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.GREATER_E) {
          resultado = resultado >= currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.LESS) {
          resultado = resultado < currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.LESS_E) {
          resultado = resultado <= currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.DIFF) {
          resultado = resultado != currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.EQUAL) {
          resultado = resultado == currExpr.eval(memory) ? 1 : 0;
        } else if (currOp == Symbol.AND) {
          if (resultado != 0 && currExpr.eval(memory) != 0)
            resultado = 1;
          else
            resultado = 0;
        } else if (currOp == Symbol.OR) {
          if (resultado != 0 || currExpr.eval(memory) != 0)
            resultado = 1;
          else
            resultado = 0;
        } else {
          throw new RuntimeException("Internal error in Composite Eval");
        }

        currExpr.eval(memory);
      }

    }

    return resultado;
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
