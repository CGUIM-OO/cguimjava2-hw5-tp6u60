import java.util.ArrayList;

public class Player extends Person {
	private String name;//���a�m�W
	private int chips;//���a�w�X
	private int bet;//���a�U����`
//	private ArrayList<Card> oneRoundCard;//�o�����P

	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
		
	}
	public String getName() {//name��getter
		return name;
	}
	
	public int makeBet() {//�U�`
		bet = 1;//�򥻤U�`
		if(chips <= 0) {//�p�G�S�w�X,�h���U�`
			bet = 0;
		}
		return bet;
		
	}

	
	public boolean hit_me(Table table) {//�O�_�n�P�A�O�^��true�A���A�n�P�h�^��false
		int totalvalue = getTotalValue();
		if(totalvalue >= 17) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getCurrentChips() {//�^�Ǫ��a�{���w�X
		return chips;
	}
	public void increaseChips (int diff) {//���a�w�X�ܰ�
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
