/**
 * The Purchase class represents a purchase made by a customer in the vending machine.
 * It encapsulates information about the purchase type, amount of money loaded, choice made, and value.
 */
public class Purchase {
    private String type;
    private int moneyLoaded;
    private String choice;
    private double value;

    /**
     * Constructs a new Purchase object with the specified parameters.
     *
     * @param type        The type of purchase (e.g., "NUMBER", "CARB", "PROTEIN", etc.).
     * @param moneyLoaded The amount of money loaded by the customer.
     * @param choice      The choice made by the customer.
     * @param value       The value associated with the choice made by the customer.
     */
    public Purchase(String type, int moneyLoaded, String choice, double value) {
        this.type = type;
        this.moneyLoaded = moneyLoaded;
        this.choice = choice;
        this.value = value;
    }

    /**
     * Retrieves the type of purchase.
     *
     * @return The type of purchase.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the amount of money loaded by the customer.
     *
     * @return The amount of money loaded.
     */
    public int getMoneyLoaded() {
        return moneyLoaded;
    }

    /**
     * Retrieves the choice made by the customer.
     *
     * @return The choice made by the customer.
     */
    public String getChoice() {
        return choice;
    }

    /**
     * Retrieves the value associated with the choice made by the customer.
     *
     * @return The value associated with the choice.
     */
    public double getValue() {
        return value;
    }
}
