import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	/** Randomly choose a monster from input file to slay
	 * 
	 * @param monsterArr, an ArrayList of Monster
	 * @param rand, a random number generator
	 * @return a randomly chosen Monster
	 */
	public static Monster pickMonster(ArrayList<Monster> monsterArr, Random rand) {
		int index = rand.nextInt(monsterArr.size());
		return monsterArr.get(index);
	} // pickMonster
	
	/** Get the treasure class of the monster
	 * 
	 * @param monster, a Monster
	 * @return the treasure class of the monster, a String
	 */
	public static String fetchTreasureClass(Monster monster) {
		return monster.getTreasureClass();
	} // fetchTreasureClass
	
	/** Randomly generate drop from the monster
	 * 
	 * @param TreasureClass, a String
	 * @param rand, a random number generator
	 * @param treasureMap, an HashMap of String and String array
	 * @return item, a String
	 */
	public static String generateBaseItems(String TreasureClass, Random rand,
			  							 HashMap<String, String[]> treasureMap) {
		String[] itemArr = treasureMap.get(TreasureClass);
		String item = itemArr[rand.nextInt(itemArr.length)];
		
		// Repeatedly look for the base item
		while(treasureMap.containsKey(item)) {
			itemArr = treasureMap.get(item);
			item = itemArr[rand.nextInt(itemArr.length)];
		}
		
		return item;
	} // generateBaseItems
	
	/** Compute base statistic for a base item
	 * 
	 * @param item, a String
	 * @param rand, a random number generator
	 * @param armor, a HashMap of String and int array
	 * @return base stat, an int
	 */
	public static int generateBaseStats(String item, Random rand,
										 HashMap<String, int[]> armor) {
		int[] stats = armor.get(item);
		return stats[0] + rand.nextInt(stats[1] - stats[0] + 1);
	} // generateBaseStats
	
	/** Generate prefixes and suffixes for items with an independent 50% chance of each
	 * 
	 * @param prefix, an ArrayList of Affixes
	 * @param suffix, an ArrayList of Affixes
	 * @param rand, a random number generator
	 * @return ret, an array of 2 Affixes, the first will be the prefix, the second the suffix
	 * In the event that one or both is not generated, the index will contain null
	 */
	public static Affix[] generateAffix(ArrayList<Affix> prefix, ArrayList<Affix> suffix,
									  Random rand) {
		Affix[] ret = {null, null};
		
		// Randomly generate prefix
		if(rand.nextInt(2) == 0) {
			ret[0] = prefix.get(rand.nextInt(prefix.size()));
		}
		
		// Randomly generator suffix
		if(rand.nextInt(2) == 0) {
			ret[1] = suffix.get(rand.nextInt(suffix.size()));
		}
		
		return ret;
	} // generateAffix

	public static void main(String[] args) throws FileNotFoundException {
		// Declare and initialize variables
		Random rand = new Random();
		Monsters mons = new Monsters(new File("src/data/large/monstats.txt"));
		Treasure TC = new Treasure(new File("src/data/large/TreasureClassEx.txt"));
		Armor arm = new Armor(new File("src/data/large/armor.txt"));
		Affixes fix = new Affixes(new File("src/data/large/MagicPrefix.txt"),
								  new File("src/data/large/MagicSuffix.txt"));
		Scanner in = new Scanner(System.in);
		char cont = 'y';
		
		// Continue the game
		while(cont == 'y') {
			// Generate elements for each game
			Monster monster = pickMonster(mons.getMonsters(), rand);
			String item = generateBaseItems(fetchTreasureClass(monster), rand, TC.getTreasureClass());
			int defense = generateBaseStats(item, rand, arm.getArmor());
			Affix[] affices = generateAffix(fix.getPrefix(), fix.getSuffix(), rand);
			String prefixstats = "";
			String suffixstats = "";
			
			// Print output
			System.out.print("Fighting " + monster.getMonsClass() + "...\n" +
							 "You have slain " + monster.getMonsClass() + "!\n" +
							 monster.getMonsClass() + " dropped:\n\n");
			
			// Complete item name with prefix and suffix
			if (affices[0] != null) { // Item name prefix
				Affix prefix = affices[0];
				int power = prefix.getMin() + rand.nextInt(prefix.getMax() - prefix.getMin() + 1);
				prefixstats =  power + " " + prefix.getModCode() + "\n";
				System.out.print(prefix.getName() + " ");
			}
			
			System.out.print(item);
			
			if (affices[1] != null) { // Item name suffix
				Affix suffix = affices[1];
				int power = suffix.getMin() + rand.nextInt(suffix.getMax() - suffix.getMin() + 1);
				suffixstats =  power + " " + suffix.getModCode() + "\n";
				System.out.print(" " + suffix.getName());
			}
			
			// Base statistic
			System.out.print("\nDefense: " + defense + "\n");
			
			// Additional affix statistics
			System.out.print(prefixstats + suffixstats);
			
			// Prompt the user to continue the game
			System.out.print("\nFight again [y/n]? ");
			String response = in.nextLine().trim().toLowerCase();
			while (!response.equals("y") && !response.equals("n")) {
				System.out.print("Fight again [y/n]? ");
				response = in.nextLine().trim().toLowerCase();
			} // while response
			
			System.out.print("\n");
			
			cont = response.charAt(0);
		} // while cont
		
		in.close();
	} // main
}
