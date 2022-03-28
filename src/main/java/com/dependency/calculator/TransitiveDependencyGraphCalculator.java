package com.dependency.calculator;

import com.dependency.calculator.model.DependencyGraph;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Calculates Transitive dependencies, using modified DFS algorithm and generates a topological sort of the Graph.
 */

public class TransitiveDependencyGraphCalculator {

  /**
   * Calculates transitive dependencies
   *
   * @param graph
   *       input DependencyGraph
   * @return
   *       a Map of dependents against a list of transitive dependencies
   */
  public Map<String, List<String>> calculateDependencies (DependencyGraph graph){
    Map<String, List<String>> map = new HashMap<>();

    for (String dependent : graph.allDependents()) {
      List<String> result = topologicalSort(dependent, graph);
      String vertex = result.get(0);
      List<String> nodes = getDependency(result);
      map.put(vertex, nodes);
    }
    return map;
  }

  /**
   * Sorts the items for a given vertex topologically
   *
   * @param start
   *       denotes the vertex of the graph
   * @param graph
   *       input DependencyGraph
   * @return
   *       List of topologically sorted items of the graph
   */
  public List<String> topologicalSort(String start, DependencyGraph graph) {
    LinkedList<String> result = new LinkedList<>();
    Map<String, Boolean> isVisited = new HashMap<>();
    topologicalSortRecursive(start, isVisited, result, graph);
    return result;
  }

  /**
   * Classic topological sorting using DFS .
   *
   * @param current
   *      current node to be traversed
   * @param isVisited
   *      map of already visited nodes
   * @param result
   *      list of transitive dependencies
   * @param graph
   *      input DependencyGraph
   */
  private void topologicalSortRecursive(String current, Map<String, Boolean> isVisited, LinkedList<String> result, DependencyGraph graph) {
    isVisited.put(current, true);
    Set<String> destSet = graph.dependenciesOf(current);
      for (String dest: destSet) {
        if (isVisited.get(dest) == null || !isVisited.get(dest))
          topologicalSortRecursive(dest, isVisited, result, graph);

    }
    result.addFirst(current);
  }

  /**
   * gets the dependency from the list of items,
   * sort the dependencies and removes any null values
   *
   * @param items
   *       List of topologically sorted items of the graph
   * @return
   *       List of dependencies for a given item
   */
  private List<String> getDependency(List<String> items) {
    List<String> dependencies = items.subList(1, items.size());
    Collections.sort(dependencies);
    dependencies.remove("");
    return dependencies;
  }

}