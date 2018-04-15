
public class Monster {

	public String Class;
	public String Type;
	public int Level;
	public String TreasureClass;
	
	public Monster(String Class, String Type, int Level, String TreasureClass) {
		this.Class = Class;
		this.Type = Type;
		this.Level = Level;
		this.TreasureClass = TreasureClass;
	}
	
	public Monster(String[] entry) {
		this.Class = entry[0];
		this.Type = entry[1];
		this.Level = Integer.parseInt(entry[2]);
		this.TreasureClass = entry[3];
	}
}
