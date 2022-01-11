package funix.csd.asm2;

import java.io.*;

/**
 * Read and write file
 */
public class FileProcess implements Writable, Readable {
    /**
     * Write file from a string
     * default append is false
     * @param fileName
     * @param string
     */
    @Override
    public void writeFile(String fileName, String string) {
        writeFile(fileName, string, false);
    }

    /**
     * Write file from a string
     * let user choose append or not
     * @param fileName
     * @param string
     */
    @Override
    public void writeFile(String fileName, String string, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read and load file data into a string
     * @param fileName
     * @return - string data
     */
    @Override
    public String readFile(String fileName) {
        StringBuilder result = new StringBuilder();

        try (FileReader file = new FileReader(fileName)) {
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();

            while (line != null) {
                result.append(line);
                result.append("\n");
                line = reader.readLine();
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
