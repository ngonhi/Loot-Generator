
public class Monster {
	// Fields
	private String monsClass;
	private String type;
	private int level;
	private String treasureClass;
	
	//Constructor
	public Monster(String[] entry) {
		this.monsClass = entry[0];
		this.type = entry[1];
		this.level = Integer.parseInt(entry[2]);
		this.treasureClass = entry[3];
	}
	
	//Methods: Return the fields of the instance
	public String getMonsClass() { return this.monsClass; }
	public String getType() { return this.type; }
	public int getLevel() { return this.level; }
	public String getTreasureClass() { return this.treasureClass; } 
}
