package com.IIT;

public class Edge {

    // ATTRIBUTES
    private final int CAPACITY;
    private final int HEAD;
    private final int TAIL;
    private int flow;

    // CONSTRUCTOR
    public Edge(int head, int tail, int capacity) {
        HEAD = head;
        TAIL = tail;
        this.CAPACITY = capacity;
        flow = 0;
    }

    // CLASS METHODS
    public int residualCapTo() {
        return CAPACITY - flow;
    }
    public void addResidualFlow(int partialFlow) {
        flow += partialFlow;
    }
    public int capacity() {
        return CAPACITY;
    }
    public int flow() {
        return flow;
    }
    public int from() {
        return HEAD;
    }
    public int to() {
        return TAIL;
    }
    public void resetFlow() { flow = 0; }
}