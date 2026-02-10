// Dice.java
// @description: A class that handles multiple dice of any number of sides using an arraylist
// @author: pcostjr
// created: 1.7.2026

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Dice {
    private ArrayList<Die> dice;
    
    public Dice() {
        this.dice = new ArrayList<>();
    }

    public int addDie(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException("Die must have at least 1 side");
        }
        dice.add(new Die(sides));
        return dice.size() - 1;
    }

    public void addDice(int count, int sides) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        for (int i = 0; i < count; i++) {
            addDie(sides);
        }
    }

    public int rollDie(int index) {
        if (index < 0 || index >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + index);
        }
        return dice.get(index).roll();
    }

    public int rollAll() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll();
        }
        return sum;
    }

    public int getDieValue(int index) {
        if (index < 0 || index >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + index);
        }
        return dice.get(index).getValue();
    }
    
    public int getAllValues() {
        int sum = 0;
        for (int i = 0; i < dice.size(); i++) {
            sum += dice.get(i).getValue();
        }
        return sum;
    }
    
    public int size() {
        return dice.size();
    }
    
    public void clear() {
        dice.clear();
    }
    
    public void removeDie(int index) {
        if (index < 0 || index >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + index);
        }
        dice.remove(index);
    }
    
    public void sortDice() {
        Collections.sort(dice, new Comparator<Die>() {
            public int compare(Die d1, Die d2) {
                return Integer.compare(d1.getValue(), d2.getValue());
            }
        });
    }

    public static class Die implements DieInterface {
        private int sides;
        private int currentValue;
        private Random random;
        
        public Die(int sides) {
            this.sides = sides;
            this.random = new Random();
            this.currentValue = 1;
        }

        public int roll() {
            return currentValue = random.nextInt(sides) + 1;
        }

        public int getValue() {
            return currentValue;
        }

        public int getSides() {
            return sides;
        }
    }
}
