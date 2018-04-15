
public class Affix {

	public String Name;
	public String modcode;
	public int min;
	public int max;
	
	public Affix(String[] entry) {
		this.Name = entry[0];
		this.modcode = entry[1];
		this.min = Integer.parseInt(entry[2]);
		this.max = Integer.parseInt(entry[3]);
	}
	
}
