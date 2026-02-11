public class Main {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Dice.Die die = new Dice.Die(6);
        System.out.println(dice);
        System.out.println(die);
        System.out.println("Placeholder");

        RPG character = new RPG ("ALEX", 1, "melee");
        character.rollStats();
        System.out.println("\n" + character);
    }
}

