package src;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N = 0;
    private Item val;
    private RandomizedQueue<Item> prev, next;
    private RandomizedQueue<Item> head = null;
    private RandomizedQueue<Item> tail = null;
    


    // construct an empty deque
    public RandomizedQueue(){
        val = null;
        prev = null;
        next = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        if (N == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // return the number of items on the deque
    public int size(){
        return N; 
    }

    // add the item to the back
    public void enqueue(Item item){

        if(item == null){
            throw new IllegalArgumentException();
        }

        RandomizedQueue<Item> node = new RandomizedQueue<>();
        node.val = item;

        if(N>0)
        {
            node.prev = tail;
            tail.next = node ;
            tail = node;
        }
        else{
            head = tail = node;
        }

        this.N = N + 1;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        int idx = StdRandom.uniformInt(0, N);
        RandomizedQueue<Item> node = head;

        for(int i = 0; i < idx; i++){
            node = node.next;
        }

        return node.val;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        int idx = StdRandom.uniformInt(0, N);
        RandomizedQueue<Item> node = head;

        for(int i = 0; i < idx; i++){
            node = node.next;
        }

        Item rmvalue = node.val;

        if(node.next == null && node.prev != null){
            tail = tail.prev;
            tail.next = null;
        }
        else if(node.prev == null && node.next != null){
            head = head.next;
            head.prev = null;
        }
        else if(node.prev != null && node.next != null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        else{
            head = tail = null;
        }

        this.N = N - 1;

        return rmvalue;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new Iterator<Item>(){

            private RandomizedQueue<Item> node = head;
            private int index = 0;
            private Item[] array = iteration(); //= (Item[]) new Object[N];

            private Item[] iteration(){
                array = (Item[]) new Object[size()];
                int i = 0;
                while(node.next != null){
                    array[i] = node.val;
                    node = node.next;
                    i = i + 1;
                }
                array[i] = node.val;
                StdRandom.shuffle(array);
                return array;
            }

            public boolean hasNext(){

                if(index < N){
                    return true;
                }
                else{
                    return false;
                }
            }
            
            public Item next(){
                if(index < N){
                    return array[index++];
                }
                else{
                    throw new NoSuchElementException();
                }
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> rdqueue = new RandomizedQueue<>();
        for(String arg: args){
            rdqueue.enqueue(arg);
        }

        Iterator<String> it = rdqueue.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}