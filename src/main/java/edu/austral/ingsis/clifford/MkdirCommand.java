package edu.austral.ingsis.clifford;

import java.util.List;

public class MkdirCommand implements Command {
  private final CLI cli;

  public MkdirCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() + arguments.size() != 1) {
      throw new IllegalArgumentException("missing argument");
    }
    String dirName = options.isEmpty() ? arguments.getFirst() : options.getFirst();
    if (dirName.contains("/")) {
      throw new IllegalArgumentException("directory cannot contain a / in it's name");
    }
    cli.currentDirectory.addChild(new Directory(dirName));
    return "'" + dirName + "' directory created";
  }
}
