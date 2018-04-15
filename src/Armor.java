import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Armor {

	public HashMap<String, int[]> armor;
	
	public Armor(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			String[] entry = in.nextLine().split("\t");
			String[] stats = Arrays.copyOfRange(entry, 1, 3);
			int[] intstats = Arrays.stream(stats).mapToInt(Integer::parseInt).toArray();
			armor.put(entry[0], intstats);
		}
		
		in.close();
	}
	
}
