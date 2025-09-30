class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void makeSound() {
        System.out.println(name + " makes a sound!");
    }

    void sleep() {
        System.out.println(name + " is sleeping...");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " says: Woof!");
    }

    void fetch() {
        System.out.println(name + " is fetching a ball!");
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}

public class DemoInheritancePolymorphism {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Kitty");

        dog.makeSound();
        cat.makeSound();
        dog.sleep();

        Animal a1 = new Dog("Rex");
        Animal a2 = new Cat("Mimi");

        a1.makeSound();
        a2.makeSound();

        Dog d2 = new Dog("Charlie");
        d2.sleep();
        d2.makeSound();

        Animal animalDog = new Dog("Rocky");
        if (animalDog instanceof Dog) {
            Dog realDog = (Dog) animalDog;
            realDog.fetch();
        }

        Animal justAnimal = new Animal("Plain Animal");
        justAnimal.makeSound();
        // Dog wrongDog = (Dog) justAnimal; // runtime error
    }
}
