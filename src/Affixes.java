import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Affixes {

	public ArrayList<Affix> prefix;
	public ArrayList<Affix> suffix;
	
	public Affixes(File prefix, File suffix) throws FileNotFoundException {
		Scanner pre = new Scanner(prefix);
		Scanner suf = new Scanner(suffix);
		while (pre.hasNextLine()) {
			this.prefix.add(new Affix(pre.nextLine().split("\t")));
		}
		while(suf.hasNextLine()) {
			this.suffix.add(new Affix(suf.nextLine().split("\t")));
		}
		
		pre.close();
		suf.close();
	}
	
}
