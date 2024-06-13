package edu.austral.ingsis.clifford;

import java.util.List;

public class ChangeDirectoryCommand implements Command {
  private final CLI cli;

  public ChangeDirectoryCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    return "";
  }
}
