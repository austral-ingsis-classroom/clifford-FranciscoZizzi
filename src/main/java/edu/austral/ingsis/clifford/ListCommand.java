package edu.austral.ingsis.clifford;

import java.util.List;

public class ListCommand implements Command {
  private final CLI cli;

  public ListCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    return "";
  }
}
