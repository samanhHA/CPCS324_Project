/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooni
 */
public class Edge implements Comparable<Edge> {

        int source;
        int destination;
        int weight;

        public Edge() {
        }

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            //check if the 
            return (this.source == ((Edge) o).source && this.destination == ((Edge) o).destination);
            //false => not equel 
            //True  => equel
        }

        //compare weight
        //used when sorting for kruskal 
        @Override
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

