package funix.csd.asm2;

/**
 * Interface of MyList, MyQueue and MyStack classes
 * @param <T>
 */
public interface IList<T> {
    /**
     * Add new value to list
     * @param value
     */
    void add(T value);

    /**
     * Traverse list
     * @return - string result from all list data
     */
    String getInfo();

    /**
     * Check if list is empty
     * @return - true/false
     */
    boolean isEmpty();
}
