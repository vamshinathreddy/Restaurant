import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Receipe extends Account {
    public Dish[] dishes;
    Ingredients ingredients = new Ingredients();
    Ingredients.Product[] products = ingredients.products;
    private boolean b = false;
    HashMap<String, Double> map = new HashMap<String, Double>();
    Scanner scanner = new Scanner(System.in);

    public Receipe() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/keshavagarivamshinathreddy/Downloads/receipe.txt"));
        dishes = new Dish[0];
        while (scanner.hasNext()) {
            String receipe1 = scanner.next();
            if (receipe1.equalsIgnoreCase("sandwich") || receipe1.equalsIgnoreCase("MasalaDosa")) {
                String ingredient1 = scanner.next();
                double quantity1 = scanner.nextDouble();
                String ingredient2 = scanner.next();
                double quantity2 = scanner.nextDouble();
                String ingredient3 = scanner.next();
                double quantity3 = scanner.nextDouble();
                String ingredient4 = scanner.next();
                double quantity4 = scanner.nextDouble();
                double dishprice = scanner.nextDouble();
                Dish newProduct = new Dish(receipe1, ingredient1, quantity1, ingredient2, quantity2, ingredient3, quantity3, ingredient4, quantity4, dishprice);
                dishes = addProduct(dishes, newProduct);
            }
            if (receipe1.equalsIgnoreCase("coffee") || receipe1.equalsIgnoreCase("tea") || receipe1.equalsIgnoreCase("filtercoffee")) {
                String ingredient1 = scanner.next();
                double quantity1 = scanner.nextDouble();
                String ingredient2 = scanner.next();
                double quantity2 = scanner.nextDouble();
                double dishprice = scanner.nextDouble();
                Dish newProduct = new Dish(receipe1, ingredient1, quantity1, ingredient2, quantity2, null, 0.00, null, 0.00, dishprice);
                dishes = addProduct(dishes, newProduct);
            }
        }
    }

    public boolean addSandwich1(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("sandwich")) {
                for (Ingredients.Product prod : products) {
                    if (prod.name.equalsIgnoreCase(product.ingredient1)) {
                        if (prod.quantity >= product.quantity1 * count) {
                            ingredients.orderfrom(prod.name, product.quantity1 * count);
                        } else {
                            map.put(prod.name, product.quantity1 * count - prod.quantity);
                            b = true;
                        }
                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient2)) {
                        if (prod.quantity >= product.quantity2 * count) {
                            ingredients.orderfrom(prod.name, product.quantity2 * count);
                        } else {
                            map.put(prod.name, product.quantity2 * count - prod.quantity);
                            b = true;
                        }

                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient3)) {
                        if (prod.quantity >= product.quantity3 * count) {
                            ingredients.orderfrom(prod.name, product.quantity3 * count);
                        } else {
                            map.put(prod.name, product.quantity3 * count - prod.quantity);
                            b = true;
                        }

                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient4)) {
                        if (prod.quantity >= product.quantity4 * count) {
                            ingredients.orderfrom(prod.name, product.quantity4 * count);
                        } else {
                            map.put(prod.name, product.quantity4 * count - prod.quantity);
                            b = true;
                        }

                    }
                }
            }
        }
        return b;
    }

    public Ingredients orderSandwich(int count) {
        b = addSandwich1(count);
        if (b) {
            System.out.println("Insufficient ingredients for Dish:\n");
            System.out.println("If you want to continue\t Enter yes else no");
            String red = scanner.next();
            if (red.equalsIgnoreCase("yes")) {
                for (Map.Entry<String, Double> set :
                        map.entrySet()) {
                    ingredients.order(set.getKey(), set.getValue());
                }
                addSandwich1(count);
                Sandwichupdate(count);
                System.out.println("Order placed");
            }

        }
        if (!b) {
            System.out.println("ordered");
            Sandwichupdate(count);

        }
        return ingredients;
    }

    public void Sandwichupdate(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("sandwich")) {
                sandwichSales = sandwichSales + product.disnprice * count;
                amount = amount + product.disnprice * count;
            }
        }
    }

    public void teaUpdate(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("tea")) {
                teaSales = teaSales + product.disnprice * count;
                amount = amount + product.disnprice * count;
            }
        }
    }

    public void coffeeUpdate(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("coffee")) {
                coffeeSales = coffeeSales + product.disnprice * count;
                amount = amount + product.disnprice * count;
            }
        }
    }

    public void filterCoffeeUpdate(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("filtercoffee")) {
                filterCoffeeSales = filterCoffeeSales + product.disnprice * count;
                amount = amount + product.disnprice * count;
            }
        }
    }

    public void masaladosaUpdate(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("masaladosa")) {
                masaladosaSales = masaladosaSales + product.disnprice * count;
                amount = amount + product.disnprice * count;
            }
        }
    }

    public double viewTotalDishes() {
        double sandwiches = 0, masaladosa = 0, tea = 0, coffee = 0, filtercoffee = 0;
        System.out.println("List of Dishes sold \n");
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("sandwich")) {
                System.out.println("Sandwich" + " " + sandwichSales / product.disnprice + "Amount" + " " + (product.disnprice) * (sandwichSales / product.disnprice));
                sandwiches = product.disnprice;
            }
            if (product.receipe1.equalsIgnoreCase("tea")) {
                System.out.println("tea" + " " + teaSales / product.disnprice + "Amount" + " " + (product.disnprice) * (teaSales / product.disnprice));
                tea = product.disnprice;
            }
            if (product.receipe1.equalsIgnoreCase("masaladosa")) {
                System.out.println("masaladosa" + " " + masaladosaSales / product.disnprice + "Amount" + " " + (product.disnprice) * (masaladosaSales / product.disnprice));
                masaladosa = product.disnprice;
            }
            if (product.receipe1.equalsIgnoreCase("coffee")) {
                System.out.println("coffee" + " " + coffeeSales / product.disnprice + "Amount" + " " + (product.disnprice) * (coffeeSales / product.disnprice));
                coffee = product.disnprice;
            }
            if (product.receipe1.equalsIgnoreCase("filtercoffee")) {
                System.out.println("filtercoffee" + " " + filterCoffeeSales / product.disnprice + "Amount" + " " + (product.disnprice) * (filterCoffeeSales / product.disnprice));
                filtercoffee = product.disnprice;
            }
        }
        double total = sandwichSales / sandwiches + masaladosaSales / masaladosa + teaSales / tea + coffeeSales / coffee + filterCoffeeSales / filtercoffee;
        System.out.println("Total dishes sold so far" + " " + total);
        return total;
    }

    public boolean addCoffee(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("coffee")) {
                for (Ingredients.Product prod : products) {
                    if (prod.name.equalsIgnoreCase(product.ingredient1)) {
                        if (prod.quantity >= product.quantity1 * count) {
                            ingredients.orderfrom(prod.name, product.quantity1 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity1 * count - prod.quantity);
                            b = true;
                        }
                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient2)) {
                        if (prod.quantity >= product.quantity2 * count) {
                            ingredients.orderfrom(prod.name, product.quantity2 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity2 * count - prod.quantity);
                            b = true;
                        }

                    }
                }
            }
        }
        return b;
    }

    public Ingredients orderCoffee(int count) {
        b = addCoffee(count);
        if (b) {
            System.out.println("Insufficient ingredients for Dish:\n");
            System.out.println("If you want to continue\t Enter yes else no");
            String red = scanner.next();
            if (red.equalsIgnoreCase("yes")) {
                for (Map.Entry<String, Double> set :
                        map.entrySet()) {
                    ingredients.order(set.getKey(), set.getValue());
                }
                addCoffee(count);
                coffeeUpdate(count);
                System.out.println("Order placed");
            }

        }
        if (!b) {
            System.out.println("ordered");
            coffeeUpdate(count);

        }
        return ingredients;
    }

    public boolean addFilterCoffee(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("filtercoffee")) {
                for (Ingredients.Product prod : products) {
                    if (prod.name.equalsIgnoreCase(product.ingredient1)) {
                        if (prod.quantity >= product.quantity1 * count) {
                            ingredients.orderfrom(prod.name, product.quantity1 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity1 * count - prod.quantity);
                            b = true;
                        }
                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient2)) {
                        if (prod.quantity >= product.quantity2 * count) {
                            ingredients.orderfrom(prod.name, product.quantity2 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity2 * count - prod.quantity);
                            b = true;
                        }

                    }
                }
            }
        }
        return b;
    }

    public Ingredients orderFilterCoffee(int count) {
        b = addFilterCoffee(count);
        if (b) {
            System.out.println("Insufficient ingredients for Dish:\n");
            System.out.println("If you want to continue\t Enter yes else no");
            String red = scanner.next();
            if (red.equalsIgnoreCase("yes")) {
                for (Map.Entry<String, Double> set :
                        map.entrySet()) {
                    ingredients.order(set.getKey(), set.getValue());
                }
                addFilterCoffee(count);
                filterCoffeeUpdate(count);
                System.out.println("Order placed");
            }

        }
        if (!b) {
            System.out.println("ordered");
            filterCoffeeUpdate(count);

        }
        return ingredients;
    }


    public boolean addTea(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("tea")) {
                for (Ingredients.Product prod : products) {
                    if (prod.name.equalsIgnoreCase(product.ingredient1)) {
                        if (prod.quantity >= product.quantity1 * count) {
                            ingredients.orderfrom(prod.name, product.quantity1 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity1 * count - prod.quantity);
                            b = true;
                        }
                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient2)) {
                        if (prod.quantity >= product.quantity2 * count) {
                            ingredients.orderfrom(prod.name, product.quantity2 * count);
                            b = false;
                        } else {
                            map.put(prod.name, product.quantity2 * count - prod.quantity);
                            b = true;
                        }

                    }
                }
            }
        }
        return b;
    }

    public Ingredients orderTea(int count) {
        b = addTea(count);
        if (b) {
            System.out.println("Insufficient ingredients for Dish:\n");
            System.out.println("If you want to continue\t Enter yes else no");
            String red = scanner.next();
            if (red.equalsIgnoreCase("yes")) {
                for (Map.Entry<String, Double> set :
                        map.entrySet()) {
                    ingredients.order(set.getKey(), set.getValue());
                }
                addTea(count);
                teaUpdate(count);
                System.out.println("Order placed");

            }

        }
        if (!b) {
            System.out.println("ordered");
            teaUpdate(count);

        }
        return ingredients;
    }


    public boolean addMasalaDosa(int count) {
        for (Dish product : dishes) {
            if (product.receipe1.equalsIgnoreCase("masaladosa")) {
                for (Ingredients.Product prod : products) {
                    if (prod.name.equalsIgnoreCase(product.ingredient1)) {
                        if (prod.quantity >= product.quantity1 * count) {
                            ingredients.orderfrom(prod.name, product.quantity1 * count);
                        } else {
                            map.put(prod.name, product.quantity1 * count - prod.quantity);
                            b = true;
                        }
                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient2)) {
                        if (prod.quantity >= product.quantity2 * count) {
                            ingredients.orderfrom(prod.name, product.quantity2 * count);
                        } else {
                            map.put(prod.name, product.quantity2 * count - prod.quantity);
                            b = true;
                        }

                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient3)) {
                        if (prod.quantity >= product.quantity3 * count) {
                            ingredients.orderfrom(prod.name, product.quantity3 * count);
                        } else {
                            map.put(prod.name, product.quantity3 * count - prod.quantity);
                            b = true;
                        }

                    }
                    if (prod.name.equalsIgnoreCase(product.ingredient4)) {
                        if (prod.quantity >= product.quantity4 * count) {
                            ingredients.orderfrom(prod.name, product.quantity4 * count);
                        } else {
                            map.put(prod.name, product.quantity4 * count - prod.quantity);
                            b = true;
                        }

                    }
                }
            }
        }
        return b;
    }

    public Ingredients orderMasalaDosa(int count) {
        b = addMasalaDosa(count);
        if (b) {
            System.out.println("Insufficient ingredients for Dish:\n");
            System.out.println("If you want to continue\t Enter yes else no");
            String red = scanner.next();
            if (red.equalsIgnoreCase("yes")) {
                for (Map.Entry<String, Double> set :
                        map.entrySet()) {
                    ingredients.order(set.getKey(), set.getValue());
                }
                addMasalaDosa(count);
                masaladosaUpdate(count);
                System.out.println("Order placed");
            }

        }
        if (!b) {
            System.out.println("ordered");
            masaladosaUpdate(count);

        }
        return ingredients;
    }


    public static class Dish {
        protected String receipe1;
        protected String ingredient1;
        protected double quantity1;
        protected String ingredient2;
        protected double quantity2;
        protected String ingredient3;
        protected double quantity3;
        protected String ingredient4;
        protected double quantity4;
        protected double disnprice;

        public Dish(String receipe1, String ingredient1, double quantity1, String ingredient2, double quantity2, String ingredient3, double quantity3, String ingredient4, double quantity4, double disnprice) {
            this.receipe1 = receipe1;
            this.ingredient1 = ingredient1;
            this.quantity1 = quantity1;
            this.ingredient2 = ingredient2;
            this.quantity2 = quantity2;
            this.ingredient3 = ingredient3;
            this.quantity3 = quantity3;
            this.ingredient4 = ingredient4;
            this.quantity4 = quantity4;
            this.disnprice = disnprice;
        }

        @Override
        public String toString() {
            return String.format("Name:%s\r\nQuantity:%d\r\nPrice:%d\r\n", receipe1, ingredient1, quantity1);
            //return String.format("Name:%s\r\nQuantity:%d\r\n",name,quantity);
        }

    }

    private static Dish[] addProduct(Dish[] products, Dish producttoAdd) {
        Dish[] newProducts = new Dish[products.length + 1];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[newProducts.length - 1] = producttoAdd;
        return newProducts;
    }

}

