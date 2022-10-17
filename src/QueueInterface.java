package src;

interface Queue<Item> {

    public int size();

    public Item pop();

    public void push(Item item);

    public Item get(int x);
}