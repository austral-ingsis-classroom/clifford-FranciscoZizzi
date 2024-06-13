package edu.austral.ingsis.clifford;

import java.util.List;

public class ChangeDirectoryCommand implements Command {
  private final CLI cli;

  public ChangeDirectoryCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (!options.isEmpty()) {
      throw new IllegalArgumentException("no options allowed");
    }
    if (arguments.size() != 1) {
      throw new IllegalArgumentException("invalid arguments");
    }
    String argument = arguments.getFirst();
    switch (argument) {
      case "." -> { }
      case ".." -> cli.currentDirectory = goToParentDirectory();
      default -> {
        if (argument.startsWith("/")) {
          cli.currentDirectory = cli.root;
          String[] route = argument.split("/");
          navigateRoute(route);
        } else {
          String[] route = argument.split("/");
          navigateRoute(route);
        }
      }
    }
    return getSuccessMessage(cli.currentDirectory.getName());
  }

  private String getSuccessMessage(String directoryName) {
    return "moved to directory: '" + directoryName + "'";
  }

  private Directory goToParentDirectory() {
    Directory parent = findDirectoryWithChild(cli.root, cli.currentDirectory.getName());
    if (parent == null) {
      throw new IllegalArgumentException("already at root");
    }
    return parent;
  }

  private Directory findDirectoryWithChild(Directory directory, String childName) {
    List<FileSystem> children = directory.getChildren();
    for (FileSystem child : children) {
      if (!(child instanceof Directory)) {
        continue;
      }
      if (child.getName().equals(childName)) {
        return directory;
      }
      Directory search = findDirectoryWithChild((Directory) child, childName);
      if (search != null) {
        return search;
      }
    }
    return null;
  }

  private void navigateRoute(String[] route) {
    Directory startingDirectory = cli.currentDirectory;
    for (String s : route) {
      try {
        FileSystem nextDirectory = cli.currentDirectory.getChild(s);
        cli.currentDirectory = (Directory) nextDirectory;
      } catch (Exception e) {
        cli.currentDirectory = startingDirectory;
        throw new IllegalArgumentException(s + " is not a directory");
      }
    }
  }
}
