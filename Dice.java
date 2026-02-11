// Dice.java
// @description: A class that handles multiple dice of any number of sides using an arraylist
// @author: pcostjr
// created: 1.7.2026
// last update: 1.20.2026
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

// a class for managing multiple dice with any number of sides.
// dice are stored persistently and can be rolled individually or together.
public class Dice {
    // instance variables
    private ArrayList<Die> dice;
    
    // creates an empty collection of dice
    public Dice() {
        this.dice = new ArrayList<>();
    }

    // adds a single die with sides
    public int addDie(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException("Die must have at least 1 side");
        }
        dice.add(new Die(sides));
        return dice.size() - 1;
    }

    // adds multiple dice with the specified number of sides to the arraylist
    public void addDice(int count, int sides) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        for (int i = 0; i < count; i++) {
            addDie(sides);
        }
    }

    // rolls a specific die at the target index, if it exists.
    public int rollDie(int index) {
        if (index < 0 || index >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + index);
        }
        return dice.get(index).roll();
    }
    

    // rolls all dice and returns the sum total of the values
    public int rollAll() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll();
        }
        return sum;
    }

    // gets the current value of a specific die without rolling it
    // might not be needed but I had this already
    public int getDieValue(int index) {
        if (index < 0 || index >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + index);
        }
        return dice.get(index).getValue();
    }
    
    // returns the sum total of dice without rolling them
    public int getAllValues() {
        int sum = 0;
        for (int i = 0; i < dice.size(); i++) {
            sum+= dice.get(i).getValue();
        }
        return sum;
    }
    
    // returns the size of the arraylist
    public int size(){
        return dice.size();
    }
    
    // clears all dice from the collection
    public void clear() {
        dice.clear();
    }

    // inner class will only be used by dice, so we can embed it here
    public static class Die implements DieInterface {
        private int sides;
        private int currentValue;
        private Random random;
        
        public Die(int sides) {
            this.sides = sides;
            this.random = new Random();
            this.currentValue = 1; // Initial value
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