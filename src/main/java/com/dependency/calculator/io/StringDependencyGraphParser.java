package com.dependency.calculator.io;

import com.dependency.calculator.model.DependencyGraph;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Implementation of {@link DependencyGraphParser} specific to String Input
 *
 */
public class StringDependencyGraphParser implements DependencyGraphParser<String> {

	@Override
	public DependencyGraph parse(String graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Graph cannot be null");
		}
		if (graph.isEmpty()) {
			return new DependencyGraph(Collections.emptyMap());
		}

		Map<String, Set<String>> dependencyMap = new HashMap<>();

    /** The multiline input graph is split where the first token is dependent string and
		 *  the rest of the token are stored as a {@link Set} which correspond to the dependencies
		 *
     */
		for (String dependencyLine : graph.split("\n")) {
			List<String> items = List.of(dependencyLine.split(" "));
			String dependent = getDependent(items);
			Set<String> dependencies = Set.copyOf(getDependency(items));
			dependencyMap.put(dependent, dependencies);
		}

		return new DependencyGraph(dependencyMap);
	}

	/**
	 * gets the dependent from the list of items
	 * @param items
	 *    List of item tokens
	 * @return
	 *    The first token of the list of items is considered to be the dependent
	 */
	private String getDependent(List<String> items) {

		return items.get(0);
	}

	/**
	 * gets the dependencies from a given list of items
	 * @param items
	 *     List of item tokens
	 * @return
	 *     Items that follow the first token are considered as dependencies
	 */
	private List<String> getDependency(List<String> items) {
		return items.subList(1, items.size());
	}
}
