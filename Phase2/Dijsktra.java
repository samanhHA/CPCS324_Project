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
 
class Dijsktra {
	// ////////////////////////////////////////////////////////////////////////////////////////
	static final int V = 10;
         final static int inf = 9999999; //indicating to infinity 
         static long startTime;
	/////////////////////////////////////////---Print---/////////////////////////////////////////////////////////////
	static void print(int distance[])
	{
		//print row of alphabits
		String cities []= new String[]
				 {"A", "B", "C","D","E","F","G","H","I","J","i","j", "k", "l","m","n","o","p","Q","r","s","t","U","V","W","X","Y","Z"};
		System.out.println("Vertex \t\t|Distance from Source");
		System.out.println("--------------------------------------");
		for (int i = 0; i < V; i++) {
			//print each alphabit with its distance
			System.out.println(cities[i] +"\t\t|" + distance[i]);
		}
	}
   ///////////////////////////////////////---Minmum distance---/////////////////////////////////////////////////////// 
	static int min_Distance(int distance[], Boolean set[])
{
   // Initialize minimum distance to compare with it with other distance
   int mindistance = Integer.MAX_VALUE;
   int index = -1;
    for (int i = 0; i < V; i++) {
	// find the vertex with minimum distance value, from the set of vertices not yet included in shortest path tree
if ( (set[i] == false)  &&  (distance[i] <= mindistance) ) {
	mindistance =distance[i];
     index = i;
}
    }   
return index;
}
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// implements Dijkstra's algorithm for a alphabet nodes Graph using adjacency matrix
static void dijkstra_algorithm(int cities[][], int src)
	{
            startTime=System.currentTimeMillis();
            
		int distance[] = new int[V]; // The output array. dist[i] will hold
	        Boolean set[] = new Boolean[V];

	//Initialize all distances as infinite and set[] as false
	 for (int i = 0; i < V; i++) {
		 distance[i] = Integer.MAX_VALUE;
        set[i] = false;
		}
		// Distance from src to it self  = 0
	 distance[src] = 0;
		// Find shortest path form src to all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices not yet processed. in the first iteration u == src
			int u = min_Distance(distance, set);
			set[u] = true;
			// Update distance value of the adjacent vertices 
			for (int v = 0; v < V; v++)
				// if distance[v] not in the set -> update edge from u to v and total weight path from src to v is smaller than current value of distance[v]
                if (!set[v] && cities[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + cities[u][v] < distance[v])
					distance[v] = distance[u] + cities[u][v];
		}

		// print distance array ( solution )
		print(distance);
	}

	/////////////////////////////////////----Main Method---//////////////////////////////////////////////////////////////////
	public static void main(String[] args)
	{
		//Print all distance
		int graph[][] = new int[][] { 
                     {0,10,0,0,0,5,0,0,0,0,0},//A
                    { 0,0,3,0,3,0,0,0,0,0 },//B
			{0,0,0,4,0,0,0,5,0,0 },//C
			{ 0,0,0,0,0,0,0,0,4,0},//D
			{0,0,4,0,0,0,2,0,0,0},//E
			{0,3,0,0,0,0,0,0,0,2 },//F
			{0,0,0,7,0,0,0,0,0,0 },//G
			{ 0,0,0,4,0,0,0,0,3,0},//H
			{0,0,0,0,0,0,0,0,0,0 }, //I
			{0,0,0,0,0,0,8,0,0,0},//J
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
                for (int i = 0; i < graph2.length; i++) {
                    for (int q = 0; q < graph2.length; q++) {
                        if (graph2[i][q]==inf) {
                            graph2[i][q]=0;
                        }
                    }
            }
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
              for (int i = 0; i < graph3.length; i++) {
                    for (int q = 0; q < graph3.length; q++) {
                        if (graph3[i][q]==inf) {
                            graph3[i][q]=0;
                        }
                    }
            }
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
          for (int i = 0; i < graph4.length; i++) {
                    for (int q = 0; q < graph4.length; q++) {
                        if (graph4[i][q]==inf) {
                            graph4[i][q]=0;
                        }
                    }
            }
                
                
					
	
       dijkstra_algorithm(graph, 0);
        long finishTime=System.currentTimeMillis();
        System.out.println("total run time of dijkstra algorithm is "+(finishTime-startTime)+" ms");
									
	}
}
