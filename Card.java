
public class Card{
	enum Suit{Club,Diamond,Heart,Spade};
	private Suit suit; 
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s,int r){ //constructor,no return value
		suit = s;
		rank = r;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		System.out.println(getSuit()+","+getRank());
		
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}