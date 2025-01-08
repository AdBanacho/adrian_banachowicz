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
    public String getPrettyResolvedGraph(String fileName) {
        return null;
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
                    .collect(Collectors. toCollection(HashSet::new));
            graph.put(key, dependencies);
        });
    }

    private Map<String, Set<String>> resolveGraph() {
        Map<String, Set<String>> resolved = new HashMap<>();
        for (String key : graph.keySet()) {
            resolved.put(key, resolveDependencies(key, new HashSet<>()));
        }
        return resolved;
    }

    private Set<String> resolveDependencies(String key, Set<String> visited) {
        Set<String> resolved = new LinkedHashSet<>();
        if (!visited.contains(key)) {
            visited.add(key);
            graph.getOrDefault(key, Collections.emptySet())
                    .forEach(dependency -> {
                        resolved.add(dependency);
                        resolved.addAll(resolveDependencies(dependency, new HashSet<>(visited)));
                    });
        }
        return resolved;
    }
}
