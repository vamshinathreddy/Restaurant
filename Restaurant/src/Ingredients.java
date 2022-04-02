import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Ingredients extends Account {
    public Product[] products;

    public Ingredients() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/keshavagarivamshinathreddy/Downloads/ingredients.txt"));
        products = new Product[0];
        while (scanner.hasNext()) {
            String name = scanner.next();
            double quantity = scanner.nextInt();
            double price = scanner.nextInt();
            Product newProduct = new Product(name, quantity, price);
            products = addProduct(products, newProduct);
        }
    }

    public void viewIngredients() {
        for (Product product1 : products) {
            System.out.println(product1.name + " " + product1.quantity);
        }
    }

    public boolean order(String select, double quan) {
        boolean b = false;
        for (Ingredients.Product product : products) {
            if (select.equalsIgnoreCase(product.name)) {
                product.quantity = product.quantity + quan;
                if ((amount - quan * product.price) < 0) return b;
                else {
                    totalexpense = totalexpense + quan * product.price;
                    amount = amount - totalexpense;
                    b = true;
                }
            }
        }
        return b;
    }

    public void orderfrom(String select, double quan) {
        for (Ingredients.Product product : products) {
            if (select.equalsIgnoreCase(product.name)) {
                product.quantity = product.quantity - quan;
            }
        }
    }

    public static class Product {
        protected String name;
        protected double quantity;
        protected double price;

        public Product(String name, double quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("Name:%s\r\nQuantity:%d\r\nPrice:%d\r\n", name, quantity, price);
        }

    }

    private static Product[] addProduct(Product[] products, Product producttoAdd) {
        Product[] newProducts = new Product[products.length + 1];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[newProducts.length - 1] = producttoAdd;
        return newProducts;
    }

}
