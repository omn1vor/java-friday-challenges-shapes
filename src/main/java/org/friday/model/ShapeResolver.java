package org.friday.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShapeResolver {
    private final List<Shape> shapes;
    private Map<Shape, List<Shape>> graph;


    public ShapeResolver(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public boolean isSingleConnectedShape() {
        if (shapes.isEmpty()) {
            return false;
        }

        buildGraph();
        return graphIsConnected();
    }

    private void buildGraph() {
        graph = shapes.stream()
                .collect(Collectors.toMap(Function.identity(), ignored -> new ArrayList<>()));

        for (int i = 0; i < shapes.size(); i++) {
            for (int j = i + 1; j < shapes.size(); j++) {
                Shape first = shapes.get(i);
                Shape second = shapes.get(j);

                if (first.intersects(second)) {
                    graph.get(first).add(second);
                    graph.get(second).add(first);
                }
            }
        }
    }

    private boolean graphIsConnected() {
        Set<Shape> visited = new HashSet<>();
        Queue<Shape> queue = new ArrayDeque<>();
        queue.add(shapes.get(0));

        while (!queue.isEmpty()) {
            Shape shape = queue.poll();
            visited.add(shape);
            for (Shape neighbor : graph.get(shape)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                queue.add(neighbor);
            }
        }

        return visited.size() == shapes.size();
    }
}
