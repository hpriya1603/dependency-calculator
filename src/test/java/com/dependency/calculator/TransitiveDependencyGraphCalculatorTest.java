package com.dependency.calculator;

import com.dependency.calculator.model.DependencyGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitiveDependencyGraphCalculatorTest {
	TransitiveDependencyGraphCalculator calculator = new TransitiveDependencyGraphCalculator();

	@Test
	void calculateNothingAsItself() {
		DependencyGraph graph = new DependencyGraph(Collections.emptyMap());

		assertThat(calculator.calculateDependencies(graph).isEmpty());
	}

	@Test
	void calculateOneStepOfTransitiveDependencies() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Set.of("B"));
		dependencyMap.put("B", Set.of("C"));
		DependencyGraph input = new DependencyGraph(dependencyMap);

		Map<String, List<String>> actual = calculator.calculateDependencies(input);

		Map<String, List<String>> expectedMap = new HashMap<>();
		expectedMap.put("A", Arrays.asList("B", "C"));
		expectedMap.put("B", Arrays.asList("C"));

		assertThat(actual).isEqualTo(expectedMap);
	}

	@Test
	void calculateOneStepOfTransitiveDependenciesMultipleTimes() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Set.of("B", "D"));
		dependencyMap.put("B", Set.of("C"));
		dependencyMap.put("D", Set.of("E"));
		DependencyGraph input = new DependencyGraph(dependencyMap);

		Map<String, List<String>> actual = calculator.calculateDependencies(input);

		Map<String, List<String>> expectedMap = new HashMap<>();
		expectedMap.put("A", Arrays.asList("B", "C", "D", "E"));
		expectedMap.put("B", Arrays.asList("C"));
		expectedMap.put("D", Arrays.asList("E"));

		assertThat(actual).isEqualTo(expectedMap);
	}

	@Test
	void calculateMultipleStepsOfTransitiveDependencies() {
		Map<String, Set<String>> dependencyMap = new HashMap<>();
		dependencyMap.put("A", Set.of("B"));
		dependencyMap.put("B", Set.of("C"));
		dependencyMap.put("C", Set.of("D"));
		DependencyGraph input = new DependencyGraph(dependencyMap);

		Map<String, List<String>> actual = calculator.calculateDependencies(input);

		Map<String, List<String>> expectedMap = new HashMap<>();
		expectedMap.put("A", Arrays.asList("B", "C", "D"));
		expectedMap.put("B", Arrays.asList("C", "D"));
		expectedMap.put("C", Arrays.asList("D"));

		assertThat(actual).isEqualTo(expectedMap);
	}

	@Test
	void calculateCircularDependencies() {
		Map<String, Set<String>> inputMap = new HashMap<>();
		inputMap.put("A", Set.of("B"));
		inputMap.put("B", Set.of("C"));
		inputMap.put("C", Set.of("A"));
		DependencyGraph input = new DependencyGraph(inputMap);

		Map<String, List<String>> actual = calculator.calculateDependencies(input);

		Map<String, List<String>> expectedMap = new HashMap<>();
		expectedMap.put("A", Arrays.asList("B", "C"));
		expectedMap.put("B", Arrays.asList("A", "C"));
		expectedMap.put("C", Arrays.asList("A", "B"));

		assertThat(actual).isEqualTo(expectedMap);
	}
}