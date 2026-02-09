public class Main {
    public static void main(String[] args) {
        RPG character = new RPG("TestCharacter", 1, "melee");
        character.rollStats();
        System.out.println(character);
        System.out.println("Alive: " + character.isAlive());
    }
}