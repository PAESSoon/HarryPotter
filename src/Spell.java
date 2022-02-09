import java.util.Random;
import java.util.stream.IntStream;

public class Spell {

	private String name;
	private double minDamage;
	private double maxDamage;
	private double chanceOfSucess;

	public Spell(String name, double minDamage, double maxDamage, double chanceOfSucess) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.chanceOfSucess = chanceOfSucess;
	}

	public String getName() {
		return name;
	}

	public double getMagicDamage(int seed) {

		double result;

		Random generatorA = new Random(seed);

		IntStream ints = generatorA.ints(1, 0, 100);

		int[] intArr = ints.toArray();

		double randomDoubleGenerated = intArr[0] / 100;

		if (randomDoubleGenerated > this.chanceOfSucess) {

			result = 0;
			;

		} else {

			result = this.minDamage + (this.maxDamage - this.minDamage) * generatorA.nextDouble();

		}

		return result;
	}

	public String toString() {

		// String result = this.name + " " + this.minDamage + " " + this.maxDamage + " "
		// + this.chanceOfSucess;
		String result = "Name: " + this.name + " Damage: " + this.minDamage + "-" + this.maxDamage + " Chance: "
				+ this.chanceOfSucess + "%";
		return result;
	}

}
