package funix.csd.asm2;

/**
 * Interface for writing file
 */
public interface Writable {
    /**
     * Write file from a string without appending
     * @param fileName
     * @param string
     */
    void writeFile(String fileName, String string);

    /**
     * Write file from a string with option for appending
     * @param fileName
     * @param string
     * @param append
     */
    void writeFile(String fileName, String string, boolean append);
}
