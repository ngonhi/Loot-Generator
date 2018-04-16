
public class Affix {
	// Fields
	private String name;
	private String modCode;
	private int min;
	private int max;
	
	// Constructor
	public Affix(String[] entry) {
		this.name = entry[0];
		this.modCode = entry[1];
		this.min = Integer.parseInt(entry[2]);
		this.max = Integer.parseInt(entry[3]);
	}
	
	// Methods: Return the fields of the instance
	public String getName() { return this.name; }
	public String getModCode() { return this.modCode; }
	public int getMin() { return this.min; }
	public int getMax() { return this.max; }
}
