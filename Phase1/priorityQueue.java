/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooni
 */
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.util.Pair;

/*
Prim's Algorithm using priority queue is an algorithm use the priority queue to select the next vertex to add to the growing graph.
It is belongs to to greedy algorithms. At each step we will choose the cheapest next step.
*/

/**
 *Prim's Algorithm using priority queue is an algorithm use the priority queue to select the next vertex to add to the growing graph.
 *It is belongs to to greedy algorithms. At each step we will choose the cheapest next step.
 * 
 */

public class priorityQueue {

    // Class to define the Parent and weight for evry vertx 
    static class ResultSet {

        int parent;
        int weight;
    }
    // Consturctor of priority queue
    public void priorityQueue(int vertices, LinkedList<Edge>[] adj_list) {

        boolean[] mst = new boolean[vertices];
        Graph.ResultSet[] resultSet = new Graph.ResultSet[vertices];
        int[] key = new int[vertices];  //keys used to store the key to know whether priority queue update is required

        //Initialize all the keys to infinity and
        //initialize resultSet for all the vertices
        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new Graph.ResultSet();
        }

        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                //sort using key values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });

        //create the pair for for the first index, 0 key 0 index
        key[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
        //add it to pq
        queue.offer(p0);

        resultSet[0] = new Graph.ResultSet();
        resultSet[0].parent = -1;

        //while priority queue is not empty
        while (!queue.isEmpty()) {
            //extract the min
            Pair<Integer, Integer> extractedPair = queue.poll();

            //extracted vertex
            //delet // int extractedVertex = extractedPair.getValue();
            mst[extractedPair.getValue()] = true;

            //iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = adj_list[extractedPair.getValue()];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is not present in mst
                if (mst[edge.destination] == false) {
                    int destination = edge.destination;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        //add it to the priority queue
                        Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                        queue.offer(p);
                        //update the resultSet for destination vertex
                        resultSet[destination].parent = extractedPair.getValue();
                        resultSet[destination].weight = newKey;
                        //update the key[]
                        key[destination] = newKey;
                    }
                }
            }
        }
        printMST(vertices,resultSet);
        
        
    }
    
    // method to print the cost of the minimum spanning tree
    public void printMST(int vertices, Graph.ResultSet[] resultSet){
        //calculate cost
        int totalMinWeight = 0;
        System.out.println("------------------------Prim's using Priority Queue------------------------");
        for (int i = 1; i < vertices; i++) {
            totalMinWeight += resultSet[i].weight;
        }

        System.out.println("\nMinimum Cost Spanning Tree = " + totalMinWeight);
        
    }

}

