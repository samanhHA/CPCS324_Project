
import java.util.ArrayList;
import java.util.Collections;

/*
Kruskal is an algorithm for computing a minimum spannig tree a It maintains a set of partial minimum spanning trees
and repeatedly adds the shortest edge in the graph whose vertices are in different partial minimum spanning trees.
 */

/**
 *
 * kruskal is an algorithm for computing a minimum spannig tree a It maintains a set of partial minimum spanning trees
 *and repeatedly adds the shortest edge in the graph whose vertices are in different partial minimum spanning trees.
 */

public class kruskal {

    public kruskal() {
    }

    class subset {

        int parent, rank;
    };

    //A utility function to find set of an element i (uses path compression technique)

    /**
     *A utility function to find set of an element i (uses path compression technique)
     */
    public int find(subset subsets[], int i) {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent
                    = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    // A function that does union of two sets (uses union by rank)

    /**
     *A function that does union of two sets (uses union by rank)
     */
    void Union(subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        //  smaller rank tree is attached under root of high rank tree (Union by Rank)
        if (subsets[xroot].rank
                < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank
                > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } // both ranks are same, then make one as root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // kruskal function to construct MST ,takes two arguments : arraylist of all edges and number of verticies

    /**
     *
     *  step 1:Sort all the edges of (allEdges) in non-decreasing order of their weight.
     * Step 2: Pick the smallest edge. And increment the index for next iteration
     * step3: checkIf including this edge doesn't cause cycle,include it in result and increment the index of result for next edge
     * @param allEdges==> array of all edges of the graph
     * @param vertices ==> number of vertices
     */
    void KruskalMST(ArrayList<Edge> allEdges, int vertices) {
        // result[] store the resultant MST
        Edge result[] = new Edge[vertices];

        //initializing the resulted edges 
        int i = 0;
        for (i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        // Sort all the edges in non-decreasing
        // order of their weight. 
        Collections.sort(allEdges);

        subset subsets[] = new subset[vertices];
        for (i = 0; i < vertices; ++i) {
            subsets[i] = new subset();
        }

        // Create subsets with single elements
        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; // Index for next edge of allEdges arraylist 

        // An index variable, used for result[]
        int e = 0;
        // Number of resultant edges are equal to V-1
        while (e < vertices - 1 && i < allEdges.size()) {
            // Pick the smallest edge from sorted in non-decreasing order allEdges array. 
            // then And increment the index for next iteration

            Edge nextEdge = new Edge();
            nextEdge = allEdges.get(i++);

            int x = find(subsets, nextEdge.source);
            int y = find(subsets, nextEdge.destination);

            //include the edge if cycle is not created
            if (x != y) {
                result[e++] = nextEdge;//increment index for next edge to include
                Union(subsets, x, y);
            }

            // Else ignore the next_edge
        }

        // print the result of kruskal (built MST)
        System.out.println("----------------------------------Kruskal----------------------------------");
        int minimumCost = 0;

        for (i = 0; i < e; ++i) {
            minimumCost += result[i].weight;
        }
        System.out.println("\nMinimum Cost Spanning Tree = " + minimumCost);

    }
}

