/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooni
 */


import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class FloydWorshall {

   static double startTime;
    static int v=10;
    final static int inf = 9999999;//indicating ti infinity
    static char[] alphabits = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','K','R','S','T','U','V','W','X','Y','Z'};
    /////////////////////////////////////////////////////////////////////////////

    // printing the alphabetprints horizontally
    static void printAlphabits() {
        System.out.println();

        System.out.printf(" %-5s", " ");

        for (int i = 0; i < v; i++) {
            System.out.printf(" %-5c", alphabits[i]);
        }
        System.out.println();
        System.out.println();
    }
    //////////////////////////////////////////////////////////////////////////////

    // This function prints matrix 
    static void printMatrix(int dist[][]) {
        for (int i = 0; i < v; ++i) {
            //prints the alphabet vertically
            System.out.printf(" %-5c", alphabits[i]);
            for (int j = 0; j < v; ++j) {
                if (dist[i][j] == inf) {
                    System.out.printf(" %-5s", "inf");
                } else {
                    System.out.printf(" %-5d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////

    //Floyd's Algorithm
    static void Floyd(int graph[][]) {
        startTime=System.currentTimeMillis();

        int i, j, k;// initialize i,j,k
  System.out.println("Floyd's algorithm implementation \n");
          System.out.println("Weighted matrix: ");
        printAlphabits();
        printMatrix(graph);
        
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Intermediate matrix for evry iteration:");
        
        //----------------------------------------------------------
        // k is an intermediate vertex in shortest path from i to j.
        for (k = 0; k < v; k++) {

            // one by one pick all vertices as source
            for (i = 0; i < v; i++) {

                for (j = 0; j <v; j++) {
                    //We update the value of graph[i][j] as graph[i][k] + graph[k][j] if graph[i][j] > graph[i][k] + graph[k][j]
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }

            if (k == 9) //last steps
            {
                System.out.println("\n\nThe following matrix shows the shortest distances between every pair of vertices");
            }
          
            System.out.println("\n D " + (k + 1) + ": \n" + "_________________________________________________________________");

            printAlphabits();
            //print solution
            printMatrix(graph);
        }
    }

    public static void main(String[] args) {
        // initialize matrix based on graph 
        int graph[][] = {
            {0, 10, inf, inf, inf, 5, inf, inf, inf, inf},//A
            {inf, 0, 3, inf, 3, inf, inf, inf, inf, inf},//B
            {inf, inf, 0, 4, inf, inf, inf, 5, inf, inf},//C
            {inf, inf, inf, 0, inf, inf, inf, inf, 4, inf},//D
            {inf, inf, 4, inf, 0, inf, 2, inf, inf, inf},//E
            {inf, 3, inf, inf, inf, 0, inf, inf, inf, 2},//F
            {inf, inf, inf, 7, inf, inf, 0, inf, inf, inf},//G
            {inf, inf, inf, 4, inf, inf, inf, 0, 3, inf},//H
            {inf, inf, inf, inf, inf, inf, inf, inf, 0, inf},//I
            {inf, 6, inf, inf, inf, inf, 8, inf, inf, 0},//J
        };
        
         int graph2[][] = {
            {0,3,4,5,1,inf, inf, inf,inf, inf, inf,inf, inf,inf,inf},//A
            {inf,0,inf, inf, inf,inf, inf, 2,inf, inf,inf, inf,inf,inf,inf},//B
            {inf, inf, 0, inf, inf,7, inf, inf,inf, inf,inf,inf,inf,inf,inf},//C
           {inf, inf, inf, 0, inf,inf, 6, inf,inf, inf,inf,inf,inf,inf,inf},
           {inf, inf, inf, inf, 0,inf, inf, inf,inf, inf,inf,3,inf,inf,inf},
           {inf, inf, inf, inf, inf,0, inf, inf,inf, 2,inf,4,inf,inf,inf},
           {inf, inf, inf, inf, inf,inf, 0, inf,inf, 10,5,inf,inf,inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, 0,11, inf,inf,inf,inf,inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,0, 4,inf,inf,inf,inf,2},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, 0,inf,inf,7,10,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,0,inf,4,inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,0,3,inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,0,8,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,0,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,7,0},
        };
         
          int graph3[][] = {
           {0,3,4,5,1,inf, inf, inf,inf, inf, inf,inf, inf,inf,inf , inf, inf, inf, inf,inf},//A
            {inf,0,inf, inf, inf,inf, inf, 2,inf, inf,inf, inf,inf,inf,inf, inf, inf, inf, inf,inf},//B
            {inf, inf, 0, inf, inf,7, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf},//C
           {inf, inf, inf, 0, inf,inf, 6, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, 0,inf, inf, inf,inf, inf,inf,3,inf,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,0, inf, inf,inf, 2,inf,4,inf,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, 0, inf,inf, 10,5,inf,inf,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, 0,11, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,0, 4,inf,inf,inf,inf,2, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, 0,inf,inf,7,10,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,0,inf,4,inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,0,3,inf,inf, 13, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,0,8,inf, inf, 3, 2, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,0,inf, inf, 5, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,7,0, inf, inf, inf, 6,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, 0, inf, 9, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, 0, inf, 4,6},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, 0, inf,12},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, 0,3},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,0},
        
            
        };
          int graph4[][] = {
           {0,3,4,5,1,inf, inf, inf,inf, inf, inf,inf, inf,inf,inf , inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},//A
            {inf,0,inf, inf, inf,inf, inf, 2,inf, inf,inf, inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},//B
            {inf, inf, 0, inf, inf,7, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},//C
           {inf, inf, inf, 0, inf,inf, 6, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, 0,inf, inf, inf,inf, inf,inf,3,inf,inf,inf, inf, inf, inf, inf,inf, 13, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,0, inf, inf,inf, 2,inf,4,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, 0, inf,inf, 10,5,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, 0,11, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,0, 4,inf,inf,inf,inf,2, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, 0,inf,inf,7,10,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,0,inf,4,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,0,3,inf,inf, 13, inf, inf, inf,inf, 2, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,0,8,inf, inf, 3, 2, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,0,inf, inf, 5, inf, inf,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,7,0, inf, inf, inf, 6,inf, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, 0, inf, 9, inf,inf, inf, 10, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, 0, inf, 4,6, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, 0, inf,12, inf, inf, 8, 11,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, 0,3, inf, inf, inf, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,0, inf, inf, inf, inf,3},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, 0, inf, 14, inf,inf},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, 0, inf, 9,7},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, 0, inf,3},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, 0,4},
           {inf, inf, inf, inf, inf,inf, inf, inf,inf, inf,inf,inf,inf,inf,inf, inf, inf, inf, inf,inf, inf, inf, inf, inf,0},
        
            
        };
        
        
       //send matrix to floyd worshal algorithm 
        Floyd(graph);
        double finishTime=System.currentTimeMillis();
        System.out.println("total run time of dijkstra algorithm is "+(finishTime-startTime)+" ms");
        

        

    }

}
