import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Armor {
	// Field
	private HashMap<String, int[]> armor;
	
	// Constructor
	public Armor(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		this.armor = new HashMap<>();
		
		while(in.hasNextLine()) {
			String[] entry = in.nextLine().split("\t");
			String[] stats = Arrays.copyOfRange(entry, 1, 3);
			int[] intstats = Arrays.stream(stats).mapToInt(Integer::parseInt).toArray();
			armor.put(entry[0], intstats);
		}
		
		in.close();
	}
	
	// Method: Return armor field of the instance
	public HashMap<String, int[]> getArmor() { return this.armor; }
}
