import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileIO {

	public static void writeCharacter(Character c, String file) {

		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);

			bw.write(c.getName() + "\n");
			bw.write(c.getAttackValue() + "\n");
			bw.write(c.getmaxEnergy() + "\n");
			bw.write(c.getnumWins() + "");
			bw.close();

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	public static Character readCharacter(String fileName) {

		Character newCharacter = null;
		BufferedReader br = null;

		try {

			File filePlayerInput = new File(fileName);

			br = new BufferedReader(new FileReader(filePlayerInput));

			String strInput;

			String[] inputFromBuffer = new String[4];

			int i = 0;

			while ((strInput = br.readLine()) != null) {

				inputFromBuffer[i] = strInput;

				i++;
			}

			String characterName = inputFromBuffer[0];

			// constructs a Character instance
			newCharacter = new Character(inputFromBuffer[0], Double.parseDouble(inputFromBuffer[1]),
					Double.parseDouble(inputFromBuffer[2]), Integer.parseInt(inputFromBuffer[3]));

			i = 0;

		} catch (FileNotFoundException e) {

			System.out.println("FileNotFoundException");

		} catch (IOException e) {

			System.out.println("IOException");

		}

		return newCharacter;

	}

	public static ArrayList<Spell> readSpells(String fileName) {

		Spell newSpell = null;
		BufferedReader br = null;
		ArrayList<Spell> spellsInput = new ArrayList<Spell>();

		try {

			File filePlayerInput = new File(fileName);

			br = new BufferedReader(new FileReader(filePlayerInput));

			String[] inputArray = new String[4];

			String strInput;

			while ((strInput = br.readLine()) != null) {

				inputArray = strInput.split("	");

				// constructs a Spell instance
				newSpell = new Spell(inputArray[0], Double.parseDouble(inputArray[1]),
						Double.parseDouble(inputArray[2]), Double.parseDouble(inputArray[3]) * 100);
				spellsInput.add(newSpell);

			}

		} catch (FileNotFoundException e) {

			System.out.println("FileNotFoundException");

		} catch (IOException e) {

			System.out.println("IOException");

		}

		// for (int i = 0; i < spellsInput.size(); i++) {
		//
		// System.out.println("name: " + spellsInput.toString());
		// }

		return spellsInput;

	}

}
