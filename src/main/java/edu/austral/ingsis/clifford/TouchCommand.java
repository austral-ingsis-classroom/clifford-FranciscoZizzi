package edu.austral.ingsis.clifford;

import java.util.List;

public class TouchCommand implements Command {
  private final CLI cli;

  public TouchCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() + arguments.size() != 1) {
      return ("missing argument");
    }
    String fileName = options.isEmpty() ? arguments.getFirst() : options.getFirst();
    if (fileName.contains("/")) {
      return ("file cannot contain a /");
    }
    cli.currentDirectory.addChild(new File(fileName));
    return "'" + fileName + "' file created";
  }
}
