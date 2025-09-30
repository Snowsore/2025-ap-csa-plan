class Product {
    String name;
    double basePrice;

    Product(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    double finalPrice() {
        return basePrice;
    }

    void printInfo() {
        System.out.println(name + " costs $" + finalPrice());
    }
}

class Book extends Product {
    Book(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    double finalPrice() {
        return basePrice * 0.9;
    }
}

class Food extends Product {
    int daysToExpire;

    Food(String name, double basePrice, int daysToExpire) {
        super(name, basePrice);
        this.daysToExpire = daysToExpire;
    }

    @Override
    double finalPrice() {
        if (daysToExpire <= 2) {
            return basePrice * 0.8;
        }
        return basePrice;
    }
}

class Electronic extends Product {
    int warrantyYears;

    Electronic(String name, double basePrice, int warrantyYears) {
        super(name, basePrice);
        this.warrantyYears = warrantyYears;
    }

    @Override
    double finalPrice() {
        return basePrice + warrantyYears * 20;
    }
}

public class StoreDemo {
    public static void main(String[] args) {
        Product p1 = new Book("Java Textbook", 50.0);
        Product p2 = new Food("Milk", 5.0, 1);
        Product p3 = new Electronic("Headphones", 100.0, 2);

        p1.printInfo();
        p2.printInfo();
        p3.printInfo();

        if (p2 instanceof Food) {
            Food f = (Food) p2;
            System.out.println(f.name + " expires in " + f.daysToExpire + " day(s).");
        }
    }
}
