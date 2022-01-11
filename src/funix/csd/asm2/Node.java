package funix.csd.asm2;

/**
 * Node of MyList, MyStack, MyQueue
 * @param <T>
 */
public class Node<T> {
    private T value;
    private Node next;

    /**
     * Constructor
     * @param value
     * @param next
     */
    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Overloading constructor
     * initialize node next with null
     * @param value
     */
    public Node(T value) {
        this(value, null);
    }

    /**
     * Get node value
     * @return
     */
    public T getValue() {
        return value;
    }

    /**
     * Set node value
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Get node next
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * Set node next
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Get node string
     * @return
     */
    @Override
    public String toString() {
        return "Node=" + value;
    }
}
