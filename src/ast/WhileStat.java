package ast;

import java.util.Map;

public class WhileStat extends Stat {

  private Expr left;
  private StatList statList;

  public WhileStat(Expr left, StatList statList) {
    super();
    this.left = left;
    this.statList = statList;
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    while (left.eval(memory) != 0)
      statList.eval(memory);
  }

  @Override
  public void genC() {
    System.out.print("while(");
    left.genC();
    System.out.println(") {");
    statList.genC();
    System.out.println("}");
  }
}
