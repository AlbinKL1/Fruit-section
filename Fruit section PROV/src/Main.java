import java.util.*;
//Namn: Albin Kaufeldt Lönn
//Mail: albin.kaufeldt.lonn@iths.se

//Main class
public class Main {
    public static Scanner input = new Scanner(System.in);
    private static final Date currentDate = new Date();
    private static boolean exitMenu = false;
    public static List<Product> productList = new ArrayList<Product>();
    //Main menu.
    private static void displayMainMenu() {
        String [] menuChoices = {
                "\n1 - Add product.",
                "2 - Search for product.",
                "3 - Navigate to a product.",
                "4 - Buy a product.",
                "5 - Edit,remove and discount a product.",
                "0 - Exit program entirely."

        };
        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);
        }
    }

    public static void main(String[] args) {
        productExamples();
        System.out.println("\nWelcome to Willy´s fruit and vegetable section. " + currentDate + "\nPlease enter a command.");

        do {
            displayMainMenu();
            int choice = Product.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addProductMenu();
                case 2 -> searchForProduct();
                case 3 -> navigateToProduct();
                case 4 -> buyProduct();
                case 5 -> editAProductMenu();
                case 0 -> exitMenu = true;
                default -> System.out.println("Invalid choice. Please select a number between 1 and 4.");
            }

        } while (!exitMenu);
        System.out.println("\n"+ "Goodbye!! Thank you for using this program");
        input.close();
    }
    private static void searchForProduct() {

        System.out.print("\nEnter the products name or category: ");
        String enteredInput = input.nextLine();
        boolean found = false;
        boolean foundCategory = false;

        for (Product product : productList) {
            if (product.getProductCategory().equalsIgnoreCase(enteredInput)) {
                foundCategory = true;
                break;
            }
        }
        if (foundCategory) {
            for (Product product : productList) {
                if (product.getProductCategory().equalsIgnoreCase(enteredInput)) {
                    System.out.println("- " + product.getName());
                    found = true;
                }
            }
        } else {
            for (Product product : productList) {
                if (product.getName().equalsIgnoreCase(enteredInput)) {
                    System.out.println("Product found: " + product);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }
    private static Product navigateToProduct() {
        Product selectedProduct = null;

        ArrayList<String>tempCategory = new ArrayList<String>();

        for (Product product : productList){
            if (!tempCategory.contains(product.getProductCategory())){
                tempCategory.add(product.getProductCategory());
            }
        }
        System.out.println("\nAvailable categories: ");
        for (int i = 0; i < tempCategory.size(); i++) {
            System.out.println((i + 1) + ". " + tempCategory.get(i));
        }
        int categoryChoice = Product.validateIntInput("Enter the corresponding number to the category you wean to explore: ");
        if (categoryChoice >=1 && categoryChoice <= tempCategory.size()){
            String selectedCategory = tempCategory.get(categoryChoice - 1);
            System.out.println( "\n" + "Product´s in selected category: " +  selectedCategory);

            ArrayList<Product> tempProductsInCategory = new ArrayList<Product>();
            for (Product product : productList) {
                if (product.getProductCategory().equalsIgnoreCase(selectedCategory)) {
                    tempProductsInCategory.add(product);
                }
            }

            for (int i = 0; i < tempProductsInCategory.size(); i++) {
                System.out.println((i + 1) + ". " + tempProductsInCategory.get(i).getName());
            }

            int productChoice = Product.validateIntInput("Enter the corresponding number to the product you want to view: ");
            if (productChoice >= 1 && productChoice <= tempProductsInCategory.size()) {
                selectedProduct = tempProductsInCategory.get(productChoice - 1);
                System.out.println("\nSelected product: " + selectedProduct.getName() +
                        " - Price kg: " + selectedProduct.getPriceByKg() +
                        " - Price unit: " + selectedProduct.getPriceByUnit());

            } else {
                System.out.println("Invalid choice. Please select a valid product number.");
            }
        }else {
            System.out.println("Invalid choice. Please select a valid category number.");
        }

        return selectedProduct;

    }
    private static void buyProduct (){

        Product selectedProduct = navigateToProduct();

        String productName = selectedProduct.getName();

        int buyChoice = Product.validateIntInput("Enter 1 to buy price/kg, 2 to buy price/unit: ");
        double numberOfProducts = Product.validateDoubleInput("Enter the amount of product you want to buy 0.1-100: ");

        switch (buyChoice) {
            case 1 -> {
                double buyPriceByKg = selectedProduct.getPriceByKg() * (numberOfProducts);
                String kg = (numberOfProducts > 1) ? "kgs": "kg";
                System.out.println("Total price for " + numberOfProducts + kg  + " of " + productName + "s: " + buyPriceByKg);
            }
            case 2 -> {
                double buyPriceByUnit = selectedProduct.getPriceByUnit() * (numberOfProducts);
                String s = (numberOfProducts > 1) ? "s": "";
                System.out.println("Total price for " + numberOfProducts + " " + productName + s + ": " + buyPriceByUnit);
            }
            default -> System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    //Add product´s menu.
    private static void displayAddProductMenu() {
        String [] menuChoices = {
                "\n1 - Add product with name.",
                "2 - Add product with name and price.",
                "3 - Add product with name, price and category.",
                "4 - Go back to main menu.",
                "0 - Exit program entirely."
        };
        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);
        }
    }
    private static void addProductMenu() {
        do {
            displayAddProductMenu();
            int choice = Product.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addOnlyProductName();
                case 2 -> addProductWithNameAndPrice();
                case 3 -> addProductWithNamePriceAndCategory();
                case 4 -> {
                    return;
                }
                case 0 -> exitMenu = true;
                default -> System.out.println("Invalid choice. Please select a number between 1 and 4.");
            }

        } while (!exitMenu);
        System.out.println("\n"+ "Goodbye!! Thank you for using this program");
        input.close();
    }
    private static void addOnlyProductName() {
        while (true) {
            System.out.print("\nEnter product name: ");
            String name = input.nextLine();
            //Detta är coolt
            if (name.matches("[a-zA-Z ]+") && !name.isEmpty()) {
                Product vegetable = new Product(name);
                productList.add(vegetable);
                System.out.println("Product added: " + name);
                break;
            } else {
                System.out.println("You need to enter a name.");
            }
        }
    }
    private static void addProductWithNameAndPrice() {
        while (true) {
            System.out.print("\nEnter product name: ");
            String name = input.nextLine();
            if (name.matches("[a-zA-Z ]+") && !name.isEmpty()) {

                double priceByKg = Product.validateDoubleInput("Enter the product´s price by kg: ");
                double priceByUnit = Product.validateDoubleInput("Enter the product´s price by unit: ");

                Product vegetable = new Product(name, priceByKg, priceByUnit);
                productList.add(vegetable);
                System.out.println("Product added: " + name);
                break;
            } else {
                System.out.println("You need to enter a name.");
            }
        }
    }
    private static void addProductWithNamePriceAndCategory() {
        while (true) {
            System.out.print("\nEnter product name: ");
            String name = input.nextLine();
            if (name.matches("[a-zA-Z ]+") && !name.isEmpty()) {

                double priceByKg = Product.validateDoubleInput("Enter the product´s price by kg: ");
                double priceByUnit = Product.validateDoubleInput("Enter the product´s price by unit: ");

                System.out.print("Enter the product´s category: ");
                String productCategory = input.nextLine();

                Product vegetable = new Product(name, priceByKg, priceByUnit, productCategory);
                productList.add(vegetable);
                System.out.println("Product added: " + name);
                break;
            } else {
                System.out.println("You need to enter a name.");
            }
        }
    }

    //Edit,remove and discount a product menu.
    private static void displayEditAProductMenu() {
        String [] menuChoices = {
                "\n1 - Edit a product.",
                "2 - Remove a product.",
                "3 - Add a discount on a product.",
                "4 - Go back to main menu.",
                "0 - Exit program entirely."

        };
        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);
        }
    }
    private static void editAProductMenu(){
        do {
            displayEditAProductMenu();
            int choice = Product.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> editAProduct();
                case 2 -> removeAProduct();
                case 3 -> discountAProduct();
                case 4 -> {
                    return;
                }
                case 0 -> exitMenu = true;
                default -> System.out.println("Invalid choice. Please select a number between 1 and 4.");
            }

        } while (!exitMenu);
        System.out.println("\n"+ "Goodbye!! Thank you for using this program");
        input.close();
    }
    private static void editAProduct(){

        System.out.print("\nName the product you want to edit: ");
        String enteredInput = input.nextLine();
        boolean productFound = false;

        for (Product editedProduct : productList) {
            if (editedProduct.getName().equalsIgnoreCase(enteredInput)) {
                productFound = true;

                System.out.println("Current name: " + editedProduct.getName());
                System.out.print("Give the product a new name: ");
                String newName = input.nextLine();

                if (newName.matches("[a-zA-Z ]+") && !newName.isEmpty()) {

                    editedProduct.setName(newName);
                    System.out.println("Current price by kg: " + editedProduct.getPriceByKg());
                    double newPriceByKg = Product.validateDoubleInput("Enter the product's new price by kg: ");
                    editedProduct.setPriceByKg(newPriceByKg);
                    System.out.println("Current price by unit: " + editedProduct.getPriceByUnit());
                    double newPriceByUnit = Product.validateDoubleInput("Enter the product's new price by unit: ");
                    editedProduct.setPriceByUnit(newPriceByUnit);
                    System.out.println("Current category: " + editedProduct.getProductCategory());
                    System.out.print("Give the product a new category: ");
                    String newCategory = input.nextLine();
                    editedProduct.setProductCategory(newCategory);
                    System.out.println("Product details updated.");

                } else {
                    System.out.println("Invalid product name. Please enter a valid name.");
                }

                break;
            }
        }

        if (!productFound) {
            System.out.println("Product not found.");
        }
    }
    private static void removeAProduct(){
        System.out.print("\nEnter the product name or category to remove: ");
        String enteredInput = input.nextLine();
        boolean found = false;

        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(enteredInput)) {
                found = true;
                productList.remove(product);
                System.out.println("Product removed successfully: " + product);
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }
    private static void discountAProduct() {

        Product selectedProduct = navigateToProduct();

        int discountChoice = Product.validateIntInput("Enter 1 to apply discount on price/kg, 2 to apply discount on price/unit: ");

        double discountPercentage = Product.validateDoubleInput("Enter the discount percentage 1-100: ");

        if (selectedProduct != null) {

            if (discountChoice == 1) {
                double discountedPriceByKg = selectedProduct.getPriceByKg() * (1 - discountPercentage / 100);
                selectedProduct.setPriceByKg(discountedPriceByKg);
                System.out.println("Discounted price/kg: " + discountedPriceByKg);

            } else if (discountChoice == 2) {
                double discountedPriceByUnit = selectedProduct.getPriceByUnit() * (1 - discountPercentage / 100);
                selectedProduct.setPriceByUnit(discountedPriceByUnit);
                System.out.println("Discounted price/unit: " + discountedPriceByUnit);

            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } else {
            System.out.println("Invalid product selection.");
        }
    }
    private static void productExamples (){
        productList.add(new Product("Banana", 23.95, 4.95 , "Fruit" ));
        productList.add(new Product("Apple", 29.95, 5.95 , "Fruit"));
        productList.add(new Product("Pear", 36.95, 6.95 , "Fruit"));
        productList.add(new Product("Strawberry", 159.95, 1.95 , "Fruit"));
        productList.add(new Product("Mango", 59.95 , 5.95 , "Fruit"));
        productList.add(new Product("Kiwi" , 101.95 , 5.95 , "Fruit"));
        productList.add(new Product("Aubergine", 53.95, 15.95, "Fruit"));
        productList.add(new Product("Cucumber" , 35.95 , 11.95,  "Vegetable"));
        productList.add(new Product("Lettuce" , 99.95 , 19.95, "Vegetable"));
        productList.add(new Product("Tomato", 34.95 , 4.40, "Vegetable"));
        productList.add(new Product("Broccoli" , 75.95, 18.95, "Vegetable"));
        productList.add(new Product("Zucchini" , 44.95, 15.95, "Vegetable"));
        productList.add(new Product("Yellow onion", 29.95 , 2.95, "Vegetable" ));
        productList.add(new Product("Red onion" , 22.95, 2.25, "Vegetable"));
        productList.add(new Product("Potato" , 11.95, 1.15, "Root vegetable"));
        productList.add(new Product("Sweet potato" , 29.95, 2.95, "Root vegetable"));
        productList.add(new Product("Cabbage", 114.95, 22.95, "Root vegetable"));
        productList.add(new Product("Carrot" , 17.95, 1.75, "Root vegetable"));
        productList.add(new Product("Rutabaga" , 34.95, 26.25, "Root vegetable"));
        productList.add(new Product("Mushroom" , 59.95, 14.95, "Fungus"));
        productList.add(new Product("Chantelle" , 449.95, 66.95, "Fungus" ));
        productList.add(new Product("King Bolette,", 1264.95, 37.95, "Fungus"));
    }
}
