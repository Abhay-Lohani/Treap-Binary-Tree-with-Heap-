import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCustomList<T> extends AbstractList<T> {
    private Node<T> head; // The head of the list
    private int size; // Size of the list

    // Node class for the custom list
    private static class Node<T> {
        T data; // Data stored in the node
        Node<T> next; // Pointer to the next node

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyCustomList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int size() {
        return size;
    }

    // Method to add an element to the end of the list
    public boolean add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return false;
    }

    // Iterator for the custom list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
