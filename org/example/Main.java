package org.example;
import java.util.*;

class Toy {
    int id;
    String name;
    int quantity;
    int weight;

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class ToyStore {
    List<Toy> toys = new ArrayList<>();

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy drawToy() {
        int totalWeight = toys.stream().mapToInt(t -> t.weight).sum();
        int random = new Random().nextInt(totalWeight) + 1;
        int currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.weight;
            if (random <= currentWeight) {
                toy.quantity--;
                if (toy.quantity == 0) {
                    toys.remove(toy);
                }
                return toy;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        store.addToy(new Toy(1, "Car", 5, 20));
        store.addToy(new Toy(2, "Doll", 3, 30));
        store.addToy(new Toy(3, "Ball", 2, 50));

        for (int i = 0; i < 10; i++) {
            Toy toy = store.drawToy();
            if (toy != null) {
                System.out.println("Congratulations, you won a " + toy.name);
            } else {
                System.out.println("Sorry, no toys left");
            }
        }
    }
}