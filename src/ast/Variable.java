package ast;

public class Variable {
  private String identifier;

  Variable(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return this.identifier;
  }
}
