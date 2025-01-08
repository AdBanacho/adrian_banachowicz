package org.exercise.dependencyGraph;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Interface representing a Dependency Graph.
 * Provides methods to resolve and represent dependency graphs in various formats.
 */
public interface DependencyGraph {

    /**
     * Resolves the dependency graph from the given file and returns a map of resolved dependencies
     *
     * @param fileName the name of the file containing the dependency definitions in JSON format
     * @return a map of strings representing the resolved dependencies for each package in the graph
     * @throws java.io.IOException if the file cannot be read or parsed
     */
    Map<String, Set<String>> getResolvedGraph(String fileName) throws IOException;

    /**
     * Resolves the dependency graph from the given file and returns a pretty-printed representation
     *
     * @param fileName the name of the file containing the dependency definitions in JSON format
     * @return a string representing the pretty-printed, resolved dependency graph
     * @throws java.io.IOException if the file cannot be read or parsed
     */
    String getPrettyResolvedGraph(String fileName) throws IOException;

}
