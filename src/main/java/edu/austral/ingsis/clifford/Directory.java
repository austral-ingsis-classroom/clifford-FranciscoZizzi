package edu.austral.ingsis.clifford;

import java.util.List;

public class Directory implements FileSystem {
  private List<FileSystem> children;
  private String name;

  @Override
  public String getName() {
    return name;
  }

  public void remove(String name) {
    int childrenSize = children.size();
    children.removeIf(child -> child.getName().equals(name));
    if (childrenSize == children.size()) {
      throw new IllegalArgumentException("no such directory or file");
    }
  }

  public List<FileSystem> getChildren() {
    return children;
  }

  public FileSystem getChild(String name) {
    for (FileSystem child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    throw new IllegalArgumentException("no such directory or file");
  }

  public void addChild(FileSystem child) {
    children.add(child);
  }
}
