package edu.austral.ingsis.clifford;

import java.util.List;

public class MkdirCommand implements Command {
  private final CLI cli;

  public MkdirCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    return "";
  }
}
