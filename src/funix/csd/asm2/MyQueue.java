package funix.csd.asm2;

import java.util.EmptyStackException;

/**
 * My Simple Queue List
 * @param <T>
 */
public class MyQueue<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * Add to the end of queue
     * @param value
     */
    public void add(T value) {
        Node node = new Node(value);

        // If queue is empty, initialize head and tail
        if (isEmpty()) {
            head = tail = node;
            return;
        }

        // Add node to end
        tail.setNext(node);
        tail = node;
    }

    /**
     * Remove node int the front of queue
     * @return
     */
    public T remove() {
        // Throw Exception if queue is empty
        if (isEmpty())
            throw new EmptyStackException();

        T removeValue = head.getValue();

        // If queue contains only one element, clear queue
        if (head == tail) {
            clear();
            return removeValue;
        }

        // Remove node from front
        Node next = head.getNext();
        head.setNext(null);
        head = next;

        return removeValue;
    }

    /**
     * Peek the front of queue
     * @return
     */
    public T peek() {
        if (isEmpty())
            return null;

        return head.getValue();
    }

    /**
     * Clear queue
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * Check if queue is empty;
     * @return
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Traverse queue
     * @return - string result from all value
     */
    @Override
    public String getInfo() {
        if (isEmpty())
            return "";

        StringBuilder result = new StringBuilder();

        while (!isEmpty()) {
            result.append(remove());
            result.append("\n");
        }

        return result.toString();
    }
}
