
/**
 * The GMM (Gym Meal Machine) class represents a vending machine that sells gym meals.
 * It manages the loading of products into slots, selling products, and clearing slots when they become empty.
 */
class GMMMachine {
    private Product[][] slots;
    private final int ROWS = 6;
    private final int COLUMNS = 4;
    /**
     * Constructs a new GMM (Gym Meal Machine) object with an array of slots.
     * The number of rows and columns is predefined.
     */
    public GMMMachine() {
        slots = new Product[ROWS][COLUMNS];
    }
    /**
     * Fills a slot in the vending machine with a product.
     *
     * @param product The product to be loaded into the vending machine.
     * @return An integer indicating the status of the operation:
     *         0 - Product loaded successfully,
     *         1 - Still slots available,
     *        -1 - Machine is full.
     */
    public int fill(Product product) {
        // Find an empty slot or a slot with the same product
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (slots[i][j] == null || slots[i][j].getName().equals(product.getName())) {
                    // If the slot is empty or contains the same product, check if there is enough capacity
                    if (slots[i][j] == null || slots[i][j].getQuantity() < 10) {
                        // Add the product to the slot
                        if (slots[i][j] == null) {
                            slots[i][j] = product;
                        } else {
                            slots[i][j].increaseQuantity();
                        }
                        // Product loaded successfully
                        return 0;
                    }
                }
            }
        }

        // Check if there are still available slots
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (slots[i][j] == null || slots[i][j].getQuantity() < 10) {
                    return 1; // Still slots available
                }
            }
        }

        // If machine is full, write to file
        return -1; // Machine is full
    }
    /**
     * Sells a product from the vending machine based on the given purchase information.
     *
     * @param purchase The purchase information including the type of purchase, money loaded, choice, and value.
     * @param messages StringBuilder to store messages related to the purchase process.
     * @return An integer indicating the status of the sale operation:
     *         0 - Sale successful,
     *        -1 - Sale unsuccessful.
     */
    public int sellProduct(Purchase purchase, StringBuilder messages) {
        if (purchase.getChoice().equals("NUMBER")) {
            if (purchase.getValue() > 24) {
                messages.append("INFO: Number cannot be accepted. Please try again with another number.\n");
                messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                return -1;
            }
            else {
                double value = purchase.getValue();

                int row = (int) (value / 4);
                int column = (int) (value % 4);



                if (slots[row][column] != null) {
                    if (slots[row][column].getQuantity() > 0) {
                        if (purchase.getMoneyLoaded() >= slots[row][column].getPrice()) {
                            messages.append("PURCHASE: You have bought one " + slots[row][column].getName() + "\n");
                            slots[row][column].decreaseQuantity();// Decrease the quantity of the sold product

                            double change = purchase.getMoneyLoaded() - slots[row][column].getPrice();
                            int changeInteger = (int) change;
                            messages.append("RETURN: Returning your change: " + changeInteger + " TL\n");

                            return 0; // Sale successful
                        } else {
                            messages.append("INFO: Insufficient money, try again with more money.\n");
                            messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                            return -1;
                        }
                    } else {
                        messages.append("INFO: This slot is empty, your money will be returned.\n");
                        messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                        return -1;
                    }
                } else {
                    messages.append("INFO: This slot is empty, your money will be returned.\n");
                    messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                    return -1;
                }
            }
        }else if (purchase.getChoice().equals("CARB")) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (slots[i][j] != null) {
                        int resultClear =clearSlot(i, j);
                        if(resultClear == 0){
                            continue;
                        }

                        double difference = Math.abs(slots[i][j].getCarbohydrate() - purchase.getValue());
                        if (difference <= 5) {
                            if (slots[i][j].getQuantity() > 0) {

                                if (purchase.getMoneyLoaded() >= slots[i][j].getPrice()) {
                                    messages.append("PURCHASE: You have bought one " + slots[i][j].getName() + "\n");
                                    slots[i][j].decreaseQuantity(); // Decrease the quantity of the sold product
                                    double change = purchase.getMoneyLoaded() - slots[i][j].getPrice();
                                    int changeInteger = (int) change;
                                    messages.append("RETURN: Returning your change: " +  changeInteger + " TL\n");
                                    return 0; // Sale successful

                                }
                                else {
                                    messages.append("INFO: Insufficient money, try again with more money.\n");
                                    messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                    return -1;
                                }
                            }
                            else {
                                messages.append("INFO: This slot is empty, your money will be returned.\n");
                                messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                return -1;
                            }
                        }
                    }
                }
            }
        } else if (purchase.getChoice().equals("PROTEIN")) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (slots[i][j] != null) {
                        int resultClear =clearSlot(i, j);
                        if(resultClear == 0){
                            continue;
                        }

                        double difference = Math.abs(slots[i][j].getProtein() - purchase.getValue());
                        if (difference <= 5) {
                            if (slots[i][j].getQuantity() > 0) {

                                if (purchase.getMoneyLoaded() >= slots[i][j].getPrice()) {
                                    messages.append("PURCHASE: You have bought one " + slots[i][j].getName() + "\n");
                                    slots[i][j].decreaseQuantity(); // Decrease the quantity of the sold product

                                    double change = purchase.getMoneyLoaded() - slots[i][j].getPrice();
                                    int changeInteger = (int) change;
                                    messages.append("RETURN: Returning your change: " +  changeInteger + " TL\n");
                                    return 0; // Sale successful
                                }
                                else {
                                    messages.append("INFO: Insufficient money, try again with more money.\n");
                                    messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                    return -1;
                                }
                            }
                            else {
                                messages.append("INFO: This slot is empty, your money will be returned.\n");
                                messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        else if (purchase.getChoice().equals("FAT")) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (slots[i][j] != null) {
                        int resultClear =clearSlot(i, j);
                        if(resultClear == 0){
                            continue;
                        }

                        double difference = Math.abs(slots[i][j].getFat() - purchase.getValue());
                        if (difference <= 5) {
                            if (slots[i][j].getQuantity() > 0) {

                                if (purchase.getMoneyLoaded() >= slots[i][j].getPrice()) {
                                    messages.append("PURCHASE: You have bought one " + slots[i][j].getName() + "\n");
                                    slots[i][j].decreaseQuantity(); // Decrease the quantity of the sold product

                                    double change = purchase.getMoneyLoaded() - slots[i][j].getPrice();
                                    int changeInteger = (int) change;
                                    messages.append("RETURN: Returning your change: " +  changeInteger + " TL\n");
                                    return 0; // Sale successful
                                }
                                else {
                                    messages.append("INFO: Insufficient money, try again with more money.\n");
                                    messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                    return -1;
                                }
                            }
                            else {
                                messages.append("INFO: This slot is empty, your money will be returned.\n");
                                messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                return -1;
                            }

                        }
                    }
                }
            }
        }
        else if (purchase.getChoice().equals("CALORIE")) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (slots[i][j] != null) {
                        int resultClear =clearSlot(i, j);
                        if(resultClear == 0){
                            continue;
                        }


                        double difference = Math.abs(slots[i][j].getCalories() - purchase.getValue());
                        if (difference <= 5) {
                            if (slots[i][j].getQuantity() > 0) {
                                resultClear =clearSlot(i, j);
                                if(resultClear == 0){
                                    continue;
                                }

                                if (purchase.getMoneyLoaded() >= slots[i][j].getPrice()) {
                                    messages.append("PURCHASE: You have bought one " + slots[i][j].getName() + "\n");
                                    slots[i][j].decreaseQuantity(); // Decrease the quantity of the sold product

                                    double change = purchase.getMoneyLoaded() - slots[i][j].getPrice();
                                    int changeInteger = (int) change;
                                    messages.append("RETURN: Returning your change: " +  changeInteger + " TL\n");
                                    return 0; // Sale successful
                                }
                                else {
                                    messages.append("INFO: Insufficient money, try again with more money.\n");
                                    messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                    return -1;
                                }
                            }
                            else {
                                messages.append("INFO: This slot is empty, your money will be returned.\n");
                                messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        messages.append("INFO: Product not found, your money will be returned.\n");
        messages.append("RETURN: Returning your change: " + purchase.getMoneyLoaded() + " TL\n");
        return -1; // Sale unsuccessful
    }
    /**
     * Clears a slot in the vending machine.
     *
     * @param row    The row index of the slot to be cleared.
     * @param column The column index of the slot to be cleared.
     * @return An integer indicating the status of the operation:
     *         0 - Slot cleared successfully,
     *         1 - Slot not empty or does not exist.
     */
    public int clearSlot(int row, int column) {
        if (slots[row][column] != null && slots[row][column].getQuantity() == 0) {
            slots[row][column] = null;
            return 0;
        }
        return 1;
    }




    /**
     * Returns a string representation of the GMM (Gym Meal Machine) object.
     *
     * @return A string containing the details of the products loaded in the vending machine.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----Gym Meal Machine-----\n");
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (slots[i][j] != null) {
                    sb.append(slots[i][j].getName()).append("(").append((int) Math.round(slots[i][j].getCalories())).append(", ").append(slots[i][j].getQuantity()).append(")");
                    sb.append("___");
                } else {
                    sb.append("___(0, 0)___");
                }
                count++;
                // Check if 4 products are processed
                if (count % 4 == 0) {
                    sb.append("\n"); // Add newline after every 4 products
                }
            }
        }
        sb.append("----------");
        return sb.toString();
    }
}
