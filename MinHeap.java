import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.util;

public class MinHeap {

    public static int length = 0;
    public static ArrayList<Integer> heap = new ArrayList<Integer>();
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command = in.next();
        while( !(command.equals("exit")) ) {
            if(command.equals("build")) {
                arr = scanArray(in);
                MinHeap.buildHeap(arr);
            } else if (command.equals("length")) {
                MinHeap.heapLength();
            } else if (command.equals("getmin")) {
                MinHeap.heapMin();
            } else if (command.equals("extract")) {
                MinHeap.heapExtract();
            } else if (command.equals("insert")) {
                MinHeap.insert(in.nextInt());
            } else {
                MinHeap.change(in.nextInt(), in.nextInt());
            }
        }
    }

    public static ArrayList<Integer> scanArray(Scanner in) {
        // scan line of text
        String line = in.nextLine();

        // convert line of text into array of strings (tokens)
        String[] tokens = line.split(" ");

        // convert array of strings into array of integers
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (String token : tokens) {
            if (!token.isEmpty()) // some tokens may be empty (e.g. with trailing spaces)
                arr.add(Integer.parseInt(token));
        }

        return arr;
    }

    public static void heapLength() {

        System.out.println(heap.size());
        MinHeap.printHeap();
    }

    public static void heapMin() {

        System.out.println(heap.get(0));
        MinHeap.printHeap();
    }

    public static void build(ArrayList<Integer> arr) {

        int length = heap.size();

        for(int i=0; i<length; i++) {
            heap.add(arr.get(i));
        }

        for(int i=(length/2); i>=0; i--) {
            MinHeap.Heapify(i);
        }

        for(int i=0; i<length; i++) {
            System.out.print(heap.get(i) + " ");
        }

        System.out.println();
    }

    public static void insert(int x) {

        heap.add(x);
        MinHeap.fixUp();
    }

    public static void heapExtract() {

        Collections.swap(heap, 0, heap.size());
        heap.remove(heap.size());
        MinHeap.heapify(0);
    }

    public static void heapify(int index) {

        int minindex;
        if( (heap.get(index) > heap.get(2*index)) || (heap.get(index) > heap.get(2*index + 1)) ) {
            minindex = MinHeap.findMin(heap.get(index), heap.get(2*index), heap.get(2*index + 1));
            Collections.swap(heap, index, minindex);
            MinHeap.heapify(minindex);
        } 
    }

    public static void printHeap() {

        for(int i=0; i<heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }

        System.out.println();
    }
}