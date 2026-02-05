import java.util.*;
public class RPG {
    private String name;
    private String characterType;
    private int level;
    private int hitPoints;
    private int armorClass;
    private int STR, DEX, INT, CON, WIS, CHA;
    private Dice dice;

    public RPG(String name, int level, String characterType) {
        this.name = name;
        this.level = level;
        this.characterType = characterType;
        this.dice = new Dice();


        this.hitPoints = 1;
        this.armorClass = 10;
        STR = DEX = INT = CON = WIS = CHA = 10;
    }

    public int getAbilityModifier(int score) {
        return (score - 10) / 2;
    }

    public int getPrimaryAbilityModifier() {
        if (characterType.equals("melee")) return getAbilityModifier(STR);
        if (characterType.equals("ranged")) return getAbilityModifier(DEX);
        return getAbilityModifier(INT); // magic
    }

    public void calculateBaseHitPoints() {
        hitPoints = 20 + getAbilityModifier(CON);
    }

    public void calculateArmorClass() {
        armorClass = 10 + getPrimaryAbilityModifier();
    }

    public int rollStat() {

        Dice temp = new Dice();
        temp.addDice(4, 6);
        temp.rollAll();


        int[] rolls = new int[4];
        int total = 0;
        int lowest = 7;

        for (int i = 0; i < 4; i++) {
            rolls[i] = temp.getDieValue(i);
            total += rolls[i];
            if (rolls[i] < lowest) lowest = rolls[i];
        }

        return total - lowest;
    }

    public void rollStats() {
        STR = rollStat();
        DEX = rollStat();
        CON = rollStat();
        INT = rollStat();
        WIS = rollStat();
        CHA = rollStat();
        calculateBaseHitPoints();
        calculateArmorClass();
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String toString() {
        return String.format(
                "\tCharacter: %s (Level %d %s)\n" +
                        "\tHP: %d | AC: %d\n" +
                        "\tSTR: %d (+%d) | DEX: %d (+%d) | CON: %d (+%d)\n" +
                        "\tINT: %d (+%d) | WIS: %d (+%d) | CHA: %d (+%d)",
                name, level, characterType, hitPoints, armorClass,
                STR, getAbilityModifier(STR), DEX, getAbilityModifier(DEX),
                CON, getAbilityModifier(CON), INT, getAbilityModifier(INT),
                WIS, getAbilityModifier(WIS), CHA, getAbilityModifier(CHA)
        );
    }
}