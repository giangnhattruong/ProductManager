package funix.csd.asm2;

public class Main {
    /**
     * Main program show list of choices
     * Allow user to choose many times
     * @param args
     */
    public static void main(String[] args) {
        OperationToProduct operation = new OperationToProduct(
                "Data.txt",
                "console_output.txt"
        );

        introduce();

        // Allow user to choose many times
        while (true) {
            showMenu();
            String choice = Console.readString("Choice");

            if (choice.equals("0")) {
                System.out.println("Thank you! Have a great day!\n");
                break;
            }

            getChoice(choice, operation);
        }
    }

    /**
     * Show introducing message
     */
    public static void introduce() {
        System.out.println("Product Manager Program\n");
    }

    /**
     * Show menu of choices
     */
    public static void showMenu() {
        System.out.println("Choose one of this options:");
        System.out.println("Product list:");
        System.out.println("1. Load data from file and display.");
        System.out.println("2. Input & add to the end.");
        System.out.println("3. Display data.");
        System.out.println("4. Save product list to file.");
        System.out.println("5. Search by ID.");
        System.out.println("6. Delete by ID.");
        System.out.println("7. Sort by ID.");
        System.out.println("8. Convert to Binary.");
        System.out.println("9. Load to stack and display.");
        System.out.println("10. Load to queue and display.");
        System.out.println("0. Exit.\n");
    }

    /**
     * Main operation - handle choice from user
     * @param choice
     * @param operation
     */
    public static void getChoice(String choice, OperationToProduct operation) {
        switch (choice) {
            case "1":
                operation.loadFileToListAndShow();
                break;
            case "2":
                operation.createProduct();
                break;
            case "3":
                operation.displayData();
                break;
            case "4":
                operation.saveFile();
                break;
            case "5":
                operation.searchByCode();
                break;
            case "6":
                operation.deleteByCode();
                break;
            case "7":
                operation.sortByCode();
                break;
            case "8":
                operation.convertToBinary();
                break;
            case "9":
                operation.loadFileToStackAndShow();
                break;
            case "10":
                operation.loadFileToQueueAndShow();
                break;
            default:
                System.out.println("Invalid choice! Please try again.\n");
        }
    }
}
