package src;

import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args){

        int idx = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            queue.enqueue(str);
        }

        if(idx > queue.size()){
            throw new NoSuchElementException();
        }

        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            System.out.println(queue.dequeue());
        } 
    }
 }