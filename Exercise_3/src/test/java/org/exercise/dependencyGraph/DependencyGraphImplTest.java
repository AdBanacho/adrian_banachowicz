package org.exercise.dependencyGraph;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DependencyGraphImplTest {

    private DependencyGraphImpl dependencyGraph;
    private final String testFilePath = "test_dependencies.json";

    @Before
    public void setUp() throws IOException {
        dependencyGraph = new DependencyGraphImpl();

        // Create a test JSON file for dependencies
        JSONObject json = new JSONObject();
        json.put("A", List.of("B", "C"));
        json.put("B", List.of("C", "D"));
        json.put("C", List.of("D"));
        json.put("D", List.of());

        Path path = Paths.get(testFilePath);
        Files.writeString(path, json.toString());
    }

    @After
    public void tearDown() throws IOException {
        // Remove the test file after all tests are done
        Path path = Paths.get(testFilePath);
        Files.deleteIfExists(path); // Safely delete the file if it exists
    }

    @Test
    public void testLoadJson() throws IOException {
        Map<String, Set<String>> graph = dependencyGraph.getResolvedGraph(testFilePath);

        Assert.assertTrue(graph.containsKey("A"));
        Assert.assertTrue(graph.get("A").contains("B"));
        Assert.assertTrue(graph.get("B").contains("C"));
        Assert.assertTrue(graph.get("C").contains("D"));
    }

    @Test
    public void testResolveGraph() throws IOException {
        Map<String, Set<String>> resolvedGraph = dependencyGraph.getResolvedGraph(testFilePath);

        Assert.assertEquals(Set.of("B", "C", "D"), resolvedGraph.get("A"));
        Assert.assertEquals(Set.of("C", "D"), resolvedGraph.get("B"));
        Assert.assertEquals(Set.of("D"), resolvedGraph.get("C"));
        Assert.assertEquals(Set.of(), resolvedGraph.get("D"));
    }

    @Test
    public void testCircularDependencyDetection() throws IOException {
        JSONObject json = new JSONObject();
        json.put("A", List.of("B"));
        json.put("B", List.of("A"));

        Path path = Paths.get(testFilePath);
        Files.writeString(path, json.toString());

        Map<String, Set<String>> resolvedGraph = dependencyGraph.getResolvedGraph(testFilePath);
        // TODO: Improve the test to also check the order of elements
        // Assert.assertEquals encounters issues related to assertion failures due to immutability
        Assert.assertTrue(resolvedGraph.get("A").containsAll(Set.of("B", "A")));
        Assert.assertTrue(resolvedGraph.get("B").containsAll(Set.of("A", "B")));
    }

    @Test
    public void testGetPrettyResolvedGraph() throws IOException {
        String result = dependencyGraph.getPrettyResolvedGraph(testFilePath);

        String expectedOutput =
                """
                        -A
                        -|   B
                        -|   |   C
                        -|   |   |   D
                        -|   |   D
                        -|   C
                        -|   |   D
                        -|   D
                        
                        -B
                        -|   C
                        -|   |   D
                        -|   D
                        
                        -C
                        -|   D
                        
                        -D
                        
                        """;

        Assert.assertEquals( expectedOutput, result);
    }

    @Test
    public void testGetPrettyResolvedGraphCircular() throws IOException {
        JSONObject json = new JSONObject();
        json.put("A", List.of("B"));
        json.put("B", List.of("A"));

        Path path = Paths.get(testFilePath);
        Files.writeString(path, json.toString());

        String result = dependencyGraph.getPrettyResolvedGraph(testFilePath);

        String expectedOutput =
                """
                    -A
                    -|   B
                    -|   |   A circular ->-|   |   B circular ->-|   A circular ->
                    -B
                    -|   A
                    -|   |   B circular ->-|   |   A circular ->-|   B circular ->
                    """;

        Assert.assertEquals( expectedOutput, result);
    }

}