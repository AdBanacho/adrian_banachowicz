package org.exercise.dependencyGraph;

/**
 * Factory for creating instances of {@link DependencyGraph}.
 */
public final class DependencyGraphFactory {

    private DependencyGraphFactory(){
    }

    /**
     * Creates an instance of {@link DependencyGraph}.
     *
     * @return the new instance
     */
    public static DependencyGraph createDependencyGraph(){
        return new DependencyGraphImpl();
    }
}
