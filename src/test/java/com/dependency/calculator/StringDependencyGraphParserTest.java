package com.dependency.calculator;

import com.dependency.calculator.io.StringDependencyGraphParser;
import com.dependency.calculator.model.DependencyGraph;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringDependencyGraphParserTest {
	private StringDependencyGraphParser parser = new StringDependencyGraphParser();

	@Test
	void throwsExceptionWhenGivenNullString() {
		assertThatThrownBy(() -> parser.parse(null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Graph cannot be null");
	}

	@Test
	void parsesEmptyString() {
		DependencyGraph expected = new DependencyGraph(Collections.emptyMap());

		DependencyGraph actual = parser.parse("");

		assertThat(actual).isEqualTo(expected);
	}


	@Test
	void parsesSingleLineGraphWithSingleDependency() {
		Map<String, Set<String>> expectedDependencyMap = new HashMap<>();
		expectedDependencyMap.put("A", Set.of("B"));
		DependencyGraph expected = new DependencyGraph(expectedDependencyMap);

		DependencyGraph actual = parser.parse("A B");

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void parsesSingleLineGraphWithMultipleDependencies() {
		Map<String, Set<String>> expectedDependencyMap = new HashMap<>();
		expectedDependencyMap.put("A", Set.of("B", "C"));
		DependencyGraph expected = new DependencyGraph(expectedDependencyMap);

		DependencyGraph actual = parser.parse("A B C");

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void parsesMultiLineGraphEachWithSingleDependency() {
		Map<String, Set<String>> expectedDependencyMap = new HashMap<>();
		expectedDependencyMap.put("A", Set.of("B"));
		expectedDependencyMap.put("B", Set.of("C"));
		DependencyGraph expected = new DependencyGraph(expectedDependencyMap);

		DependencyGraph actual = parser.parse("A B\nB C");

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void parsesMultiLineGraphEachWithMultipleDependency() {
		Map<String, Set<String>> expectedDependencyMap = new HashMap<>();
		expectedDependencyMap.put("A", Set.of("B", "C"));
		expectedDependencyMap.put("B", Set.of("C", "D"));
		DependencyGraph expected = new DependencyGraph(expectedDependencyMap);

		DependencyGraph actual = parser.parse("A B C\nB C D");

		assertThat(actual).isEqualTo(expected);
	}
}