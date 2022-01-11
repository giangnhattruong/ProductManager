package funix.csd.asm2;

/**
 * Interface for reading file
 */
public interface Readable {
    /**
     * Read a file and return string result
     * @param fileName
     * @return
     */
    String readFile(String fileName);
}
