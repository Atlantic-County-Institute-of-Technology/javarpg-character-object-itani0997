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
        this.armorClass = 0;
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
        dice.addDice(4, 6);
        dice.rollAll();
        dice.sortDice();
        dice.removeDie(0);
        int val = dice.getAllValues();
        dice.clear();
        return val;
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
        String chara = name.toUpperCase() + " " + "(Level " + level + " " + characterType + ")\n";
        chara += "HP: " + hitPoints + " | AC: " + armorClass + "\n";
        chara += "STR: " + STR + " (+" + getAbilityModifier(STR) + 
                ") | DEX: " + DEX + " (+" + getAbilityModifier(DEX) + 
                ") | CON: " + CON + " (+" + getAbilityModifier(CON) + ")\n";
        chara += "INT: " + INT + " (+" + getAbilityModifier(INT) + 
                ") | WIS: " + WIS + " (+" + getAbilityModifier(WIS) + 
                ") | CHA: " + CHA + " (+" + getAbilityModifier(CHA) + ")";
        return chara;
    }
}
