package LinkedQueue;
public class CircularQueue implements Queue{
    private int base = 0;
    private int top = -1;
    private int[] data;

    public CircularQueue(int size) {
        this.data = new int[size];
    }

    private int move(int position) {
        return position + 1 == data.length ? 0 : position + 1;
    }

    @Override
    public void add(int element) {
        if(isFull()) {
            throw new IllegalStateException("Queue is full!");
        }
        top = move(top);
        data[top] = element;
    }

    @Override
    public int remove() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        int element = data[base];
        if(base == top) {
            clear();
        } else {
            data[base] = 0;
            base = move(base);
        }
        return element;
    }

    @Override
    public void clear() {
        top = -1;
        base = 0;
        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return !isEmpty() && move(top) == base;
    }

    //função que retorna a capacidade da fila
    public int getCapacity() {
        return data.length;
    }

    @Override
    public String toString() {
        String out = "[";
        if(getCapacity() > 0) {
            out += this.data[0];
        }
        for (int i = 1; i < getCapacity(); i++) {
            out += ", " + this.data[i];
        }
        out += "]";
        return out;
    }
}