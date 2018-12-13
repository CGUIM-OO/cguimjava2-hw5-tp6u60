import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards; //莊家手裡的牌
	public ArrayList<Card> usedCard;//玩家手裡的牌(發出去的牌)
	public ArrayList<Card> openCard;//存放此副牌中所有打開的牌，洗牌時要重置
	public int nUsed; //發的次數(local variable, no need initialization)
	Random rnd = new Random();
	//TODO: Please implement the constructor (30 points)
	public Deck(int nDeck){
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int d = 0 ; d < nDeck ; d++) {  //numbers of deck
			for(int x = 0 ; x <= 3 ; x++) {   //suits
				for(int y = 1 ; y <= 13 ; y++) {  //ranks
					if(x == 0) {
						Card card = new Card(Card.Suit.Club,y);
						cards.add(card);
					}
					else if(x == 1) {
						Card card = new Card(Card.Suit.Diamond,y);
						cards.add(card);
					}
					else if(x == 2) {
						Card card = new Card(Card.Suit.Heart,y);
						cards.add(card);
					}
					else {
						Card card = new Card(Card.Suit.Spade,y);
						cards.add(card);
					}
					
				}
			}
		}
		shuffle();
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		for(int p = 0 ; p < cards.size() ; p++) {
			Card Cprint = cards.get(p);
			Cprint.printCard();
		}

	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public Card getOneCard(boolean isOpened) {		
		if(cards.size() == 0){ //牌發完,洗牌
			shuffle();
		}
		Card card = cards.get(0);//拿到第一張牌
		cards.remove(0);//莊家發出去一張
		if(isOpened == true) {//true為開牌
			openCard.add(card);
		}
		usedCard.add(card);//玩家拿到一張牌
		nUsed ++;
		return card;
	}
	public ArrayList<Card> getOpenedCard(){//取得打開的牌
		return openCard;
	}
	
	public void shuffle() {
		nUsed = 0;
		cards.addAll(usedCard);
		usedCard.clear();//收牌
		openCard.clear();//重置已經開過的牌
		for(int pos = 0;pos < cards.size();pos++) { 
			int swap = rnd.nextInt(cards.size()); //隨機位址
			Card newv = cards.get(swap); //預計洗好牌的arraylist
			cards.set(swap, cards.get(pos)); //牌的隨機位址換成cards[pos]
			cards.set(pos, newv); //newv[swap]存入
		}
		
	}
	
}