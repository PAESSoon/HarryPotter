import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SorcererBattle {

	private static Random random = new Random();
	final static String[] playerArray = {"hermione", "harry", "ron"};
	final static String deathEaterText = "deathEater.txt";

	public static void main(String[] args) throws IOException {
		
		String player = selectPlayer(playerArray);

		SorcererBattle.playGame(player + ".txt", "deathEater.txt", "spells.txt");

	}
	
	public static String selectPlayer(String[] players) throws IOException {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Players Available: ");
		
		for (String item: playerArray) {
			System.out.println(item);
		}
		
		System.out.print("Select your player: ");
		
		String playerSelected = input.nextLine();
		
		return playerSelected;
	
		
	}

	public static void playGame(String player, String deathEater, String spell) {

		Scanner input = new Scanner(System.in);

		// ****** Characters *******
		Character newPlayer = FileIO.readCharacter(player);
		Character newDeathEater = FileIO.readCharacter(deathEater);

		System.out.println("name: " + newPlayer.getName());
		System.out.println("Health: " + newPlayer.getcurrentEnergy());
		System.out.println("Attack: " + newPlayer.getAttackValue());
		System.out.println("Number of wins: " + newPlayer.getnumWins() + "\n");

		System.out.println("name: " + newDeathEater.getName());
		System.out.println("Health: " + newDeathEater.getcurrentEnergy());
		System.out.println("Attack: " + newDeathEater.getAttackValue());
		System.out.println("Number of wins: " + newDeathEater.getnumWins());

		// ****** Spells *******
		ArrayList<Spell> spellsSelection = new ArrayList<Spell>();
		spellsSelection = FileIO.readSpells("spells.txt");

		if (spellsSelection == null) {

			System.out.println("Game will be played without spells");

		} else {

			System.out.println("\nHere are the available spells");

			for (Spell item : spellsSelection) {

				System.out.println(item.toString());
			}

		}

		System.out.print("\n");

		// loop as long as both player and deathEater energy levels are above 0
		while (newPlayer.getcurrentEnergy() > 0 && newDeathEater.getcurrentEnergy() > 0) {

			System.out.println("\nEnter a command: ");

			String command = input.nextLine();

			int randomInt = random.nextInt();

			if (command.equals("attack")) {

				randomInt = random.nextInt();

				Double playerAttackdamage = newPlayer.getAttackDamage(randomInt);

				String playerAttackStr = String.format("%1$.2f", playerAttackdamage);

				System.out.println("\n" + newPlayer.getName() + " attacks for " + playerAttackStr + " damage!");

				newDeathEater.setmaxEnergy(newDeathEater.takeDamage(playerAttackdamage));

				if (newDeathEater.getcurrentEnergy() > 0) {

					// String deathEatercurrentEnergyStr = String.format("%1$.2f",
					// newDeathEater.takeDamage(playerAttackdamage));

					System.out.println(newDeathEater.toString());

					randomInt = random.nextInt();

					Double deathEaterAttackdamage = newDeathEater.getAttackDamage(randomInt);

					String deathEaterAttackStr = String.format("%1$.2f", deathEaterAttackdamage);

					System.out.println(
							"\n" + newDeathEater.getName() + " attacks for " + deathEaterAttackStr + " damage!");

					newPlayer.setmaxEnergy(newPlayer.takeDamage(deathEaterAttackdamage));

					System.out.println(newPlayer.toString());

				} else {

					System.out.println("deathEater is knocked out!");
					break;
				}
			} else if (command.equals("quit")) {

				System.out.println("\nGoodbye!");

				input.close();

				return;

			} else {

				// Cast a Spell

				randomInt = random.nextInt();

				String nameOfSpell = command;
				double damageByPlayer = 0;

				for (Spell item : spellsSelection) {

					if (item.getName().toLowerCase().equals(command.toLowerCase())) {

						damageByPlayer = item.getMagicDamage(randomInt);

						nameOfSpell = item.getName();

						break;
					}
				}

				if (damageByPlayer <= 0) {

					System.out.println(
							"\n" + newPlayer.getName() + " has tried to cast " + command + ", but they failed.");

				} else {

					String playerAttackStr = String.format("%1$.2f", damageByPlayer);

					System.out.println("\n" + newPlayer.getName() + " casts " + nameOfSpell + " dealing "
							+ playerAttackStr + " damage!");

					newDeathEater.setmaxEnergy(newDeathEater.takeDamage(damageByPlayer));

				}

				if (newDeathEater.getcurrentEnergy() > 0) {

					System.out.println(newDeathEater.toString());

					randomInt = random.nextInt();

					Double deathEaterAttackdamage = newDeathEater.getAttackDamage(randomInt);

					String deathEaterAttackStr = String.format("%1$.2f", deathEaterAttackdamage);

					System.out.println(
							"\n" + newDeathEater.getName() + " attacks for " + deathEaterAttackStr + " damage!");

					newPlayer.setmaxEnergy(newPlayer.takeDamage(deathEaterAttackdamage));

					// check new player current energy
					if (newPlayer.getcurrentEnergy() > 0) {

						System.out.println(newPlayer.toString());

					} else {

						System.out.println(newPlayer.getName() + " was knocked out!");
						break;
					}

				} else {

					System.out.println(newDeathEater.getName() + " was knocked out!");
					break;
				}

				if (newPlayer.getcurrentEnergy() <= 0) {

					System.out.println(newPlayer.getName() + " was knocked out!");
					break;

				}
			}
		}

		if (newPlayer.getcurrentEnergy() >= newDeathEater.getcurrentEnergy()) {

			newPlayer.increaseWins();

			System.out.println("\nFantastic! You killed the deathEater!");

			Character newCharacter = FileIO.readCharacter(player);

			newPlayer.setmaxEnergy(newCharacter.getmaxEnergy());

			FileIO.writeCharacter(newPlayer, player);
			System.out.println("Successfuly wrote to file: " + player);
			System.out.println(newPlayer.getName() + " has won " + newPlayer.getnumWins() + " times.");

		} else {

			newDeathEater.increaseWins();

			System.out.println("\nOh no! You lost!");

			Character newCharacter = FileIO.readCharacter(deathEaterText);

			newDeathEater.setmaxEnergy(newCharacter.getmaxEnergy());

			FileIO.writeCharacter(newDeathEater, deathEaterText);
			System.out.println("Successfuly wrote to file: deathEater.txt");
			System.out.println(newDeathEater.getName() + " has won " + newDeathEater.getnumWins() + " times.");
		}

		input.close();

	}
}