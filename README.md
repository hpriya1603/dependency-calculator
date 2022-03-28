Dependency Calculator
=====================

This application provides the full set of transitive dependencies for a group of items.

The input is a set of lines where the first token is the name of an item.
The remaining tokens are the names of things that this first item depends on, in the following format

```
A B C
B C E
C G
D A F
E F
F H
```

### Approach

- Write a program that accepts multi-line string as input through command line argument.
- Convert the input into a Graph, where the first token is the vertex and the remaining are edges.
- To obtain the transitive dependency modify the DFS algorithm to generate a topological sort of the Graph.
  During the DFS traversal, after all neighbors of a vertex u are visited, we then put it to the front of the result list.
- This algorithm is similar to the standard DFS algorithm. Although it starts with a random vertex, it doesn’t put the vertex into the list immediately once it visits it.
  Instead, it first visits all its neighbors recursively and then put the vertex to the front of the list. 
  Therefore, if G contains an edge (u, v), then u appears before v in the list (but the output doesnt appear edge wise - refer assumption no.3).
- The overall running time is also O(V+E), as it has the same time complexity as the DFS algorithm.
- This list is mapped to a HashMap for easier printing.     

### Assumptions

- The input is of type String.
- The input is a single multi-line String.
- Although topological sorting algorithm is used, the final output is sorted conventionally(alphabetically),
  so that the output is in accordance with the expected output mentioned in the problem statement.

### Specifications 

- Java 8
- Junit 5.4.2
- Gradle 5.5.1

### Installation

To run this application, run the following commands in the command prompt/terminal, from the root folder of the project

```
./gradlew clean && ./gradlew build
```

```
./gradlew runCalc -Pargs="A  B  C
B  C  E
C  G
D  A  F
E  F
F  H"
```
**Note:**  The multiline input String to be given between `""` in `-Pargs=`
