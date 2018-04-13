import java.util.Scanner;

public class LootGenerator {
	
	public LootGenerator() {
		
	}
	
	public void pickMonster() {
		
	}
	
	public void fetchTreasureClass() {
		
	}
	
	public void generateBaseItems() {
		
	}
	
	public void generateBaseStats() {
		
	}
	
	public void generateAffix() {
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char cont = 'y';
		while(cont == 'y') {
			//Output formatted as it will appear to user
			System.out.print("Fighting <monster name>...\n" +
							 "You have slain <monster name>...\n" +
							 "<monster name> dropped:\n\n" +
							 "<complete item name>\n" + 
							 "<base item statistic>\n" +
							 "<additional affix statistics>\n" +
							 "Fight again [y/n]? ");
			String response = in.nextLine().toLowerCase();
			while (!response.equals("y") && !response.equals("n")) {
				System.out.print("Fight again [y/n]? ");
				response = in.nextLine().toLowerCase();
			}
			cont = response.charAt(0);
		}
		
		in.close();
	}
	
}
