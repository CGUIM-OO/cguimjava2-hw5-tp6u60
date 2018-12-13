import java.util.ArrayList;

public class Player extends Person {
	private String name;//玩家姓名
	private int chips;//玩家籌碼
	private int bet;//玩家下的賭注
//	private ArrayList<Card> oneRoundCard;//這局的牌

	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
		
	}
	public String getName() {//name的getter
		return name;
	}
	
	public int makeBet() {//下注
		bet = 1;//基本下注
		if(chips <= 0) {//如果沒籌碼,則不下注
			bet = 0;
		}
		return bet;
		
	}

	
	public boolean hit_me(Table table) {//是否要牌，是回傳true，不再要牌則回傳false
		int totalvalue = getTotalValue();
		if(totalvalue >= 17) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getCurrentChips() {//回傳玩家現有籌碼
		return chips;
	}
	public void increaseChips (int diff) {//玩家籌碼變動
		chips = chips + diff;
	}
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + "chips.");
	}
	public void setchips(int c) {
		chips = c;
		
	}
	
	
}
