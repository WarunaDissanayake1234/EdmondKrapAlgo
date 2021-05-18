package com.IIT;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class PureEdmondsKarp {

    // ATTRIBUTES
    private boolean[] visited;
    private Edge[] edgeTo;
    private int result = 0;
    Instant start, end;
    double duration;

    // CONSTRUCTOR
    public PureEdmondsKarp(Digraph digraph) {
        // Log starting time
        start = Instant.now();
        System.out.println("...\nCalculating Maximum Flow...");
        int s = digraph.getSourceVertex();
        int t = digraph.getSinkVertex();

        // As long as there is augmenting path from source to sink
        while (breadthFirstSearch(digraph, s, t)) {

            int minCapacity = Integer.MAX_VALUE;
            // Find minimum capacity in the path
            for (int v = t; v != s; v = edgeTo[v].from()) {
                minCapacity = Math.min(minCapacity, edgeTo[v].residualCapTo());
            }
            // Add to the flow
            for (int v = t; v != s; v = edgeTo[v].from()) {
                edgeTo[v].addResidualFlow(minCapacity);
            }
            result += minCapacity;
        }
        end = Instant.now();
        duration = ChronoUnit.MILLIS.between(start, end) / 1000.0;
        System.out.print("Execution time: " + duration + " seconds");
    }

    // CLASS METHODS
    public boolean breadthFirstSearch(Digraph digraph, int source, int sink) {

        // Initialize variables
        visited = new boolean[digraph.getSize()];
        edgeTo = new Edge[digraph.getSize()];
        Queue<Integer> queue = new LinkedList<>();
        // Begin with source Vertex
        visited[source] = true;
        queue.add(source);
        // Go to Vertex from the queue
        while (!queue.isEmpty())  {

            int head = queue.remove();
            // Explore all Edges from this Vertex
            for (Edge edge : digraph.getVertex(head).getEdges()) {
                int tail = edge.to();
                if (edge.residualCapTo() > 0 && !visited[tail]) {
                    edgeTo[tail] = edge;
                    visited[tail] = true;
                    queue.add(tail);
                }
            }
        }
        // Has augmenting path to sink?
        return visited[sink];
    }

    // CLASS METHODS
    public int getResult() {
        return result;
    }
}
