package funix.csd.asm2;

/**
 * Main operation on product list
 */
public class OperationToProduct {
    private final String INPUT_FILE_NAME;
    private final String OUTPUT_FILE_NAME;
    private final String FILE_HEADER;
    private final int BINARY_ARRAY_SIZE;
    private final Writable writer;
    private final Readable reader;
    private MyList<Product> list;

    /**
     * Constructor
     * @param inputFileName
     * @param outputFileName
     * @param writer
     * @param reader
     */
    public OperationToProduct(
            String inputFileName,
            String outputFileName,
            Writable writer,
            Readable reader
    ) {
        this.INPUT_FILE_NAME = inputFileName;
        this.OUTPUT_FILE_NAME = outputFileName;
        this.writer = writer;
        this.reader = reader;
        list = new MyList<>();
        FILE_HEADER = "ID | Title | Quantity | Price\n" +
                "------------------------------\n";
        BINARY_ARRAY_SIZE = 32;
    }

    /**
     * Overloading constructor with default implementation class
     * for Writer and Reader interface
     * @param inputFileName
     * @param outputFileName
     */
    public OperationToProduct(String inputFileName, String outputFileName) {
        this(inputFileName, outputFileName, new FileProcess(), new FileProcess());
    }

    /**
     * Create new product from user input and add to list
     * @return
     */
    public Product createProduct() {
        // Input product info
        String bcode = Console.readString("Input new ID");
        String title = Console.readString("Input product's name");
        Integer quantity = Console.readInteger("Input product's quantity");
        Double price = Console.readDouble("Input product's price");

        // Add product to list
        Product product = new Product(bcode, title, quantity, price);
        list.addLast(product);

        // Log result
        logResult("Create successful!");
        return product;
    }

    /**
     * Load data from file and add to list
     * @param list
     */
    private void loadData(IList<Product> list) {
        String fileInput = reader.readFile(INPUT_FILE_NAME);
        StringUtils.convertString(fileInput, list);
    }

    /**
     * Display data of IList interface
     * @param list
     */
    private void displayData(IList<Product> list) {
        String result;

        if (list.isEmpty())
            result = "List is empty.";
        else {
            result = FILE_HEADER + list.getInfo() + "\nSuccessfully!";
        }

        logResult(result);
    }

    /**
     * Overloading displayData for
     * displaying list data as default
     */
    public void displayData() {
        displayData(list);
    }

    /**
     * Load file to list and display
     */
    public void loadFileToListAndShow() {
        list.clear();
        loadData(list);
        displayData();
    }

    /**
     * Load file to stack and display with order reversed
     */
    public void loadFileToStackAndShow() {
        MyStack<Product> stack = new MyStack<>();
        loadData(stack);
        displayData(stack);
    }

    /**
     * Load file to queue and display
     */
    public void loadFileToQueueAndShow() {
        MyQueue<Product> queue = new MyQueue<>();
        loadData(queue);
        displayData(queue);
    }

    /**
     * Save info from list to file
     */
    public void saveFile() {
        StringBuilder content = new StringBuilder();

        // Add file header
        String initialString = FILE_HEADER;
        content.append(initialString);

        // Add list info
        content.append(list.getInfo());

        // Write file
        writer.writeFile(INPUT_FILE_NAME, content.toString());

        // Log result
        logResult("Save successful!");
    }

    /**
     * Search produce from input product bcode or ID
     * @param bcode
     * @return
     */
    private Product searchByCode(String bcode) {
        // Iterate list to find the node with this bcode
        Node<Product> current = list.head();
        while (current != null) {
            if (current.getValue().getBcode().equals(bcode))
                return current.getValue();

            current = current.getNext();
        }

        return null;
    }

    /**
     * Overloading searchByCode
     */
    public void searchByCode() {
        // Check if list is empty
        if (list.isEmpty()) {
            logResult("List is empty.");
            return;
        }

        // Input bcode
        String bcode = Console.readString("Input search ID");

        // Search product
        Product product = searchByCode(bcode);

        // Get result string
        String result;
        if (product == null)
            result = "Product ID \"" + bcode + "\" not found.";
        else
            result = "Search result: " + product.toString();

        // Log result
        logResult(result);
    }

    /**
     * Delete a produce by bcode
     */
    public void deleteByCode() {
        // Check if list is empty
        if (list.isEmpty()) {
            logResult("List is empty.");
            return;
        }

        // Input bcode
        String bcode = Console.readString("Delete ID");

        // Search product
        Product product = searchByCode(bcode);

        // Get result string and remove found product
        String result;
        if (product == null)
            result = "Product not found.";
        else {
            list.removeValue(product);
            result = "Delete product ID " + bcode + " successful!";
        }

        // Log result
        logResult(result);
    }

    /**
     * Quick sort - recursive
     * @param start
     * @param end
     */
    private void sortByCode(Node<Product> start, Node<Product> end) {
        // Base cases:
        // node end is out of bound or
        // list contains only one element or
        // start is out of bound (after end) - when iterate
        // a node from start to end
        if (end == null || start == end || end.getNext() == start)
            return;

        // Pivot is the node end
        // Boundary is the pivot position after partition
        Node<Product> boundary = null;

        // Boundary Previous become the new Node end for
        // the next recursive of left part
        Node<Product> boundaryPrevious = null;

        // Check Point is the position available for swapping
        // and form a new boundary in the left part
        Node<Product> checkPoint = start;

        // Current iterate to the end for comparing values
        Node<Product> current = start;
        while (current != end.getNext()) {
            // Swap smaller or equal element (compare to the pivot)
            // To the left part
            // after all, the pivot will be at it's right position
            if (hasCodeLessThanOrEqualTo(current, end)) {
                list.swap(checkPoint, current);
                boundaryPrevious = boundary;
                boundary = checkPoint;
                checkPoint = checkPoint.getNext();
            }

            current = current.getNext();
        }

        // Call recursion for the left part (excluding the pivot)
        sortByCode(start, boundaryPrevious);

        // Call recursion for the right part (excluding the pivot)
        sortByCode(checkPoint, end);
    }

    /**
     * Overloading sortByCode
     */
    public void sortByCode() {
        // Check if list is empty
        if (list.isEmpty()) {
            logResult("List is empty.");
            return;
        }

        // Call recursion for list
        sortByCode(list.head(), list.tail());

        // Log result
        logResult("Sort by ID successful!");
    }

    /**
     * Check if first node's value is less than or equal to second node's value
     * @param first
     * @param second
     * @return
     */
    private boolean hasCodeLessThanOrEqualTo(Node<Product> first, Node<Product> second) {
        return first.getValue().getBcode().compareTo(second.getValue().getBcode()) <= 0;
    }

    /**
     * Convert number into binary array - recursive
     * @param number
     * @param binaryResult
     * @param index
     */
    private void convertToBinary(int number, int[] binaryResult, int index) {
        // Check for special case when number equal to 0
        if (number == 0)
            return;

        // Get binary last value as remainder of number % 2
        // and add to the last position of the array
        binaryResult[index] = number % 2;

        // Call recursion for number / 2
        convertToBinary(number / 2, binaryResult, index - 1);
    }

    /**
     * Overloading convertToBinary
     * initialize recursion
     * @param number
     * @return
     */
    private int[] convertToBinary(int number) {
        // Initialize recursion
        int[] binaryResult = new int[BINARY_ARRAY_SIZE];
        convertToBinary(number, binaryResult, BINARY_ARRAY_SIZE - 1);

        return binaryResult;
    }

    /**
     * Overloading convertToBinary
     * Get binary for the first product's quantity number in the list
     */
    public void convertToBinary() {
        // Check if list is empty
        if (list.isEmpty()) {
            logResult("List is empty.");
            return;
        }

        // Get the first product's quantity number in the list
        int number = list.head().getValue().getQuantity();

        // Check for special case when number is 0
        if (number == 0) {
            logResult("Binary of 0 is 0.");
            return;
        }

        // Convert number to array of binary
        int[] binaryResult = convertToBinary(number);

        // Get string result
        StringBuilder result = new StringBuilder();
        result.append("Binary of " + number + " is: ");

        // Skip all 0 value til the beginning
        // of binary (number "1")
        int i = 0;
        while (binaryResult[i] != 1)
            i++;

        // Collect result
        for (int k = i; k < BINARY_ARRAY_SIZE; k++) {
            result.append(binaryResult[k]);
        }

        // Log result
        logResult(result.toString());
    }

    /**
     * Show result and write it to console log file
     * @param result
     */
    private void logResult(String result) {
        // Wrap result with empty lines
        result = "\n" + result + "\n";

        // Show result and write to file
        System.out.println(result);
        writer.writeFile(OUTPUT_FILE_NAME, result, true);
    }
}
