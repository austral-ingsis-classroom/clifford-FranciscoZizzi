package edu.austral.ingsis.clifford;

import java.util.List;

public class PwdCommand implements Command {
  private final CLI cli;

  public PwdCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    StringBuilder stringBuilder = new StringBuilder("/");
    Directory index = cli.currentDirectory;
    while (true) {
      Directory parent = findDirectoryWithChild(cli.root, index.getName());
      if (parent == null) {
        break;
      }
      stringBuilder.insert(0, "/" + parent.getName());
      index = parent;
    }
    stringBuilder.append(cli.currentDirectory.getName());
    int rootNameLength = cli.root.getName().length();
    stringBuilder.delete(0, rootNameLength + 1);
    return stringBuilder.toString();
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
}
