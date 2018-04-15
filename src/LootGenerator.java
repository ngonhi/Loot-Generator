import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	
	public LootGenerator() {
		
	}
	
	public static Monster pickMonster(ArrayList<Monster> mons, Random rand) {
		int index = rand.nextInt(mons.size());
		return mons.get(index);
	}
	
	public static String fetchTreasureClass(Monster mon) {
		return mon.TreasureClass;
	}
	
	public static String generateBaseItems(String TreasureClass, Random rand,
			  							 HashMap<String, String[]> treasure) {
		
		String[] items = treasure.get(TreasureClass);
		String item = items[rand.nextInt(items.length)];
		while(treasure.containsKey(item)) {
			items = treasure.get(item);
			item = items[rand.nextInt(items.length)];
		}
		
		return item;
	}
	
	public static int generateBaseStats(String item, Random rand,
										 HashMap<String, int[]> armor) {
		int[] stats = armor.get(item);
		return stats[0] + rand.nextInt(stats[1] - stats[0] + 1);
	}
	
	/** Generate prefixes and suffixes for items with an independent 50% chance of each
	 * 
	 * @param prefix, an arraylist of Affixes
	 * @param suffix, an arraylist of Affixes
	 * @param rand, a random number generator
	 * @return ret, an array of 2 Affixes, the first will be the prefix, the second the suffix
	 * In the event that one or both is not generated, the index will contain null
	 */
	public static Affix[] generateAffix(ArrayList<Affix> prefix, ArrayList<Affix> suffix,
									  Random rand) {
		
		Affix[] ret = {null, null};
		if(rand.nextInt(2) == 0) {
			ret[0] = prefix.get(rand.nextInt(prefix.size()));
		}
		if(rand.nextInt(2) == 0) {
			ret[1] = suffix.get(rand.nextInt(suffix.size()));
		}
		return ret;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Random rand = new Random();
		Monsters mons = new Monsters(new File("src/data/large/monstats.txt"));
		Treasure TC = new Treasure(new File("src/data/large/TreasureClassEx.txt"));
		Armor arm = new Armor(new File("src/data/large/armor.txt"));
		Affixes fix = new Affixes(new File("src/data/large/MagicPrefix.txt"),
								  new File("src/data/large/MagicSuffix.txt"));
		Scanner in = new Scanner(System.in);
		char cont = 'y';
		while(cont == 'y') {
			//generate things
			Monster monster = pickMonster(mons.monsters, rand);
			String item = generateBaseItems(fetchTreasureClass(monster), rand, TC.treasure);
			int defense = generateBaseStats(item, rand, arm.armor);
			Affix[] affices = generateAffix(fix.prefix, fix.suffix, rand);
			String prefixstats = "";
			String suffixstats = "";
			//Output
			System.out.print("Fighting " + monster.Class + " ...\n" +
							 "You have slain " + monster.Class + "...\n" +
							 monster.Class + " dropped:\n\n");
			if (affices[0] != null) {
				Affix prefix = affices[0];
				int power = prefix.min + rand.nextInt(prefix.max - prefix.min + 1);
				prefixstats =  power + " " + prefix.modcode + "\n";
				System.out.print(prefix.Name + " ");
			}
			System.out.print(item);
			if (affices[1] != null) {
				Affix suffix = affices[1];
				int power = suffix.min + rand.nextInt(suffix.max - suffix.min + 1);
				suffixstats =  power + " " + suffix.modcode + "\n";
				System.out.print(" " + suffix.Name);
			}
			System.out.print("\nDefense: " + defense + "\n");
			System.out.print(prefixstats + suffixstats);
			System.out.print("Fight again [y/n]? ");
			String response = in.nextLine().toLowerCase();
			while (!response.equals("y") && !response.equals("n")) {
				System.out.print("Fight again [y/n]? ");
				response = in.nextLine().toLowerCase();
			}
			cont = response.charAt(0);
		}
		
		in.close();
	}
	
}
