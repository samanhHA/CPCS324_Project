/*
 * Rofidah Jamal
 * Samanh Ibrahim
 * Rania Hafiz
 */
package algorithm;

/**
 *
 * @author roban
 */
import java.util.*;

public class Edmond {

    static int nodes = 6;
    static int source = 0;
    static int sink = nodes - 1;
    static Node[] graph = new Node[nodes];

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {

        // Initialize each node
        for (int i = 0; i < nodes; i++) {
            graph[i] = new Node();
        }

        // Initialize each edge
        addEdge(1, 2, 2);
        addEdge(1, 3, 7);
        addEdge(2, 4, 3);
        addEdge(2, 5, 4);
        addEdge(3, 4, 4);
        addEdge(3, 5, 2);
        addEdge(4, 6, 1);
        addEdge(5, 6, 5);
        maxFlow();
        minCut();
    }

    /**
     * method to find max flow and print it for each edge
     *
     */
    public static void maxFlow() {
        System.out.println("----------Maximum Flow----------");
        int maxFlow = 0;

        while (true) {
            // Parent array used for storing path
            // (parent[i] stores edge used to get to node i)
            Edge[] parent = new Edge[nodes];

            ArrayList<Node> q = new ArrayList<>();
            q.add(graph[source]);

            // BFS finding shortest augmenting path
            while (!q.isEmpty()) {
                Node curr = q.remove(0);

                // Checks that edge has not yet been visited, and it doesn't
                // point to the source, and it is possible to send flow through it. 
                for (Edge e : curr.edges) {
                    if (parent[e.v] == null && e.v != source && e.capacity > e.flow) {
                        parent[e.v] = e;
                        q.add(graph[e.v]);
                    }
                }
            }

            // If sink was NOT reached, no augmenting path was found.
            // Algorithm terminates and prints out max flow.
            if (parent[sink] == null) {
                break;
            }

            String path = "";

            // If sink WAS reached, we will push more flow through the path
            int pushFlow = Integer.MAX_VALUE;

            // Finds maximum flow that can be pushed through given path
            // by finding the minimum residual flow of every edge in the path
            for (Edge e = parent[sink]; e != null; e = parent[e.u]) {
                pushFlow = Math.min(pushFlow, e.capacity - e.flow);

                String direction = "<-";
                if (e.capacity != 0) {
                    direction = "->";
                }
                path = direction + (e.v + 1) + path;
            }
            path = 1 + path;
            System.out.printf("%-17s \n", path);
            System.out.print("flow: " + pushFlow);

            // Adds to flow values and subtracts from reverse flow values in path
            for (Edge e = parent[sink]; e != null; e = parent[e.u]) {
                e.flow += pushFlow;
                e.reverse.flow -= pushFlow;
            }

            maxFlow += pushFlow;
            System.out.println("  after update: " + maxFlow);
        }
        System.out.println("\n-The maximum possible flow is " + maxFlow);

        System.out.println();
        System.out.println();

    }

    /**
     * method of mincut to find and print mincut for each edge
     *
     */
    public static void minCut() {
        System.out.println("-------------Mincut-------------");

        // print min-cut edges
        boolean[] isVisited = new boolean[graph.length];
        dfs(graph, source, isVisited);
        // Print all edges that are from a reachable vertex to 
        // non-reachable vertex in the original graph
        int totalCut = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].edges.size(); j++) {
                if (graph[i].edges.get(j).capacity > 0 && isVisited[i] && !isVisited[graph[i].edges.get(j).u]) {
                    System.out.println("Edge " + (graph[i].edges.get(j).u+1) + "," + (graph[i].edges.get(j).v + 1));
                    System.out.print("Capacity: " + graph[i].edges.get(j).reverse.capacity);
                    totalCut += graph[i].edges.get(j).reverse.capacity;
                    System.out.println("    after update : " + totalCut);
                }
            }
        }
        System.out.println("\n-Total mincut capacity is " + totalCut);

    }

    /**
     * DFS method helps to find the min-cut
     *
     * @param graph
     * @param s
     * @param visited
     */
    static void dfs(Node[] graph, int s, boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[s].edges.size(); j++) {
                if (graph[s].edges.get(j).reverse.capacity > 0 && !visited[graph[s].edges.get(j).v]) {
                    dfs(graph, j, visited);
                }
            }
        }
    }

    /**
     * method to add Edge
     *
     * @param u
     * @param v
     * @param c
     */
    public static void addEdge(int u, int v, int c) {
        // decrese, to start from zero
        u--;
        v--;

        // Note edge "b" is not actually in the input graph
        // It is a construct that allows us to solve the problem
        Edge a = new Edge(u, v, 0, c);
        Edge b = new Edge(v, u, 0, 0);

        // Set pointer from each edge "a" to
        // its reverse edge "b" and vice versa
        a.setReverse(b);
        b.setReverse(a);

        graph[u].edges.add(a);
        graph[v].edges.add(b);

    }
}

class Node {

// List of edges also includes reverse edges that
// are not in original given graph (for push-back flow)
    ArrayList<Edge> edges = new ArrayList<>();

}

class Edge {

    int u, v, flow, capacity;
    Edge reverse;

    public Edge(int u, int v, int flow, int capacity) {
        this.u = u;
        this.v = v;
        this.flow = flow;
        this.capacity = capacity;
    }

    public void setReverse(Edge e) {
        reverse = e;
    }
}
