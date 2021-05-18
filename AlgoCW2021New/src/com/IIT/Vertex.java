package com.IIT;

import java.util.ArrayList;

public class Vertex {

    // ATTRIBUTES
    private final int VERTEX_NUMBER;
    private final ArrayList<Edge> setOfEdges;

    // CONSTRUCTOR
    public Vertex(int vertexNumber) {
        this.VERTEX_NUMBER = vertexNumber;
        setOfEdges = new ArrayList<>();
    }

    // CLASS METHODS
    public void addLinkedVertex(int head, int tail, int capacity) {
        setOfEdges.add(new Edge(head, tail, capacity));
    }

    public String printAllEdges() {
        if (setOfEdges.isEmpty()) {
            return " -> null";
        } else {
            StringBuilder str = new StringBuilder();
            for (Edge edge : setOfEdges) {
                str.append(" -> [").append(edge.to()).append(" (").append(edge.capacity()).append(")]");
            }
            return str.toString();
        }
    }

    public int getVertexNumber() {
        return VERTEX_NUMBER;
    }

    public ArrayList<Edge> getEdges() {
        return setOfEdges;
    }
}
