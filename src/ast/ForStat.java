package ast;

import java.util.Map;

public class ForStat extends Stat {

  private Ident id;
  private Expr left;
  private Expr right;
  private StatList statList;

  public ForStat(Ident id, Expr left, Expr right, StatList statList) {
    super();
    this.id = id;
    this.left = left;
    this.right = right;
    this.statList = statList;
  }

  @Override
  public void eval(Map<String, Integer> memory) {

    String identName = id.getName();

    int leftVal = left.eval(memory);

    memory.put(identName, leftVal);

    if (left.eval(memory) > right.eval(memory))
      throw new RuntimeException(
          "Error: the expression to the left should be less than or equal to the right expression");

    while (memory.get(identName) <= right.eval(memory)) {
      statList.eval(memory);
      int next = memory.get(identName) + 1;
      memory.put(identName, next);
    }

    memory.remove(identName);
  }

  @Override
  public void genC() {
    System.out.print("for(int ");
    id.genC();
    System.out.print(" = ");
    left.genC();
    System.out.print("; ");
    id.genC();
    System.out.print(" <= ");
    right.genC();
    System.out.print("; ");
    id.genC();
    System.out.println("++) {");
    statList.genC();
    System.out.println("}");
  }

}
