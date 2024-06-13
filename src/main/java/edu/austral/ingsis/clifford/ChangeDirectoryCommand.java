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
      case ".." -> {
        Directory parent = goToParentDirectory();
        if (parent == null) {
          cli.currentDirectory = cli.root;
          return getSuccessMessage(cli.root.getName());
        }
        cli.currentDirectory = goToParentDirectory();
      }
      default -> {
        if (argument.startsWith("/")) {
          cli.currentDirectory = cli.root;
          String[] route = argument.split("/");
          try {
            navigateRoute(route);
          } catch (Exception e) {
            return e.getMessage();
          }
        } else {
          String[] route = argument.split("/");
          try {
            navigateRoute(route);
          } catch (Exception e) {
            return e.getMessage();
          }
        }
      }
    }
    return getSuccessMessage(cli.currentDirectory.getName());
  }

  private String getSuccessMessage(String directoryName) {
    return "moved to directory '" + directoryName + "'";
  }

  private Directory goToParentDirectory() {
    return findDirectoryWithChild(cli.root, cli.currentDirectory.getName());
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
        throw new IllegalArgumentException("'" + s + "' directory does not exist");
      }
    }
  }
}
