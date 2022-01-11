package funix.csd.asm2;

import java.util.EmptyStackException;

/**
 * My Simple Stack
 * @param <T>
 */
public class MyStack<T> implements IList<T> {
    private Node<T> head;

    /**
     * Add node to the front of stack
     * @param value
     */
    public void add(T value) {
        Node<T> node = new Node(value);

        // If queue is empty, initialize head
        if (isEmpty()) {
            head = node;
            return;
        }

        // Add node to front
        node.setNext(head);
        head = node;
    }

    /**
     * Remove node from the front of stack
     * @return
     */
    public T remove() {
        // Throw Exception if queue is empty
        if (isEmpty())
            throw new EmptyStackException();

        T popValue = head.getValue();

        // Remove node from front
        Node<T> newHead = head.getNext();
        head.setNext(null);
        head = newHead;

        return popValue;
    }

    /**
     * Peek the front of stack
     * @return
     */
    public T peek() {
        if (isEmpty())
            return null;

        return head.getValue();
    }

    /**
     * Clear stack
     */
    public void clear() {
        head = null;
    }

    /**
     * Check if queue is empty;
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Traverse list
     * @return - string result from all value
     */
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
