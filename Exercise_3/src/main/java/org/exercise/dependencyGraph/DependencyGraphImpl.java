package org.exercise.dependencyGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DependencyGraphImpl implements DependencyGraph {

    private final Map<String, Set<String>> graph;

    public DependencyGraphImpl() {
        this.graph = new HashMap<>();
    }

    @Override
    public Map<String, Set<String>> getResolvedGraph(String fileName) {
        return null;
    }

    @Override
    public String getPrettyResolvedGraph(String fileName) {
        return null;
    }


}
