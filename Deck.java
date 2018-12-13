import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards; //���a��̪��P
	public ArrayList<Card> usedCard;//���a��̪��P(�o�X�h���P)
	public ArrayList<Card> openCard;//�s�񦹰ƵP���Ҧ����}���P�A�~�P�ɭn���m
	public int nUsed; //�o������(local variable, no need initialization)
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
		if(cards.size() == 0){ //�P�o��,�~�P
			shuffle();
		}
		Card card = cards.get(0);//����Ĥ@�i�P
		cards.remove(0);//���a�o�X�h�@�i
		if(isOpened == true) {//true���}�P
			openCard.add(card);
		}
		usedCard.add(card);//���a����@�i�P
		nUsed ++;
		return card;
	}
	public ArrayList<Card> getOpenedCard(){//���o���}���P
		return openCard;
	}
	
	public void shuffle() {
		nUsed = 0;
		cards.addAll(usedCard);
		usedCard.clear();//���P
		openCard.clear();//���m�w�g�}�L���P
		for(int pos = 0;pos < cards.size();pos++) { 
			int swap = rnd.nextInt(cards.size()); //�H����}
			Card newv = cards.get(swap); //�w�p�~�n�P��arraylist
			cards.set(swap, cards.get(pos)); //�P���H����}����cards[pos]
			cards.set(pos, newv); //newv[swap]�s�J
		}
		
	}
	
}