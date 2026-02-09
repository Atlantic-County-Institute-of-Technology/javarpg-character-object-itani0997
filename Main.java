public class Main {
    public static void main(String[] args) {
        System.out.println("Java RPG Character Test");

        RPG character = new RPG("Adventurer", 1, "melee");
        character.rollStats();
        System.out.println(character);
    }
}