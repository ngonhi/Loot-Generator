import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Affixes {
	// Fields
	private ArrayList<Affix> prefix;
	private ArrayList<Affix> suffix;
	
	// Constructor
	public Affixes(File prefix, File suffix) throws FileNotFoundException {
		Scanner pre = new Scanner(prefix);
		Scanner suf = new Scanner(suffix);
		this.prefix = new ArrayList<>();
		this.suffix = new ArrayList<>();
		
		while (pre.hasNextLine()) {
			this.prefix.add(new Affix(pre.nextLine().split("\t")));
		}
		pre.close();
		
		while(suf.hasNextLine()) {
			this.suffix.add(new Affix(suf.nextLine().split("\t")));
		}
		suf.close();
	}
	
	// Methods
	public ArrayList<Affix> getPrefix() { return this.prefix; }
	public ArrayList<Affix> getSuffix() { return this.suffix; }
}
