import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Character {

    private String name;
    private double attackValue;
    private double maxEnergy;
    private double currentEnergy;
    private int numWins = 0;
    private static ArrayList<Spell> spells = new ArrayList<Spell>(); // Create an ArrayList object

    public Character(String name, double attackValue, double maxEnergy, int numWins) {
        this.name = name;
        this.attackValue = attackValue;
        this.maxEnergy = maxEnergy;
        this.numWins = numWins;
    }

    public String getName() {
        return name;
    }

    public double getAttackValue() {
        return attackValue;
    }

    public double getmaxEnergy() {
        return maxEnergy;
    }

    public void setmaxEnergy(Double value) {
        this.maxEnergy = value;
    }

    public double getcurrentEnergy() {
        return maxEnergy;
    }

    public int getnumWins() {
        return numWins;
    }

    public String toString() {

        String result = String.format("%1$.2f", getcurrentEnergy());
        return getName() + " current energy is " + result + ".";
    }

    public Double getAttackDamage(int seed) {

        // generate random object with provided seed
        Random generatorA = new Random(seed);

        IntStream ints = generatorA.ints(1, 70, 100);

        int[] intArr = ints.toArray();

        double randomGenerated = getAttackValue() * intArr[0] / 100;

        return randomGenerated;

    }

    public Double takeDamage(Double input) {

        Double newcurrentEnergy = getcurrentEnergy() - input;

        return newcurrentEnergy;
    }

    public void increaseWins() {
        numWins = numWins + 1;
    }

    public void setSpells(ArrayList<Spell> spellsInput) {

        spells = new ArrayList<Spell>(spellsInput);
    }

    public void displaySpells() {

        for (Spell item : spells) {

            System.out.println(item.toString());
        }
    }

    public Double castSpell(String spellName, int y) {

        for (int i = 0; i < spells.size(); i++) {

            if (spells.get(i).getName().toLowerCase().equals(spellName.toLowerCase())) {

                return spells.get(i).getMagicDamage(y);
            }
        }

        return -1.0;
    }

}
