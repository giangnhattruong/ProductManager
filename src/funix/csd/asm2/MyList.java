package funix.csd.asm2;

/**
 * My Single Linked List with generic type
 * @param <T>
 */
public class MyList<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Add new node to the front of list
     * @param value
     */
    public void addFirst(T value) {
        Node<T> node = new Node(value);
        size++;

        // If list is empty, initialize head and tail
        if (isEmpty()) {
            head = tail = node;
            return;
        }

        // Add node to front
        node.setNext(head);
        head = node;
    }

    /**
     * Add node to the end of list
     * @param value
     */
    public void addLast(T value) {
        Node<T> node = new Node(value);
        size++;

        // If list is empty, initialize head and tail
        if (isEmpty()) {
            head = tail = node;
            return;
        }

        // Add node to end
        tail.setNext(node);
        tail = node;
    }

    /**
     * Wrapper function of add last
     * Required by interface IList
     * @param value
     */
    public void add(T value) {
        addLast(value);
    }

    /**
     * Add node after a position index
     * @param position
     * @param value
     */
    public void addAfter(int position, T value) {
        // Check if position is out of bound
        if (position < 0 && position >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node = new Node(value);

        // Get Node(current) at position index
        Node<T> current = head;
        int i = 0;
        while (i++ < position) {
            current = current.getNext();
        }

        // Add new node after Node current
        node.setNext(current.getNext());
        current.setNext(node);
    }

    /**
     * Remove node from the from of list
     */
    public void removeFirst() {
        // Throw exception of list is empty
        if (isEmpty())
            throw new IllegalStateException();

        size--;

        // Check if list contains only 1 element, then empty list
        if (head == tail) {
            clear();
            return;
        }

        // Remove node from front
        Node<T> next = head.getNext();
        head.setNext(null);
        head = next;
    }

    /**
     * Remove a node in the end of list
     */
    public void removeLast() {
        // Throw exception of list is empty
        if (isEmpty())
            throw new IllegalStateException();

        size--;

        // Check if list contains only 1 element, then empty list
        if (head == tail) {
            clear();
            return;
        }

        // Get Node(current) stays before tail
        Node<T> current = head;
        while (!isTail(current.getNext()))
            current = current.getNext();

        // Remove node from end
        current.setNext(null);
        tail = current;
    }

    /**
     * Remove a node with a value from list
     * @param value
     */
    public void removeValue(T value) {
        // Throw Exception if list is empty
        if (isEmpty())
            throw new IllegalStateException();

        // Check if the node with value is head
        if (head.getValue() == value) {
            removeFirst();
            return;
        }

        // Iterate Node(current), if it's next node
        // is the node we are looking for, we remove it
        Node<T> current = head;
        while (!isTail(current)) {
            if (current.getNext().getValue() == value) {
                // Remove the node with value
                Node<T> removeNode = current.getNext();
                current.setNext(removeNode.getNext());
                removeNode.setNext(null);

                // If current is tail, point tail to current
                tail = isTail(current) ? current : tail;

                size--;
                break;
            }

            current = current.getNext() ;
        }
    }

    /**
     * Swap data of two nodes
     * @param firstNode
     * @param secondNode
     */
    public void swap(Node<T> firstNode, Node<T> secondNode) {
        // If list is empty, or one of the two nodes is null,
        // or two nodes is the same, do nothing
        if (isEmpty() || firstNode == null || secondNode == null || firstNode == secondNode)
            return;

        // Swap data of two nodes
        T temp = secondNode.getValue();
        secondNode.setValue(firstNode.getValue());
        firstNode.setValue(temp);
    }

    /**
     * Get list's head
     * @return
     */
    public Node<T> head() {
        return head;
    }

    /**
     * Get list tail
     * @return
     */
    public Node<T> tail() {
        return tail;
    }

    /**
     * Get value in the front of the list
     * @return
     */
    public T peek() {
        return head.getValue();
    }

    /**
     * Get list size
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Clear list
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * Check if list is empty
     * @return
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Check if a node is tail
     * @param node
     * @return
     */
    private boolean isTail(Node<T> node) {
        return node.getNext() == null;
    }

    /**
     * Traverse list
     * @return - string result from all value
     */
    @Override
    public String getInfo() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node<T> current = head;

        while (current != null) {
            result.append(current.getValue());
            result.append("\n");
            current = current.getNext();
        }

        return result.toString();
    }
}
