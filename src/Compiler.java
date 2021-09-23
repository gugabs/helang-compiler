import java.util.Hashtable;

public class Compiler {
  Symbol token;
  int tokenPos;

  char[] input;

  char identifier;

  static private Hashtable<String, Symbol> keywordsTable;

  static {
    keywordsTable = new Hashtable<String, Symbol>();

    keywordsTable.put("var", Symbol.VAR);
    keywordsTable.put("int", Symbol.INT);
    keywordsTable.put("if", Symbol.IF);
    keywordsTable.put("else", Symbol.ELSE);
    keywordsTable.put("not", Symbol.NOT);
    keywordsTable.put("and", Symbol.AND);
    keywordsTable.put("or", Symbol.OR);
    keywordsTable.put("for", Symbol.FOR);
    keywordsTable.put("while", Symbol.WHILE);
  }

  public Compiler(char[] expr) {
    this.tokenPos = 0;
    this.input = expr;
  }

  public void compile() {
    for (int i = 0; i < this.input.length; i++) {
      String output = String.format("pos[%d]: %c\n", i, this.input[i]);
      System.out.println(output);
    }

    for (int i = 0; i < this.input.length; i++) {
      this.nextToken();
    }
  }

  private void nextToken() {
    while (this.tokenPos < this.input.length
        && (this.input[this.tokenPos] == ' ' || this.input[this.tokenPos] == '\r' || this.input[this.tokenPos] == '\n'))
      this.tokenPos++;

    if (this.tokenPos >= input.length)
      return;

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
        if (this.input[tokenPos + 1] == '=')
          token = Symbol.EQUAL;
        else
          token = Symbol.ASSIGN;

        this.tokenPos++;
        break;

      case '>':
        if (this.input[tokenPos + 1] == '=')
          token = Symbol.GREATER_E;
        else
          token = Symbol.GREATER;

        this.tokenPos++;
        break;

      case '<':
        if (this.input[tokenPos + 1] == '=')
          token = Symbol.LESS_E;
        else
          token = Symbol.LESS;

        this.tokenPos++;
        break;

      case '!':
        if (this.input[tokenPos + 1] == '=')
          token = Symbol.DIFF;
        else
          token = Symbol.NOT;

        this.tokenPos++;
        break;

      case '&':
        if (this.input[tokenPos + 1] == '&')
          token = Symbol.AND;

        this.tokenPos++;
        break;

      case '|':
        if (this.input[tokenPos + 1] == '|')
          token = Symbol.OR;

        this.tokenPos++;
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
      }

      this.tokenPos++;
    }
  }

  private void getWord() {
    StringBuffer identifier = new StringBuffer();

    while (tokenPos < this.input.length && this.input[tokenPos] != ' ') {
      identifier.append(this.input[tokenPos]);
      this.tokenPos++;
    }

    Symbol value = keywordsTable.get(identifier.toString());

    if (value == null)
      this.token = Symbol.ID;
    else
      this.token = value;
  }

  private void getNumber() {
    StringBuffer number = new StringBuffer();

    while (tokenPos < this.input.length && this.input[tokenPos] != ' ') {
      number.append(this.input[tokenPos]);
      this.tokenPos++;
    }

    this.token = Symbol.NUMBER;
    System.out.println(Integer.valueOf(number.toString()));
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

  private void program() {
    if (this.token != Symbol.LEFT_B)
      error("Error: expected '{'.");

    try {
      while (this.token != Symbol.RIGHT_B)
        this.varList();
    } catch (Exception e) {
      error("Error: expected a valid input.");
    }

    while (token == Symbol.ID || token == Symbol.FOR || token == Symbol.PRINT || token == Symbol.PRINTLN
        || token == Symbol.WHILE || token == Symbol.IF) {
      this.stat();
    }
  }

  private void varList() {
    if (this.token != Symbol.VAR)
      error("Error: expected 'var'.");

    this.nextToken();

    if (this.token != Symbol.INT)
      error("Error: expected 'int'.");

    this.nextToken();

    if (this.token != Symbol.ID)
      error("Error: expected an identifier.");

    // Armazenar identificador

    this.nextToken();

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void assignStat() {
    char identifier = this.identifier;

    this.nextToken();

    if (this.token != Symbol.EQUAL)
      error("Error: expected '='.");

    this.nextToken();

    // Expression

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void ifStat() {
    this.nextToken();

    // Expression
    // statList

    this.nextToken();

    if (this.token == Symbol.ELSE) {
      // statList
    }
  }

  private void forStat() {
    this.nextToken();

    if (this.token == Symbol.ID) {
      char identifier = this.identifier;

      this.nextToken();

      if (this.token != Symbol.IN)
        error("Error: expected 'in'.");

      this.nextToken();

      // Expression

      if (this.token != Symbol.RANGE)
        error("Error: expected '..'.");

      this.nextToken();

      // Expression
    } else {
      error("Error: expected an identifier.");
    }
  }

  private void whileStat() {
    this.nextToken();

    if (this.token != Symbol.LEFT_P)
      error("Error: expected '('.");

    // Expression

    this.nextToken();

    if (this.token != Symbol.RIGHT_P)
      error("Error: expected ')'.");

    this.nextToken();

    // Expression
    // statList()
  }

  private void printStat() {
    this.nextToken();

    // Expression

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void printlnStat() {
    this.nextToken();

    // Expression

    if (this.token != Symbol.SEMICOLON)
      error("Error: expected ';'.");

    this.nextToken();
  }

  private void expr() {

  }

  private void andExpr() {

  }

  private void relExpr() {

  }

  private void addExpr() {

  }

  private void multExpr() {

  }

  private void simpleExpr() {

  }

  private void error(String errorMessage) {
    throw new RuntimeException(errorMessage);
  }
}