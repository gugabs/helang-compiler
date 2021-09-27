import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ast.Statement;

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
  /**  nome da última variável encontrada no código
  */
  private String variableName;

  /*
  public class Program {
    private List<Statement> statList;

    public Program(List<Statement> statList) {
      super();
      this.statList = statList;
    }
  }*/

  private void program() {
    this.nextToken();
    
    while (token == Symbol.VAR) {
      this.varList();
    }
    
    while (token == Symbol.ID || token == Symbol.FOR || token == Symbol.PRINT || token == Symbol.PRINTLN
        || token == Symbol.WHILE || token == Symbol.IF) {
      this.stat();
    }
    this.nextToken();
    return;
  }

  /*
   * private Program program() { List<Variable> varList = new ArrayList();
   * List<Statement> statList = new ArrayList<>(); statList.add(this.stat());
   * 
   * while (this.token == Symbol.VAR) varList.add(this.varList());
   * 
   * while (token == Symbol.ID || token == Symbol.FOR || token == Symbol.PRINT ||
   * token == Symbol.PRINTLN || token == Symbol.WHILE || token == Symbol.IF) {
   * statList.add(this.stat());
   * 
   * return new Program(statList); }
   */

  private void varList() {
    if (this.token != Symbol.VAR)
      error("Error: expected 'var'.");

    this.nextToken();
    
    if (this.token != Symbol.INT)
      error("Error: expected 'int'.");

    this.nextToken();

    if (this.token != Symbol.ID)
      error("Error: expected an identifier.");

    this.nextToken();

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  public Compiler(char[] expr) {
    this.tokenPos = 0;
    this.input = expr;
  }

  public void compile() {
   /* 
	for (int i = 0; i < this.input.length; i++) {
      String output = String.format("pos[%d]: %c\n", i, this.input[i]);
      System.out.println(output);
    }
    */

    this.program();
  }
  
  
  
  private void nextToken() {
    while (this.tokenPos < this.input.length
        && (this.input[this.tokenPos] == ' ' || 
        this.input[this.tokenPos] == '\r' || this.input[this.tokenPos] == '\n'))
      this.tokenPos++;

    if (this.tokenPos >= input.length) {
      token = Symbol.EOF;
      return ;
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
      	if(this.input[tokenPos + 1] == '&') {  
    		token = Symbol.AND;	
    	    this.tokenPos++;
    	} 	
        break;

      case '|':
      	if(this.input[tokenPos + 1] == '|') {  
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
    	if(this.input[tokenPos + 1] == '.') {  
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

    System.out.println(this.token);
  }

  private void getWord() {
    StringBuffer identifier = new StringBuffer();

    while (tokenPos < this.input.length && this.input[tokenPos] != ' ' && Character.isLetter(this.input[this.tokenPos])) {
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

    while (tokenPos < this.input.length && this.input[tokenPos] != ' ' && Character.isDigit(this.input[this.tokenPos])) {
      number.append(this.input[tokenPos]);
      this.tokenPos++;
    }

    this.token = Symbol.NUMBER;
    this.numberValue = Integer.valueOf(number.toString());
  }

  private void stat() {
    switch (this.token) {
    case ID:
      this.assignStat();
      break;

    case FOR:
      this.forStat();
      break;

    case WHILE:
      this.whileStat();
      break;

    case PRINT:
      this.printStat();
      break;

    case PRINTLN:
      this.printlnStat();
      break;

    case IF:
      this.ifStat();
      break;

    default:
      error("Error: expected a statement.");
      break;
    }
  }

  private void statList() {
    if (this.token != Symbol.LEFT_B)
      error("Error: expected '{'.");

    this.nextToken();

    while (this.token == Symbol.ID || this.token == Symbol.FOR || this.token == Symbol.WHILE
        || this.token == Symbol.PRINT || this.token == Symbol.PRINTLN || this.token == Symbol.IF) {
      this.stat();
    }

    if (this.token != Symbol.RIGHT_B)
      error("Error: expected '}'.");

    this.nextToken();
  }

  private void assignStat() {

    String id = variableName;
    this.nextToken();
    if (this.token != Symbol.ASSIGN)
      error("Error: expected '='.");

    this.nextToken();

    this.expr();

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
    // return new AssignStat(id, e);
  }

  private void ifStat() {
    this.nextToken();

    this.expr();

    this.nextToken();
    this.statList();

    this.nextToken();
 
    if (this.token == Symbol.ELSE) {
      this.statList();
    }
  }

  private void forStat() {
    this.nextToken();

    if (this.token == Symbol.ID) {
      String id = variableName;
      this.nextToken();

      if (this.token != Symbol.IN)
        error("Error: expected 'in'.");

      this.nextToken();

      this.expr();

      if (this.token != Symbol.RANGE)
        error("Error: expected '..'.");

      this.nextToken();

      this.expr();
      statList();

    } else {
      error("Error: expected an identifier.");
    }

  }

  private void whileStat() {
    this.nextToken();
    /* WhileStat ::= "while" Expr StatList    */
    this.expr();

    this.statList();
    //this.nextToken();

  }

  private void printStat() {
    this.nextToken();

    this.expr();

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void printlnStat() {
    this.nextToken();

    this.expr();

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void expr() {
    this.andExpr();

    if (this.token == Symbol.OR)
    {
    	this.nextToken();
    	this.andExpr();
    }
    
    
  }

  private void andExpr() {
    this.relExpr();

    if (this.token == Symbol.AND) {
    	this.nextToken();
        this.andExpr();
    	
    }
   }

  private void relExpr() {
    this.addExpr();

    if (this.token == Symbol.GREATER || this.token == Symbol.GREATER_E || this.token == Symbol.LESS
        || this.token == Symbol.LESS_E || this.token == Symbol.EQUAL || this.token == Symbol.DIFF) {
    	this.nextToken();
    	// this.addExpr();
  	}
  }

  private void addExpr() {
    this.multExpr();
    
    while (this.token == Symbol.PLUS || this.token == Symbol.MINUS) {
    	this.nextToken();
    	this.multExpr();
    }
     
  }

  private void multExpr() {
    this.simpleExpr();

    while (this.token == Symbol.MULT || this.token == Symbol.DIV || this.token == Symbol.MOD){
    	this.nextToken();
    	 this.simpleExpr();
   }
      }

  private void simpleExpr() {
	  
	  
    if(this.token == Symbol.NUMBER) {
      System.out.println(this.numberValue);
      this.nextToken();
    } else  if(this.token == Symbol.ID) {
      this.getWord();
      this.nextToken();
    } else if(this.token == Symbol.PLUS) {
    	this.nextToken();
    	simpleExpr();
    } else if(this.token==Symbol.NOT) {
    	this.nextToken();
    	simpleExpr();
    }  else if(this.token==Symbol.MINUS) {
    	this.nextToken();
    	simpleExpr();
    }  else if(this.token==Symbol.PLUS) {
    	this.nextToken();
    	simpleExpr();
    }  else if(this.token==Symbol.LEFT_P) {
      this.nextToken();
      expr();

      if(this.token!=Symbol.RIGHT_P) 
        error("Error: expected ')'.");
      this.nextToken();
    } else {
      error("Error: expected a simpleExpr.");
    }
  }

  private void error(String errorMessage) {
    throw new RuntimeException(errorMessage);
  }
}