package edu.austral.ingsis.clifford;

import java.util.List;

public interface Command {
  public String execute(List<String> options, List<String> arguments);
}
