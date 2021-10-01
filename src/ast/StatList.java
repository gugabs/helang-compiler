package ast;

import java.util.List;
import java.util.Map;

public class StatList extends Stat {

  private List<Stat> listStat;

  public StatList(List<Stat> listStat) {
    super();
    this.listStat = listStat;
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    listStat.forEach(stat -> {
      stat.eval(memory);
    });
  }

  @Override
  public void genC() {
    listStat.forEach(item -> item.genC());
  }
}
