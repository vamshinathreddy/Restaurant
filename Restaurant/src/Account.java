import java.io.*;
import java.util.Scanner;

public class Account {
    public static double amount = 5000.0;
    public static double sandwichSales = 0;
    public static double masaladosaSales = 0.00;
    public static double coffeeSales = 0.00;
    public static double teaSales = 0.00;
    public static double filterCoffeeSales = 0.00;
    public static double totalexpense = 0;


    public static void main(String args[]) throws IOException {
        Ingredients ingredients = new Ingredients();
        Receipe receipe = new Receipe();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Choose the option \n 1.View Available Ingredients \n 2.Order Specific Ingredients \n 3.View Total Sales \n 4.View Total Expenses \n 5.View Net Profit \n 6.Place Order \n 7.Exit From the Program \n");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    ingredients.viewIngredients();
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter the ingredient");
                    String select = scanner.next();
                    System.out.println("Please enter the qunatity");
                    int quan = scanner.nextInt();
                    if (amount > 2) {
                        boolean orderId = ingredients.order(select, quan);
                        if (orderId) System.out.println("order successful");
                        else System.out.println("Order not placed for" + select);
                    } else {
                        System.out.println("no funds");
                    }
                    break;
                case 3:
                    receipe.viewTotalDishes();
                    break;
                case 4:
                    System.out.println("total expense so far" + " " + totalexpense);
                    break;
                case 5:
                    double totalDishes = sandwichSales + masaladosaSales + coffeeSales + teaSales + filterCoffeeSales;
                    if ((totalDishes - totalexpense) < 0) System.out.println("Total Profit:" + 0);
                    else System.out.println("Total Profit" + " " + (totalDishes - totalexpense));
                    break;
                case 6:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Please Enter the receipe below");
                    System.out.println("1:Sandwich\n2:MasalaDosa\n3:Coffee\n4:Tea\n5:FilterCoffee");
                    int selectdish = scanner1.nextInt();
                    System.out.println("please select the quantity");
                    int quantity = scanner1.nextInt();
                    switch (selectdish) {
                        case 1:
                            ingredients = receipe.orderSandwich(quantity);
                            continue;
                        case 2:
                            ingredients = receipe.orderMasalaDosa(quantity);
                            continue;
                        case 3:
                            ingredients = receipe.orderCoffee(quantity);
                            continue;
                        case 4:
                            ingredients = receipe.orderFilterCoffee(quantity);
                            continue;
                        case 5:
                            ingredients = receipe.orderTea(quantity);

                            continue;

                    }
                case 7:
                    loop = false;
                    break;
            }
        }
    }
}
