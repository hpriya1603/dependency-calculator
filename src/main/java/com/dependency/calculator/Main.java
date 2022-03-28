package com.dependency.calculator;

import com.dependency.calculator.io.StringDependencyGraphParser;
import com.dependency.calculator.model.DependencyGraph;

import java.util.List;
import java.util.Map;

public class Main {

  public static void main (String args[]) {
    String input = args[0];
    parseInput(input);
  }

  private static void parseInput(String input) {
    StringDependencyGraphParser parser = new StringDependencyGraphParser();
    DependencyGraph graph = parser.parse(input);
    TransitiveDependencyGraphCalculator dependencyGraphCalculator = new TransitiveDependencyGraphCalculator();
    Map<String, List<String>> result = dependencyGraphCalculator.calculateDependencies(graph);
    System.out.println("The Transitive dependencies are : "+result);
  }
}

