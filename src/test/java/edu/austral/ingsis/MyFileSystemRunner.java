package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.Directory;

import java.util.ArrayList;
import java.util.List;

public class MyFileSystemRunner implements FileSystemRunner {
  @Override
  public List<String> executeCommands(List<String> commands) {
    CLI cli = new CLI(new Directory("/"));
    List<String> result = new ArrayList<>();
    for (String command : commands) {
     result.add(cli.executeCommand(command));
    }
    return result;
  }
}
