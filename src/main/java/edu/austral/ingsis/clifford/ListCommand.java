package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCommand implements Command {
  private final CLI cli;

  public ListCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() > 1 || !arguments.isEmpty()) {
      return ("invalid options or arguments");
    }
    List<String> names = getNameList(cli.currentDirectory.getChildren());
    if (options.isEmpty()) {
      return listNames(names);
    }
    String option = options.getFirst();
    if (option.startsWith("--ord=")) {
      switch (option.split("=")[1]) {
        case "asc":
          names.sort(String::compareTo);
          break;
        case "desc":
          names.sort(Comparator.reverseOrder());
          break;
        default :
          return ("unknown parameter " + option.split("=")[1]);
      }
      return listNames(names);
    }
    return "error";
  }

  private List<String> getNameList(List<FileSystem> elements) {
    List<String> elementNames = new ArrayList<>();
    for (FileSystem element : cli.currentDirectory.getChildren()) {
      elementNames.add(element.getName());
    }
    return elementNames;
  }

  private String listNames(List<String> names) {
    StringBuilder result = new StringBuilder();
    for (String name : names) {
      result.append(name).append(" ");
    }
    return result.isEmpty() ? "" : result.substring(0, result.length() - 1);
  }
}
