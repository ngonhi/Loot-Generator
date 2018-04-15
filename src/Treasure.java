import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Treasure {

	public HashMap<String, String[]> treasure;
	
	public Treasure(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		this.treasure = new HashMap<>();
		while(in.hasNextLine()) {
			String[] entry = in.nextLine().split("\t");
			treasure.put(entry[0], Arrays.copyOfRange(entry, 1, 4));
		}
		
		in.close();
	}
	
}
