package ast;

import java.util.Map;

public class AssignStat extends Stat {

  private Ident id;
  private Expr expr;

  public AssignStat(Ident id, Expr expr) {
    super();
    this.id = id;
    this.expr = expr;
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    int e = expr.eval(memory);
    String identName = id.getName();
    memory.put(identName, e);
  }

  @Override
  public void genC() {
    id.genC();
    System.out.print(" = ");
    expr.genC();
    System.out.println("; ");
  }
}
