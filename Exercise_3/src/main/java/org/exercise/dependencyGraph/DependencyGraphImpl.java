package org.exercise.dependencyGraph;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
}
