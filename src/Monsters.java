import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Monsters {
	// Field
	private ArrayList<Monster> monsters;

	// Constructor
	public Monsters(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		this.monsters = new ArrayList<>();
		
		while (in.hasNextLine()) {
			this.monsters.add(new Monster(in.nextLine().split("\t")));
		}
		
		in.close();
	} 
	
	// Method
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	} // getMonster
}
