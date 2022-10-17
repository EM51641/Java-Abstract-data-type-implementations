package src;

// Efficient linear implementation
// of a Stack in an array form.
//Exemple for 64 data we make 
// 2^6 - 1 + (64)
// = 127 + 64 
// = 193
// = 3N - 1

// One Drawback is the need to type cast
// the generic type T
// To ensure that this typecast succeeds
// We need to pass the T generic through
// methods where the integrity is verified


public class Stack<Item> {
    private Item[] arr;
    public int length = 0;
    private int N;

    Stack(){
        N = 1;
        Resize();
    }

    private void Resize() {
        Item[] coparr = arr;
        N = 2 * N;
        arr = (Item[]) new Object[N];

        for (int i = 0; i < length; i++) {
            arr[i] = coparr[i];
        }
    }

    public void push(Item item) {
        if (isFull()) {
            Resize();
        }

        arr[++length - 1] = item;
    }

    public Item pop() {
        return arr[length-- - 1];
    }

    public Item get(int i) {
        if (i >= 0 && i < length) {
            return arr[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private boolean isFull() {
        if (length - 1 == N - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        for (String arg : args) {
            stack.push(arg);
        }
    }
}
