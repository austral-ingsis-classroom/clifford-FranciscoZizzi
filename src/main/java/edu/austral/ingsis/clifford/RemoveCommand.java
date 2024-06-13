package edu.austral.ingsis.clifford;

import java.util.List;

public class RemoveCommand implements Command {
  private final CLI cli;

  public RemoveCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.isEmpty() && arguments.isEmpty()) {
      return ("missing arguments");
    }
    if (!options.isEmpty() && options.get(0).equals("--recursive") && arguments.isEmpty()) {
      cli.currentDirectory.remove("--recursive");
      return "'--recursive' removed";
    } else if (!options.isEmpty() && options.get(0).equals("--recursive")) {
      cli.currentDirectory.remove(arguments.get(0));
    } else {
      if (options.isEmpty()) {
        FileSystem object = cli.currentDirectory.getChild(arguments.get(0));
        if (!(object instanceof Directory)) {
          cli.currentDirectory.remove(arguments.get(0));
        } else {
          return "cannot remove '" + arguments.get(0) + "', is a directory";
        }
      }
    }
    return "'" + arguments.get(0) + "' removed";
  }
}
