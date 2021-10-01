package main;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ast.AssignStat;
import ast.CompositeExpr;
import ast.Expr;
import ast.Ident;
import ast.IfStat;
import ast.Numero;
import ast.PrintLnStat;
import ast.PrintStat;
import ast.Stat;
import ast.StatList;
import ast.Statement;
import ast.UnaryExpr;
import ast.VarList;
import ast.WhileStat;
import ast.Program;
import ast.ForStat;
import lexer.Symbol;

public class Compiler {
	public Symbol token;
	private int tokenPos;

	private char[] input;

	private char identifier;

	static private Hashtable<String, Symbol> keywordsTable;

	static {
		keywordsTable = new Hashtable<String, Symbol>();

		keywordsTable.put("var", Symbol.VAR);
		keywordsTable.put("int", Symbol.INT);
		keywordsTable.put("Int", Symbol.INT);
		keywordsTable.put("if", Symbol.IF);
		keywordsTable.put("else", Symbol.ELSE);
		keywordsTable.put("not", Symbol.NOT);
		keywordsTable.put("and", Symbol.AND);
		keywordsTable.put("or", Symbol.OR);
		keywordsTable.put("for", Symbol.FOR);
		keywordsTable.put("in", Symbol.IN);
		keywordsTable.put("while", Symbol.WHILE);
		keywordsTable.put("print", Symbol.PRINT);
		keywordsTable.put("println", Symbol.PRINTLN);
	}

	private int numberValue;
	/**
	 * nome da ultima variavel encontrada no codigo
	 */
	private String variableName;

	public Compiler(char[] expr) {
		this.tokenPos = 0;
		this.input = expr;
	}

	public Program compile() {
		Program p = program();
		if(token!= Symbol.EOF)
			error("Fim do arquivo não esperado");
		return p;
	}

	private Program program() {
		this.nextToken();

		List<Stat> statList = new ArrayList<Stat>();
		List<Ident> identList = new ArrayList<Ident>();

		while (token == Symbol.VAR) {
			identList.add(this.varList());
		}

		VarList varl = new VarList(identList);
		
		while (token == Symbol.ID || token == Symbol.FOR || token == Symbol.PRINT || token == Symbol.PRINTLN
				|| token == Symbol.WHILE || token == Symbol.IF) {
			statList.add(this.stat());
		}

		StatList sl = new StatList(statList);

		this.nextToken();
		return new Program(sl, varl);
	}

	private Ident varList() {
		if (this.token != Symbol.VAR)
			error("Error: expected 'var'.");

		this.nextToken();

		if (this.token != Symbol.INT)
			error("Error: expected 'int'.");

		this.nextToken();

		if (this.token != Symbol.ID)
			error("Error: expected an identifier.");

		Ident id = new Ident(variableName);

		this.nextToken();

		if (this.token != Symbol.SEMICOLON)
			error("Error: expected ';'.");

		this.nextToken();
		return id;
	}

	private void nextToken() {
		while (this.tokenPos < this.input.length && (this.input[this.tokenPos] == ' '
				|| this.input[this.tokenPos] == '\r' || this.input[this.tokenPos] == '\n'))
			this.tokenPos++;

		if (this.tokenPos >= input.length) {
			token = Symbol.EOF;
			return;
		}

		char currCharacter = this.input[this.tokenPos];

		if (Character.isLetter(currCharacter)) {
			this.getWord();
		} else if (Character.isDigit(currCharacter)) {
			this.getNumber();
		} else {
			switch (currCharacter) {
			case '+':
				token = Symbol.PLUS;
				break;

			case '-':
				token = Symbol.MINUS;
				break;

			case '*':
				token = Symbol.MULT;
				break;

			case '/':
				token = Symbol.DIV;
				break;

			case '%':
				token = Symbol.MOD;
				break;

			case '=':
				if (this.input[tokenPos + 1] == '=') {
					token = Symbol.EQUAL;
					this.tokenPos++;
				} else {
					token = Symbol.ASSIGN;
				}
				break;

			case '>':
				if (this.input[tokenPos + 1] == '=') {
					token = Symbol.GREATER_E;
					this.tokenPos++;
				} else {
					token = Symbol.GREATER;
				}
				break;

			case '<':
				if (this.input[tokenPos + 1] == '=') {
					token = Symbol.LESS_E;
					this.tokenPos++;
				} else {
					token = Symbol.LESS;
				}

				break;

			case '!':
				if (this.input[tokenPos + 1] == '=') {
					token = Symbol.DIFF;
					this.tokenPos++;
				} else {
					token = Symbol.NOT;
				}

				break;

			case '&':
				if (this.input[tokenPos + 1] == '&') {
					token = Symbol.AND;
					this.tokenPos++;
				}
				break;

			case '|':
				if (this.input[tokenPos + 1] == '|') {
					token = Symbol.OR;
					this.tokenPos++;
				}
				break;

			case '(':
				token = Symbol.LEFT_P;
				break;

			case ')':
				token = Symbol.RIGHT_P;
				break;

			case '{':
				token = Symbol.LEFT_B;
				break;

			case '}':
				token = Symbol.RIGHT_B;
				break;

			case ',':
				token = Symbol.COMMA;
				break;

			case ';':
				token = Symbol.SEMICOLON;
				break;

			case '.':
				if (this.input[tokenPos + 1] == '.') {
					token = Symbol.RANGE;
					this.tokenPos++;
				}
				break;

			case '\0':
				token = Symbol.EOF;
				System.out.print("Terminou programa");
				break;
			}

			this.tokenPos++;
		}

	}

	private void getWord() {
		StringBuffer identifier = new StringBuffer();

		while (tokenPos < this.input.length && this.input[tokenPos] != ' '
				&& Character.isLetter(this.input[this.tokenPos])) {
			identifier.append(this.input[tokenPos]);
			this.tokenPos++;
		}

		Symbol value = keywordsTable.get(identifier.toString());
		this.variableName = identifier.toString();
		if (value == null)
			this.token = Symbol.ID;
		else
			this.token = value;
	}

	private void getNumber() {
		StringBuffer number = new StringBuffer();

		while (tokenPos < this.input.length && this.input[tokenPos] != ' '
				&& Character.isDigit(this.input[this.tokenPos])) {
			number.append(this.input[tokenPos]);
			this.tokenPos++;
		}

		this.token = Symbol.NUMBER;
		this.numberValue = Integer.valueOf(number.toString());
	}

	private Stat stat() {
		switch (this.token) {
		case ID:
			return this.assignStat();
		case FOR:
			return this.forStat();
		case WHILE:
			return this.whileStat();
		case PRINT:
			return this.printStat();
		case PRINTLN:
			return this.printlnStat();
		case IF:
			return this.ifStat();
		default:
			error("Error: expected a statement.");
			return null;
		}
	}

	private StatList statList() {
		if (this.token != Symbol.LEFT_B)
			error("Error: expected '{'.");

		this.nextToken();

		List<Stat> statList = new ArrayList<Stat>();

		while (this.token == Symbol.ID || this.token == Symbol.FOR || this.token == Symbol.WHILE
				|| this.token == Symbol.PRINT || this.token == Symbol.PRINTLN || this.token == Symbol.IF) {
			statList.add(this.stat());
		}

		if (this.token != Symbol.RIGHT_B)
			error("Error: expected '}'.");

		this.nextToken();
		return new StatList(statList);
	}

	private AssignStat assignStat() {

		Ident id = new Ident(variableName);

		this.nextToken();
		if (this.token != Symbol.ASSIGN)
			error("Error: expected '='.");

		this.nextToken();

		Expr e = this.expr();

		if (this.token != Symbol.SEMICOLON)
			error("Error: expected ';'.");

		this.nextToken();
		return new AssignStat(id, e);
	}

	private IfStat ifStat() {
		this.nextToken();

		Expr e = this.expr();

		this.nextToken();

		StatList sl = this.statList();

		this.nextToken();

		if (this.token == Symbol.ELSE) {
			StatList listElse = this.statList();
			return new IfStat(e, sl, listElse);
		}
		return new IfStat(e, sl);
	}

	private ForStat forStat() {
		this.nextToken();

		if (this.token == Symbol.ID) {
			Ident id = new Ident(variableName);
			this.nextToken();

			if (this.token != Symbol.IN)
				error("Error: expected 'in'.");

			this.nextToken();

			Expr left = this.expr();

			if (this.token != Symbol.RANGE)
				error("Error: expected '..'.");

			this.nextToken();

			Expr right = this.expr();
			StatList forList = this.statList();
			return new ForStat(id, left, right, forList);

		} else {
			error("Error: expected an identifier.");
			return null;
		}

	}

	private WhileStat whileStat() {
		this.nextToken();
		/* WhileStat ::= "while" Expr StatList */
		Expr e = this.expr();

		StatList sl = this.statList();
		// this.nextToken();
		return new WhileStat(e, sl);
	}

	private PrintStat printStat() {
		this.nextToken();

		Expr e = this.expr();

		if (this.token != Symbol.SEMICOLON)
			error("Error: expected ';'.");

		this.nextToken();
		return new PrintStat(e);

	}

	private PrintLnStat printlnStat() {
		this.nextToken();

		Expr e = this.expr();

		if (this.token != Symbol.SEMICOLON)
			error("Error: expected ';'.");

		this.nextToken();
		return new PrintLnStat(e);
	}

	private Expr expr() {
		Expr left = this.andExpr();
		List<Symbol> op = new ArrayList<Symbol>();
		List<Expr> right = new ArrayList<Expr>();

		if (this.token == Symbol.OR) {
			op.add(this.token);
			this.nextToken();
			right.add(this.andExpr());
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr andExpr() {
		Expr left = this.relExpr();

		List<Symbol> op = new ArrayList<Symbol>();
		List<Expr> right = new ArrayList<Expr>();

		if (this.token == Symbol.AND) {
			op.add(this.token);
			this.nextToken();
			right.add(this.relExpr());
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr relExpr() {
		Expr left = this.addExpr();

		List<Symbol> op = new ArrayList<Symbol>();
		List<Expr> right = new ArrayList<Expr>();

		if (this.token == Symbol.GREATER || this.token == Symbol.GREATER_E || this.token == Symbol.LESS
				|| this.token == Symbol.LESS_E || this.token == Symbol.EQUAL || this.token == Symbol.DIFF) {
			op.add(this.token);
			this.nextToken();
			right.add(this.addExpr());
			left = new CompositeExpr(left, op, right);
		}
		return left;

	}

	private Expr addExpr() {
		Expr left = this.multExpr();

		List<Symbol> op = new ArrayList<Symbol>();
		List<Expr> right = new ArrayList<Expr>();

		if(this.token == Symbol.PLUS || this.token == Symbol.MINUS) {
			while (this.token == Symbol.PLUS || this.token == Symbol.MINUS) {
				op.add(this.token);
				this.nextToken();
				right.add(this.multExpr());	
			}
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr multExpr() {
		Expr left = this.simpleExpr();

		List<Symbol> op = new ArrayList<Symbol>();
		List<Expr> right = new ArrayList<Expr>();
		
		//Adicionei um if, para dar add na lista durante o while, e só depois colocar left como recebendo a lista 
		if(this.token == Symbol.MULT || this.token == Symbol.DIV || this.token == Symbol.MOD) {
			while (this.token == Symbol.MULT || this.token == Symbol.DIV || this.token == Symbol.MOD) {
				op.add(this.token);
				this.nextToken();
				right.add(this.simpleExpr());
			}
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr simpleExpr() {
		Expr e = null;
		if (this.token == Symbol.NUMBER) {
			e = new Numero(numberValue);
			this.nextToken();
		} else if (this.token == Symbol.ID) {
			e = new Ident(variableName);
			this.nextToken();
		} else if (this.token == Symbol.MINUS || this.token == Symbol.NOT || this.token == Symbol.PLUS) {
			Symbol guardar = this.token;
			this.nextToken();
			e = new UnaryExpr(simpleExpr(), guardar);
		} else if (this.token == Symbol.LEFT_P) {
			this.nextToken();
			e = expr();
			if (this.token != Symbol.RIGHT_P)
				error("Error: expected ')'.");
			this.nextToken();
		} else {
			error("Error: expected a simpleExpr.");
		}
		return e;
	}

	private void error(String errorMessage) {
		throw new RuntimeException(errorMessage);
	}
}