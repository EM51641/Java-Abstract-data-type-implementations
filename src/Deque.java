package src;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private int N = 0;
    private Item val;
    private Deque<Item> prev, next;
    private Deque<Item> head = null;
    private Deque<Item> tail = null;
    


    // construct an empty deque
    public Deque(){
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

    // add the item to the front
    public void addFirst(Item item){

        if(item == null){
            throw new IllegalArgumentException();
        }

        Deque<Item> node = new Deque<>();
        node.val = item;

        if (N > 0)
        {
            node.next = head;
            head.prev = node;
            head = node;
        }
        else{

            head = tail = node;
        }

        this.N = N + 1;
    }

    // add the item to the back
    public void addLast(Item item){

        if(item == null){
            throw new IllegalArgumentException();
        }

        Deque<Item> node = new Deque<>();
        node.val = item;

        if (N > 0)
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

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item rmval = head.val;

        if(head.next != null){

            head = head.next;
            head.prev = null;
        
        }else{
            head = tail = null;
        }

        this.N = N - 1;

        return rmval;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item rmval = tail.val;

        if(tail.prev != null){

            tail = tail.prev;
            tail.next = null;
        
        }else{
            tail = head = null;
        }

        this.N = N - 1;

        return rmval;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new Iterator<Item>(){

            private Deque<Item> node = head;
            private Item nval;
            private int index = 0;


            public boolean hasNext(){

                if(index < N){
                    return true;
                }
                else{
                    return false;
                }
            }
            
            public Item next(){
                if(hasNext()){
                    
                    nval = node.val;
                    node = node.next;
                    index = index + 1;

                    return nval;
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
        Deque<String> deque = new Deque<>();
        for(String arg: args){
            deque.addLast(arg);
        }

        Iterator<String> it = deque.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        //System.out.println(deque.removeFirst());
        //System.out.println(deque.removeLast());
        //System.out.println(deque.size());
        //System.out.println(deque.isEmpty());

    }

}