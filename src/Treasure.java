import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Treasure {
	// Field
	private HashMap<String, String[]> treasureClass;
	
	// Constructor
	public Treasure(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		this.treasureClass = new HashMap<>();
		
		while(in.hasNextLine()) {
			String[] entry = in.nextLine().split("\t");
			treasureClass.put(entry[0], Arrays.copyOfRange(entry, 1, 4));
		}
		
		in.close();
	}
	
	// Method: Return treasureClass field of the instance
	public HashMap<String, String[]> getTreasureClass() { return this.treasureClass; }
	
}
