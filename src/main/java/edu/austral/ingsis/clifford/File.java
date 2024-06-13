package edu.austral.ingsis.clifford;

public class File implements FileSystem {
  private String name;

  @Override
  public String getName() {
    return name;
  }
}
