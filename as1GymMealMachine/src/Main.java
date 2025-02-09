/**
 * This class represents the main entry point of the Gym Meal Machine application.
 * It reads input files containing product and purchase data, interacts with the GMM (Gym Meal Machine)
 * to fill the machine with products and sell products based on purchase requests, and writes the
 * transaction details to an output file.
 */
public class Main {

    /**
     * The main method of the application. It reads input files, interacts with the GMM,
     * and writes transaction details to an output file.
     *
     * @param args Command-line arguments. Expects three arguments: input product file path, input purchase file path,
     * and output file path.
     */
    public static void main(String[] args) {
        // Read input file and parse player data
        String[] productLines = FileInput.readFile(args[0], false, false);
        GMMMachine gmm = new GMMMachine();

        StringBuilder loadingMessages = new StringBuilder();
        StringBuilder messages = new StringBuilder();
        for (String productLine : productLines) {
            // Split each line into product attributes
            String[] partsOfProduct = productLine.split("\t");
            String productName = partsOfProduct[0];
            double price = Double.parseDouble(partsOfProduct[1]); // Parse price
            String[] nutrients = partsOfProduct[2].split(" ");
            double protein = Double.parseDouble(nutrients[0]);
            double carbohydrate = Double.parseDouble(nutrients[1]);
            double fat = Double.parseDouble(nutrients[2]);

            Product product = new Product(productName, price, protein, carbohydrate, fat);

            int resultFill = gmm.fill(product);
            if (resultFill == 1)
                loadingMessages.append("INFO: There is no available place to put ").append(productName).append("\n");
            if (resultFill == -1) {
                loadingMessages.append("INFO: There is no available place to put ").append(productName).append("\n");
                loadingMessages.append("INFO: The machine is full!\n");
                break;
            }
        }
        FileOutput.writeToFile(args[2], loadingMessages.toString() + gmm.toString(), false,true);

        String[] purchaseLines = FileInput.readFile(args[1], false, false);
        for (String purchaseline : purchaseLines){
            messages.append( "INPUT: "+ purchaseline +"\n");
            String[] partsOfPurchase  = purchaseline.split("\t");

            String type = partsOfPurchase[0];
            String[] moneyList = partsOfPurchase[1].split(" ");

            int totalMoney = 0;
            for (String moneyStr : moneyList) {
                try {
                    int money = Integer.parseInt(moneyStr);
                    totalMoney += money;
                } catch (NumberFormatException e) {
                    System.out.println("INFO: Invalid money value: " + moneyStr);
                }
            }
            int moneyLoaded = totalMoney;

            String choice = partsOfPurchase[2];

            double value = Double.parseDouble(partsOfPurchase[3]);
            Purchase purchase = new Purchase(type, moneyLoaded, choice, value);
            int resultSell = gmm.sellProduct(purchase, messages);
        }
        // Write loadingMessages and machine content to output file
        FileOutput.writeToFile(args[2],  messages.toString() + gmm.toString(), true, true);
    }
}
