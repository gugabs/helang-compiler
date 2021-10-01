package ast;

import java.util.Map;

public class Program {

  private StatList statList;
  private VarList varlist;

  public Program(StatList statList, VarList varlist) {
    super();
    this.statList = statList;
    this.varlist = varlist;
  }

  public void eval(Map<String, Integer> memory) {
    this.varlist.eval(memory);
    this.statList.eval(memory);
  }

  public void genC() {

    System.out.println("#include <stdio.h>");
    System.out.println("int main() {");
    varlist.genC();
    statList.genC();
    System.out.println("return 0;\n}");
  }
}
