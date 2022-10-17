package src;
import java.util.EmptyStackException;

//Queuing through stack arrays
//Time Complexity of the push:
//O(1)
//Time Complexity of the pop:
//O(2N) 
//We go through entire stacks one after
//the other
public class QueuStack<Item> implements Queue<Item> {

    private Stack<Item> mainstack;
    private Stack<Item> tempstack;
    public int length;

    QueuStack() {
        mainstack = new Stack<>();
        tempstack = new Stack<>();
        length = mainstack.length;
        }

    private void readd() {
        while (!tempstack.isEmpty()) {
            Item val = tempstack.pop();
            mainstack.push(val);
        }
    }

    private void reverse() {
        while (!mainstack.isEmpty()) {
            Item val = mainstack.pop();
            tempstack.push(val);
        }
    }

    public Item pop() {
        if (mainstack.isEmpty()) {
            throw new EmptyStackException();
        }
        reverse();
        Item val = tempstack.pop();
        readd();
        return val;
    }

    public void push(Item item) {
        mainstack.push(item);
    }

    public Item get(int x) {
        return mainstack.get(x);
    }

    public int size() {
        return mainstack.length;
    }

    public static void main(String[] args) {

        Queue<String> queue = new QueuStack<>();
        for (String arg : args){
            queue.push(arg);
        }
        System.out.println(queue.pop());
        System.out.println(queue.get(0));
        System.out.println(queue.size());
    }
}
