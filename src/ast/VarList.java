package ast;

import java.util.List;
import java.util.Map;

public class VarList {

  private List<Ident> id;

  public VarList(List<Ident> id) {
    super();
    this.id = id;
  }

  public void eval(Map<String, Integer> memory) {
    id.forEach(identifier -> {
      if (memory.containsKey(identifier))
        throw new RuntimeException("Error: duplicated identifier.");
    });
  }

  public void genC() {
    id.forEach(item -> {
      System.out.print("int ");
      item.genC();
      System.out.println(";");
    });
  }
}
