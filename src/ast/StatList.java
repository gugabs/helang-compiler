package ast;
import java.util.List;
public class StatList extends Stat {

	private List<Stat> listStat;
	
	
	public StatList(List<Stat> listStat) {
		super();
		this.listStat = listStat;
	}

	@Override
	public void eval() {
		// TODO Auto-generated method stub

	}

	@Override
	public void genC() {
		// TODO Auto-generated method stub

	}

}
