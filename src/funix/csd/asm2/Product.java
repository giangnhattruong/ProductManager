package funix.csd.asm2;

/**
 * Product template
 */
public class Product {
    private String bcode;
    private String title;
    private int quantity;
    private double price;

    /**
     * Constructor
     * @param bcode
     * @param title
     * @param quantity
     * @param price
     */
    public Product(String bcode, String title, int quantity, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Get product barcode - ID
     * @return
     */
    public String getBcode() {
        return bcode;
    }

    /**
     * Get product title - name
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get product quantity
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get product price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get product string
     * @return
     */
    @Override
    public String toString() {
        return bcode + " | " +
                title + " | " +
                quantity + " | " +
                price;
    }

    /**
     * Compare product by barcode or ID
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return bcode == product.bcode;
    }
}
