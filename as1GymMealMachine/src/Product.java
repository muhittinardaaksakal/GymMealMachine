/**
 * The Product class represents a product that can be sold in the vending machine.
 * It contains information about the product such as its name, price, nutritional values, and quantity.
 */
public class Product {
    private String name;
    private double price;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double calories;
    private int quantity;

    /**
     * Constructs a new Product object with the specified attributes.
     *
     * @param name         The name of the product.
     * @param price        The price of the product.
     * @param protein      The protein content of the product.
     * @param carbohydrate The carbohydrate content of the product.
     * @param fat          The fat content of the product.
     */
    public Product(String name, double price, double protein, double carbohydrate, double fat) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.calories = calculateCalorie();
        this.quantity = 1;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the total calories of the product.
     *
     * @return The total calories of the product.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Increases the quantity of the product.
     */
    public void increaseQuantity() {
        quantity++;
    }

    /**
     * Decreases the quantity of the product if the quantity is greater than zero.
     */
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    /**
     * Calculates the total calories of the product based on its nutritional values.
     *
     * @return The total calories of the product.
     */
    private double calculateCalorie() {
        return 4 * protein + 4 * carbohydrate + 9 * fat;
    }

    /**
     * Gets the carbohydrate content of the product.
     *
     * @return The carbohydrate content of the product.
     */
    public double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Gets the protein content of the product.
     *
     * @return The protein content of the product.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Gets the fat content of the product.
     *
     * @return The fat content of the product.
     */
    public double getFat() {
        return fat;
    }
}
