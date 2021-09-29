 package ast;

import java.util.Map;

abstract public class Stat {
	public abstract void eval(Map<String, Integer> memory);
	public abstract void genC();
}
