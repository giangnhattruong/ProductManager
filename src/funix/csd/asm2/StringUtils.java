package funix.csd.asm2;

/**
 * Helper class for converting string input from file
 */
public class StringUtils {
    public static final int NUMBER_OF_PRODUCT_PROPERTIES = 4;

    /**
     * Convert a string input from file to list of products
     * @param string
     * @param list
     */
    public static void convertString(String string, IList<Product> list) {
        // Split string by line for array of lines
        var lines =  string.split("\n");

        for (int i = 0; i < lines.length; i++) {
            // Skip the first 2 lines (file header)
            if (i < 2)
                continue;

            // Split a line for product info (bcode, title, quantity, price)
            String[] info = lines[i].split("\\|");

            // Simple format check if data is the same length as
            // number of product properties
            if (info.length != NUMBER_OF_PRODUCT_PROPERTIES) {
                System.out.println("Invalid file format!");
                return;
            }

            // Format each info element for product properties
            String bcode = info[0].trim();
            String title = info[1].trim();
            int quantity = Integer.parseInt(info[2].trim());
            double price = Double.parseDouble(info[3].trim());

            // Add product to list
            Product product = new Product(bcode, title, quantity, price);
            list.add(product);
        }
    }
}
