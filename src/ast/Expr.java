package ast;

import java.util.Map;

abstract public class Expr {
	public abstract int eval( Map<String, Integer> memory);
	public abstract void genC();
}
