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
      throw new IllegalArgumentException("missing arguments");
    }
    if (options.getFirst().equals("--recursive") && arguments.isEmpty()) {
      cli.currentDirectory.remove("--recursive");
      return "'--recursive' removed";
    } else if (options.getFirst().equals("--recursive")) {
        cli.currentDirectory.remove(arguments.getFirst());
    } else {
      if (options.isEmpty()) {
        FileSystem object = cli.currentDirectory.getChild(arguments.getFirst());
        if (!(object instanceof Directory)) {
          cli.currentDirectory.remove(arguments.getFirst());
        } else {
          throw new IllegalArgumentException("must use --recursive to delete directory");
        }
      }
    }
    return "'" + arguments.getFirst() + "' removed";
  }
}
