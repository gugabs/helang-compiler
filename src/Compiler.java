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
      nextToken();
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
      getWord();
    } else if (Character.isDigit(currCharacter)) {
      getNumber();
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

  private void error(String errorMessage) {
    throw new RuntimeException(errorMessage);
  }
}