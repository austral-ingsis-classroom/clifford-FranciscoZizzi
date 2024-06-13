package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class CLI {
  public final Directory root;
  public Directory currentDirectory;

  public CLI(Directory root) {
    this.root = root;
    currentDirectory = root;
  }

  public String executeCommand(String string) {
    String[] components = string.split(" ");

    String command = components[0];

    List<String> options = new ArrayList<>();

    List<String> arguments = new ArrayList<>();

    for (int i = 1; i < components.length; i++) {
      if (components[i].startsWith("--")) {
        if (arguments.isEmpty()) {
          options.add(components[i]);
        } else {
          arguments.add(components[i]);
        }
      } else {
        arguments.add(components[i]);
      }
    }
    switch (command) {
      case "ls":
        return new ListCommand(this).execute(options, arguments);
      case "cd":
        return new ChangeDirectoryCommand(this).execute(options, arguments);
      case "touch":
        return new TouchCommand(this).execute(options, arguments);
      case "mkdir":
        return new MkdirCommand(this).execute(options, arguments);
      case "rm":
        return new RemoveCommand(this).execute(options, arguments);
      case "pwd":
        return new PwdCommand(this).execute(options, arguments);
      default:
        return "Unknown command: " + command;
    }
  }
}
