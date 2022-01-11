package funix.csd.asm2;

import java.util.Scanner;

/**
 * Helper class read input from user
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Get valid string from user,
     * not allow empty string ""
     * @param prompt - prompt message
     * @return - string after trimming
     */
    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        String input = "";

        while (true) {
            if (!input.equals(""))
                break;
            input = scanner.nextLine().trim();
        }

        return input;
    }

    /**
     * Get valid integer from user
     * parse from string
     * @param promp - prompt message
     * @return - integer
     */
    public static int readInteger(String promp) {
        String input = "";
        int number = 0;

        while (true) {
            try {
                input = readString(promp);
                number = Integer.parseInt(input);
                break;
            }
            catch (NumberFormatException ex) {
                System.out.println("Invalid integer number! Please try again.");
            }
        }

        return number;
    }

    /**
     * Get valid number from user
     * parse from string
     * @param promp - prompt message
     * @return - double
     */
    public static double readDouble(String promp) {
        String input = "";
        double number = 0;

        while (true) {
            try {
                input = readString(promp);
                number = Double.parseDouble(input);
                break;
            }
            catch (NumberFormatException ex) {
                System.out.println("Invalid number! Please try again.");
            }
        }

        return number;
    }
}
