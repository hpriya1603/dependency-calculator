package com.dependency.calculator.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Model class representing a graph data structure using a Map.
 * Operates with Set<String> object representing graph nodes.
 *
 *
 */
public class DependencyGraph {

	/**
	 * The dependency graph, represented as a {@link Map} with
	 * key -> node name and value -> {@link Set} object for fast retrieval
	 * by node name.
	 */
	private Map<String, Set<String>> dependencyMap;

	public DependencyGraph(Map<String, Set<String>> dependencyMap) {this.dependencyMap = dependencyMap;}

	/**
	 * Set of dependencies for a given node name
	 *
	 */
	public Set<String> dependenciesOf(String dependent) {
		Set<String> dependencies = Collections.emptySet();

		if (dependencyMap.containsKey(dependent))
			dependencies = new HashSet<>(dependencyMap.get(dependent));

		return dependencies;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DependencyGraph that = (DependencyGraph) o;
		return Objects.equals(dependencyMap, that.dependencyMap);
	}

	/**
	 * Fetch all node names in the graph
	 *
	 */
	public Set<String> allDependents() {
		return new HashSet<>(dependencyMap.keySet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(dependencyMap);
	}

	@Override
	public String toString() {
		return dependencyMap.toString();
	}
}
