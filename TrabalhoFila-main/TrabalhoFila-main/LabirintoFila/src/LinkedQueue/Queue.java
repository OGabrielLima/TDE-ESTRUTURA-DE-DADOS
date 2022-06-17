package LinkedQueue;

public interface Queue {
    void add(int element);
    int remove();
    void clear();
    boolean isEmpty();
    boolean isFull();
}