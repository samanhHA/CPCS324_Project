/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooni
 */
import java.util.Scanner;

public class CPCS324Project_P1 {

    public static void main(String[] args) {

        int n = 0, m = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("        -----Runtime of Different Minimum Spanning Tree Algorithms----"
                + "\n1- Kruskal's Algorithm  &  Prim's Algorithm based on Priority Queue"
                + "\n2- Prim's Algorithm based on Min Heap  &  Prim's Algorithm based on Priority Queue");
        System.out.print("-> Enter your choice 1 or 2: ");
        int choice = in.nextInt();
        System.out.println("-> Available cases are shown in the table below:");
        System.out.println(" No. |  vertex    |  edges");
        System.out.println("-------------------------------");
        System.out.println("  1  |   1,000    |  10,000");
        System.out.println("  2  |   1,000    |  15,000");
        System.out.println("  3  |   1,000    |  25,000");
        System.out.println("  4  |   5,000    |  15,000");
        System.out.println("  5  |   5,000    |  25,000");
        System.out.println("  6  |   10,000   |  15,000");
        System.out.println("  7  |   10,000   |  25,000");
        System.out.println("  8  |   20,000   |  200,000");
        System.out.println("  9  |   20,000   |  300,000");
        System.out.println("  10 |   50,000   |  10,000,000");
        System.out.print("-> Enter a case to test: ");
        int c = in.nextInt();
        System.out.println();
        switch (c) {
            case 1: {
                n = 1000;
                m = 10000;
            }
            break;
            case 2: {
                n = 1000;
                m = 15000;
            }
            break;
            case 3: {
                n = 1000;
                m = 25000;
            }
            break;
            case 4: {
                n = 5000;
                m = 15000;
            }
            break;
            case 5: {
                n = 5000;
                m = 25000;
            }
            break;
            case 6: {
                n = 10000;
                m = 15000;
            }
            break;
            case 7: {
                n = 10000;
                m = 25000;
            }
            break;
            case 8: {
                n = 20000;
                m = 200000;
            }
            break;
            case 9: {
                n = 20000;
                m = 300000;
            }
            break;
            case 10: {
                n = 50000;
                m = 1000000;
            }
            break;
            default:
                System.out.println("Invalid input..");
        }
        Graph graph = new Graph(n, m);
        graph.make_graph(graph);
        //graph.printGraph();

        switch (choice) {
            case 1:
                graph.printKruskal(graph);
                graph.printPQ(graph);
                break;

            case 2:
                graph.primMH(graph);
                graph.printPQ(graph);
                break;
            default:
                System.out.println("Invalid input..");
                break;
        }
    }

}

