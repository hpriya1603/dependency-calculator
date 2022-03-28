package com.dependency.calculator.io;

import com.dependency.calculator.model.DependencyGraph;

public interface DependencyGraphParser<T> {
	DependencyGraph parse(T graph);
}
