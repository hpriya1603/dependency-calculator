package com.dependency.calculator;

import com.dependency.calculator.model.DependencyGraph;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencyGraphTest {

	@Test
	void returnsRequestedDependency() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Set.of("B"));

		DependencyGraph graph = new DependencyGraph(dependencyMap);

		Set<String> dependenciesOfA = graph.dependenciesFor("A");

		assert(dependenciesOfA).contains("B");
	}

	@Test
	void twoGraphsWithTheSameContentAreEqual() {
		Map<String, Set<String>> dependencyMap1 = new HashMap<>();
		dependencyMap1.put("A", Set.of("B"));

		Map<String, Set<String>> dependencyMap2 = new LinkedHashMap<>();
		dependencyMap2.put("A", Set.of("B"));

		DependencyGraph graph1 = new DependencyGraph(dependencyMap1);
		DependencyGraph graph2 = new DependencyGraph(dependencyMap2);

		assertThat(graph1).isEqualTo(graph2);
	}

	@Test
	void returnsAllDependents() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Collections.emptySet());
		dependencyMap.put("B", Collections.emptySet());
		dependencyMap.put("C", Collections.emptySet());

		DependencyGraph graph = new DependencyGraph(dependencyMap);

		assertThat(graph.allDependents()).containsExactly("A", "B", "C");
	}

	@Test
	void returnsNothingForNonDependent() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Set.of("B"));

		DependencyGraph graph = new DependencyGraph(dependencyMap);

		assertThat(graph.dependenciesFor("B")).isEmpty();
	}
}