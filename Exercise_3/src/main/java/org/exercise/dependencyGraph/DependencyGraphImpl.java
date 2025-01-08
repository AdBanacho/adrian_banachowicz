package org.exercise.dependencyGraph;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DependencyGraphImpl implements DependencyGraph {

    private final Map<String, Set<String>> graph;

    public DependencyGraphImpl() {
        this.graph = new HashMap<>();
    }

    @Override
    public Map<String, Set<String>> getResolvedGraph(String fileName) throws IOException {
        loadFromJson(fileName);
        return resolveGraph();
    }

    @Override
    public String getPrettyResolvedGraph(String fileName) throws IOException {
        Map<String, Set<String>> resolvedGraph =  getResolvedGraph(fileName);
        return resolveGraphToPrint(resolvedGraph);
    }

    private void loadFromJson(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        String content = Files.readString(path);
        JSONObject json = new JSONObject(content);

        json.keySet().forEach(key -> {
            Set<String> dependencies = json.getJSONArray(key)
                    .toList()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toCollection(HashSet::new));
            graph.put(key, dependencies);
        });
    }

    private Map<String, Set<String>> resolveGraph() {
        Map<String, Set<String>> resolvedGraph = new HashMap<>();
        graph.keySet().forEach(key ->
                resolvedGraph.put(key, resolveDependencies(key, new HashSet<>()))
        );

        return resolvedGraph;
    }

    private Set<String> resolveDependencies(String key, Set<String> visited) {
        Set<String> resolvedGraph = new LinkedHashSet<>();
        // If the node has already been visited, return an empty set to avoid cycles.
        if (visited.contains(key)) {
            return resolvedGraph;
        }
        visited.add(key);
        // Recursively resolve the dependencies of each direct dependency (DFS)
        // A new visited set is created for each recursive call to track nodes in the current DFS path
        graph.getOrDefault(key, Collections.emptySet())
                .forEach(dependency -> {
                    resolvedGraph.add(dependency);
                    resolvedGraph.addAll(resolveDependencies(dependency, new HashSet<>(visited)));
                });

        return resolvedGraph;
    }

    private String resolveGraphToPrint(Map<String, Set<String>> resolvedGraph) {
        StringBuilder output = new StringBuilder();

        resolvedGraph.keySet().forEach(root -> {
            String node = resolveDependenciesToPrint(root, resolvedGraph, new HashSet<>(), "-");
            output.append(node).append("\n");
        });

        return output.toString();
    }

    private String resolveDependenciesToPrint(String node, Map<String, Set<String>> resolvedGraph, Set<String> visited, String indent) {
        // If a circular dependency is detected, mark it
        if (visited.contains(node)) {
            return indent + node + " circular ->";
        }
        visited.add(node);
        StringBuilder builder = new StringBuilder(indent).append(node).append("\n");
        Set<String> dependencies = resolvedGraph.getOrDefault(node, Collections.emptySet());
        String deeperIndent = indent + "|   ";
        // Recursively resolve the dependencies of each direct dependency (DFS)
        // A new visited set is created for each recursive call to track nodes in the current DFS path
        dependencies.forEach(dependency -> {
            String newNode = resolveDependenciesToPrint(dependency, resolvedGraph, new HashSet<>(visited), deeperIndent);
            builder.append(newNode);
        });

        return builder.toString();
    }
}