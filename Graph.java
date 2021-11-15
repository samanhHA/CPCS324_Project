/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooni
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rooni
 */
public class Graph {

    //decliration vertices, edges
    int vertices;
    int edges;

    // An adjacency list to represent a graph
    LinkedList<Edge>[] adj_list;
    // allEdges Arraylist is used to sort it in Kruskal algorithm 
    ArrayList<Edge> allEdges;

    Graph(int vertices, int edges) {

        this.vertices = vertices;
        this.edges = edges;
        adj_list = new LinkedList[vertices];
        allEdges = new ArrayList<>(vertices);
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adj_list[i] = new LinkedList<>();
        }
    }

    public void make_graph(Graph graph) {

        // use random class to randomly generat edges
        Random random = new Random();
        // ensure that all vertices are connected with v-1 edges connecting all vertcies
        for (int i = 0; i < vertices - 1; i++) {
            int weight = random.nextInt(20) + 1;
            addEdge(i, i + 1, weight);

        }

        // generate random graph with the remaining edges
        int remaningEdges = edges - (vertices - 1);
        // for loop to generate random edges
        for (int i = 0; i < remaningEdges;) {
            // Randomly select two vertices (v,u) to create an edge between them
            int source = random.nextInt(graph.vertices);
            int dest = random.nextInt(graph.vertices);
            if (dest == source || isConnected(source, dest, graph.adj_list)) { // check for self loops and existed edges

                continue;
            }

            i++;
            // generate random weights in range 0 to 20
            int weight = random.nextInt(20) + 1;
            // add edge to the graph as therer is no self loops or existed edges
            addEdge(source, dest, weight);

        }

    }

    /*
        
        **************************************************************************
        This function is not being used, it is only for test reason 
        **************************************************************************
        
       public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> list = adj_list[i];
            for (int j = 0; j < list.size(); j++) {
                System.out.println("vertex-" + i + " is connected to "
                        + list.get(j).destination + " with weight " + list.get(j).weight);
            }
        }
    }*/
    // checks if the edge is already existed
    public boolean isConnected(int src, int dest, LinkedList<Edge>[] edges) {
        for (LinkedList<Edge> i : edges) {
            for (Edge edge : i) {
                if ((edge.source == src && edge.destination == dest) || (edge.source == dest && edge.destination == src)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to add edges between source, destination
    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adj_list[source].addFirst(edge);

        Edge edge1 = new Edge(destination, source, weight);
        adj_list[destination].addFirst(edge1); //for undirected graph
        allEdges.add(edge);
        allEdges.add(edge1);

    }

    // Class to define the Parent and weight for evry vertx 
    static class ResultSet{

        int parent;
        int weight;
    }

    /*
        
        **************************************************************************
        The code of Prim's Algortithm using min-heap starts from here
        **************************************************************************
        
     */
    public void primMH(Graph graph) {
        long startTime_minHeap, endTime_minHeap;
        startTime_minHeap = System.nanoTime();
        graph.minheap_MST();
        endTime_minHeap = System.nanoTime();
        long estmateTime_minHeap = endTime_minHeap - startTime_minHeap;
        System.out.println("-> Estimated Time for MinHeap Prim : " + TimeUnit.NANOSECONDS.toMillis(estmateTime_minHeap) + " millisecond\n\n");

    }

    public void printMST_minHeap(ResultSet[] resultSet) {
        int total_min_weight = 0;

        for (int i = 1; i < vertices; i++) {
            total_min_weight += resultSet[i].weight;
        }

        System.out.println("--------------------------Prim's using Min Heap---------------------------\n"
                + "\nMinimum Cost Spanning Tree = " + total_min_weight);
    }

    //to update weight 
    public void decreaseKey(MinHeap minHeap, int newWeight, int vertex) {

        int index = minHeap.indexes[vertex]; //index needs to decrese the weight
        HeapNode node = minHeap.minheap[index]; //update value of the node
        node.wieght = newWeight;
        minHeap.bubbleUp(index);
    }

    public void minheap_MST() {

        boolean[] inHeap = new boolean[vertices];
        ResultSet[] results = new ResultSet[vertices];
        int[] Wieghts = new int[vertices];
        HeapNode[] heapNodes = new HeapNode[vertices];

        //initializing items in arrays 
        for (int i = 0; i < vertices; i++) {

            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].wieght = Integer.MAX_VALUE; //all vertices except the first are given value of infinity
            results[i] = new ResultSet();
            results[i].parent = -1; //vertex with no parent 
            inHeap[i] = true; //weather vertex exist in heap or not 
            Wieghts[i] = Integer.MAX_VALUE; //weights also given infinity value 
        }

        heapNodes[0].wieght = 0; //first node is given value of zero to its weight

        MinHeap minHeap = new MinHeap(vertices); //initializing Min-Heap 
        for (int i = 0; i < vertices; i++) {
            minHeap.insert(heapNodes[i]); //using the insert function to insert nodes in minheap
        }

        //extract the root while the minheap is not empty
        while (!minHeap.isEmpty()) {
            HeapNode extractedNode = minHeap.extractMin();

            int extractedVertex = extractedNode.vertex;
            inHeap[extractedVertex] = false; //vertex had been removed 

            //create linkedlist with the values of extracted vertex edges  
            LinkedList<Edge> list = adj_list[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);

                //check if destination exist first
                if (inHeap[edge.destination]) {
                    int x = edge.destination;
                    int newWeight = edge.weight;
                    //update weight if it differ 
                    if (Wieghts[x] > newWeight) {

                        decreaseKey(minHeap, newWeight, x);
                        //update parent, weight of destination 
                        results[x].parent = extractedVertex;
                        results[x].weight = newWeight;
                        Wieghts[x] = newWeight;
                    }
                }
            }
        }

        printMST_minHeap(results);
    }


    /*
        
        **************************************************************************
        The code of Prim's Algortithm using min-heap ENDS here
        **************************************************************************
        
    
     */
    
    // call the code of Prim's Algorithm using priority queue
    public void printPQ(Graph graph) {
        long startTimePQ, endTimePQ;
        startTimePQ = System.nanoTime();
        priorityQueue prq = new priorityQueue();
        prq.priorityQueue(graph.vertices, graph.adj_list);
        endTimePQ = System.nanoTime();
        long estmateTimePQ = endTimePQ - startTimePQ;
        System.out.println("-> Estimated Time for prim prioirty queue: " + TimeUnit.NANOSECONDS.toMillis(estmateTimePQ) + " millisecond\n\n");
    }

    // call the code of kruskal
    public void printKruskal(Graph graph) {
        long startTimeK, endTimeK;
        startTimeK = System.nanoTime();
        kruskal kruskal = new kruskal();
        kruskal.KruskalMST(allEdges, vertices);
        endTimeK = System.nanoTime();
        long estmateTimeK = endTimeK - startTimeK;
        System.out.println("-> Estimated Time for Kroskal: " + TimeUnit.NANOSECONDS.toMillis(estmateTimeK) + " millisecond\n\n");
    }
}

