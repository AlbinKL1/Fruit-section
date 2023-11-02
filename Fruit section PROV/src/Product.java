import java.util.InputMismatchException;
import java.util.Scanner;
//Namn: Albin Kaufeldt Lönn
//Mail: albin.kaufeldt.lonn@iths.se

//Product class
public class Product {
private String name;
private double priceByKg;
private double priceByUnit;
private String productCategory;
public static Scanner validatorInput = new Scanner(System.in);

    //Number validators.
    public static double validateDoubleInput(String print) {
        double input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print(print);
                input = validatorInput.nextDouble();
                validatorInput.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                validatorInput.nextLine();
            }
        }
        return input;
    }
    public static int validateIntInput(String print) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print(print);
                input = validatorInput.nextInt();
                validatorInput.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                validatorInput.nextLine();
            }
        }
        return input;
    }

    //Constructors.
    public Product(String name) {
        this.name = name;
        this.priceByKg = 0.0;
        this.priceByUnit = 0.0;
        this.productCategory = "Uncategorized";
    }
    public Product(String name, double priceByKg, double priceByUnit) {
        this.name = name;
        this.priceByKg = priceByKg;
        this.priceByUnit = priceByUnit;
        this.productCategory = "Uncategorized";
    }
    public Product(String name, double priceByKg, double priceByUnit, String productCategory) {
        this.name = name;
        this.priceByKg = priceByKg;
        this.priceByUnit = priceByUnit;
        this.productCategory = productCategory;
    }

    //Getters.
    public String getName() {
        return name;
    }
    public double getPriceByKg() {
        return priceByKg;
    }
    public double getPriceByUnit() {
        return priceByUnit;
    }
    public String getProductCategory() {
        return productCategory;
    }

    //Setters.
    public void setName(String name) {
        this.name = name;
    }
    public void setPriceByKg(double priceByKg) {
        this.priceByKg = priceByKg;
    }
    public void setPriceByUnit(double priceByUnit) {
        this.priceByUnit = priceByUnit;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    //ToString
    @Override
    public String toString() {
        return  "\n" + "Product" +
                "\n" + "Name: " + name +
                "\n" + "Price kg: " + priceByKg +
                "\n" + "Price unit: " + priceByUnit +
                "\n" + "Category: " + productCategory;
    }
}
