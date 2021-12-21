/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *A min Heap is a data structure, where the root of the tree has the smallest value,
 and the children of each node has a smaller value than its parent. 
 In this program we are using min heap to implement the prim's algorithm 
 and increase its efficency 
 
/*

A min Heap is a data structure, where the root of the tree has the smallest value,
and the children of each node has a smaller value than its parent. 
In this program we are using min heap to implement the prim's algorithm 
and increase its efficency 


 */


public class MinHeap {
    

    int capacity; //nodes number
    int currentSize;

    /**
     *
     */
    HeapNode[] minheap;
    int[] indexes; //to decrease the wieght

    public MinHeap(int capacity) {

        this.capacity = capacity;
        minheap = new HeapNode[capacity + 1];  //intialize the min heap array
        indexes = new int[capacity]; //this array has elements with the exact number of nodes to know the index in minheap
        minheap[0] = new HeapNode();
        minheap[0].wieght = Integer.MIN_VALUE;
        minheap[0].vertex = -1;
        currentSize = 0;

    }

    //insert new node
    public void insert(HeapNode hNode) {
        currentSize++;
        int i = currentSize;
        minheap[i] = hNode; //store the node to be inserted into index i 
        indexes[hNode.vertex] = i; //upadate indexes 
        bubbleUp(i);
    }

    public void bubbleUp(int p) {
        //to know position of parent node
        int parentIndex = p / 2;
        int currentIndex = p;

        while (currentIndex > 0 && minheap[parentIndex].wieght > minheap[currentIndex].wieght) {
            HeapNode currentNode = minheap[currentIndex];
            HeapNode parentNode = minheap[parentIndex];

            //positions swapping
            indexes[currentNode.vertex] = parentIndex;
            indexes[parentNode.vertex] = currentIndex;
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    //To extract minimum weight 
    public HeapNode extractMin() {

        HeapNode min = minheap[1]; //root value 
        HeapNode lastNode = minheap[currentSize]; //last node = maximum node, since the root of min heap is the least as we said above
        indexes[lastNode.vertex] = 1; //move last node to the top
        minheap[1] = lastNode; //move first node down 
        minheap[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    //in minheap it must to have the min value in the root
    //we do heapify if it was not 
    public void sinkDown(int x) {
        int smallest = x;
        int IndexOfLeftChild = 2 * x;
        int IndexOfRightChild = 2 * x + 1;

        if (IndexOfLeftChild < Current_Size_Heap() && minheap[smallest].wieght > minheap[IndexOfLeftChild].wieght) {
            smallest = IndexOfLeftChild;
        }

        if (IndexOfRightChild < Current_Size_Heap() && minheap[smallest].wieght > minheap[IndexOfRightChild].wieght) {
            smallest = IndexOfRightChild;
        }
        if (smallest != x) {

            HeapNode smallestNode = minheap[smallest];
            HeapNode xNode = minheap[x];

            indexes[smallestNode.vertex] = x;
            indexes[xNode.vertex] = smallest;
            swap(x, smallest);
            sinkDown(smallest);
        }
    }

    public int Current_Size_Heap() {
        return currentSize;
    }

    public void swap(int a, int b) {
        HeapNode temp = minheap[a];
        minheap[a] = minheap[b];
        minheap[b] = temp;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    /*public void printMinHeap() {
        for (int i = 0; i <= currentSize; i++) {
            System.out.println(" " + minheap[i].vertex + "   key   " + minheap[i].wieght);
        }
        System.out.println("________________________");
    }*/
    
}

