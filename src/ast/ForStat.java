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
	public int eval( Map<String, Integer> memory ) {
		
        String id_name = ident.eval();
        
        if (memory.containsKey(id))
            throw new RuntimeException("Identifier wasn't declared inside the for statement");

        int value = left.eval(memory);
        memory.put(id_name,  value);

        if (left.eval(memory) > right.eval(memory))
            throw new RuntimeException("initial value should be less than or equal end value");

        for (; memory.get(id_name) <= right.eval(memory); memory.put(id_name,  memory.get(id_name) + 1))
            statList.eval(memory);

        memory.remove(id_name);
    
	
	return 1;
	}

	
	@Override
	public void genC() {
		System.out.print("for(");
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
