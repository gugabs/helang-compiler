package ast;

import java.util.Map;

public class Ident extends Expr {

  private String name;

  public Ident(String name) {
    super();
    this.name = name;
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    return memory.get(this.name);
  }

  @Override
  public void genC() {
    System.out.print(name);
  }

  public String getName() {
    return name;
  }
}
