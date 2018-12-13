import java.util.ArrayList;

public class Table {
	private final int MAXPLAYER = 4;
	private Deck deck;//存放所有的牌
	private Player[] Players;//存放所有的玩家
	private Dealer dealer;//存放一個莊家
	private int[] pos_betArray;//存放每個玩家在一局下的注
	private ArrayList<Card> DealerCard=new ArrayList<Card>();
	private ArrayList<Card> Player1Card=new ArrayList<Card>();
	private ArrayList<Card> Player2Card=new ArrayList<Card>();
	private ArrayList<Card> Player3Card=new ArrayList<Card>();
	private ArrayList<Card> Player4Card=new ArrayList<Card>();
	public Table(int nDeck) {
		Deck D = new Deck(nDeck);
		deck = D;
		Players = new Player[MAXPLAYER];
		pos_betArray = new int[MAXPLAYER];
	}
	public void set_player(int pos, Player p) {
		Players[pos] = p;
	}
	public Player[] get_player() {
		return Players;
	}
	public void set_dealer(Dealer d) {
		dealer = d;
	}
	public Card get_face_up_card_of_dealer() {
		ArrayList<Card> dcard = new ArrayList();
		dcard = dealer.getOneRoundCard();
		Card card = dcard.get(1);
		return card;		
	}
	private void ask_each_player_about_bets() {
		for(int i = 0;i < 4;i++) {
			if(Players[i]!=null) {
				Player p = Players[i];
				p.sayHello();
				p.makeBet();
				pos_betArray[i] = p.makeBet();
			}
		}
	}
	private void distribute_cards_to_dealer_and_players() {
		Player1Card.add(deck.getOneCard(true));
		Player1Card.add(deck.getOneCard(true));
		Players[0].setOneRoundCard(Player1Card);
		Player2Card.add(deck.getOneCard(true));
		Player2Card.add(deck.getOneCard(true));
		Players[1].setOneRoundCard(Player2Card);
		Player3Card.add(deck.getOneCard(true));
		Player3Card.add(deck.getOneCard(true));
		Players[2].setOneRoundCard(Player3Card);
		Player4Card.add(deck.getOneCard(true));
		Player4Card.add(deck.getOneCard(true));
		Players[3].setOneRoundCard(Player4Card);
		DealerCard.add(deck.getOneCard(true));
		DealerCard.add(deck.getOneCard(true));
		dealer.setOneRoundCard(DealerCard);
		System.out.println("Dealer's face up card is " + get_face_up_card_of_dealer().getSuit()+","+get_face_up_card_of_dealer().getRank());
	}
	private void ask_each_player_about_hits() {
		boolean hit = false;
		for(int i = 0;i < 4;i++) {
			do {
				hit = Players[i].hit_me(this);
				if(hit) {
					ArrayList<Card> newc = Players[i].getOneRoundCard();
					newc.add(deck.getOneCard(true));
					Players[i].setOneRoundCard(newc);
					System.out.println("Hit! "+Players[i].getName()+"'s cards now: ");
					for(Card card : newc) {
						card.printCard();
					}
				}
				else {
					System.out.println(Players[i].getName()+", Pass hit!");
					
				}
			}
			while(hit);
		}
	}
	private void ask_dealer_about_hits() {
		ArrayList<Card> cards = this.dealer.getOneRoundCard();
		boolean hit = false;
		do {
			hit = dealer.hit_me(this);
			if(hit) {
				cards.add(this.deck.getOneCard(true));
				this.dealer.setOneRoundCard(cards);
			}
			if(this.dealer.getTotalValue()>21) {
				hit = false;
			}
		}
		while(hit);
		System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		System.out.print("Dealer's card value is " +dealer.getTotalValue() +" ,Cards:");
		dealer.printAllCard();
		for(int i = 0;i<Players.length;i++) {
			System.out.println(Players[i].getName() + " card value is " + Players[i].getTotalValue());
			if(Players[i].getTotalValue()>21&&dealer.getTotalValue()>21) {
				System.out.println(",chips have no change! The Chips now is:"+Players[i].getCurrentChips());
			}
			else if(dealer.getTotalValue()>Players[i].getTotalValue()&&dealer.getTotalValue()<=21) {
				int c = Players[i].getCurrentChips();
				c -= Players[i].makeBet();
				Players[i].setchips(c);
				System.out.println(", Loss "+Players[i].makeBet()+" Chips, the Chips now is: "+c);
			}
			else if(dealer.getTotalValue()>Players[i].getTotalValue()&&dealer.getTotalValue()>21) {
				int c = Players[i].getCurrentChips();
				c += Players[i].makeBet();
				Players[i].setchips(c);
				System.out.println(",Get "+Players[i].makeBet()+" Chips, the Chips now is: "+c);
			}
			else if(dealer.getTotalValue()<Players[i].getTotalValue()&&Players[i].getTotalValue()<=21) {
				int c = Players[i].getCurrentChips();
				c += Players[i].makeBet();
				Players[i].setchips(c);
				System.out.println(",Get "+Players[i].makeBet()+" Chips, the Chips now is: "+c);
			}
			else if(dealer.getTotalValue()<Players[i].getTotalValue()&&Players[i].getTotalValue()>21) {
				int c = Players[i].getCurrentChips();
				c -= Players[i].makeBet();
				Players[i].setchips(c);
				System.out.println(", Loss "+Players[i].makeBet()+" Chips, the Chips now is: "+c);
			}
			else if(dealer.getTotalValue()==Players[i].getTotalValue())
				System.out.println(",chips have no change! The Chips now is:"+Players[i].getCurrentChips());
		}
		
	}
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
