package edu.austral.ingsis.clifford;

import java.util.List;

public class PwdCommand implements Command {
  private final CLI cli;

  public PwdCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    return "";
  }
}
