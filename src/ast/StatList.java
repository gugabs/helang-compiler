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
	public void eval( Map<String, Integer> memory ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genC() {
		//StatList ::= "{" { Stat } "}"
		listStat.forEach(item-> item.genC());

	}

}
