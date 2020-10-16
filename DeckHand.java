package defaultpackage;

import java.util.Random;

public class DeckHand {

	private Card[] deck;
	private Card[] deleted;
	private int size;
	private final int MAXSIZE = 52;
	private Random rand = new Random();
	
	
	//default constructor initializing the deck with no cards
	public DeckHand() {
		deck = new Card[MAXSIZE];
		deleted = new Card[2];
		size = 0;
	}
	
	
	//NOTE: may have to fix index to be size instead of size+1
	//insert a given card at the back of the deck
	public void insert(Card card) {
		if(size == MAXSIZE) {
			Card[] temp = new Card[deck.length + MAXSIZE];
			for(int i = 0; i < size; i++) {
				temp[i] = deck[i];
			}
			temp[size] = card;
			size++;
			deck = temp;
			deck[size] = card;
		}
		else {
			deck[size] = card;
			size++;
		}
		
	}
	
	
	//delete a specific card from the deck and returns it
	public Card delete(int num, String suit) {
		int index = 0;
		//find the index of the card
		for(int i = 0; i < size; i++) {
			if(num == deck[i].getNum()) {
				if(suit.equalsIgnoreCase(deck[i].getSuit()))
					index = i;
			}	
		} 
		//move the card to a deleted cards array
		deleted[1] = deck[index];
		
		//shift indexes after deleted card
		for(int e = index; e < size; e++) {
				deck[e] = deck[e+1];
			} size--;
		
		return deleted[1];
	}
	
	
	//delete a random card from the deck and returns it
	public Card deleteAny() {
		if(size == 0) {
			deleted[0] = deck[0];
		}
		
		else {
		int index = rand.nextInt(size);
		deleted[0] = deck[index];
		
		//shift the index after deleted card
		for(int e = index; e < size-1; e++) {
			deck[e] = deck[e+1];
		} size--;
		}
		
		return deleted[0]; 
	}
	
	//return the amount of times a specific card shows up int the deck
	public int count(int num, String suit) {
		int match = 0;
		//find the index of the card
		for(int i = 0; i < size; i++) {
			if(num == deck[i].getNum()) {
				if(suit.equalsIgnoreCase(deck[i].getSuit()))
					match++;
			}
		} 
		return match;
	}
	
	public int getSize() {
		return size;
	}
	
	public Card getCard(int pos) {
		return deck[pos-1];
	}
	
	//fills the deck with a standard 52 unique cards
	public void fillStandardDeck() {
		//clubs
		for(int i = 1; i < 14; i++) {
			Card card = new Card(i, 1);
			insert(card);
		}
		//diamonds
		for(int i = 1; i < 14; i++) {
			Card card = new Card(i, 2);
			insert(card);
		}
		//hearts
		for(int i = 1; i < 14; i++) {
			Card card = new Card(i, 3);
			insert(card);
		}
		//spades
		for(int i = 1; i < 14; i++) {
			Card card = new Card(i, 4);
			insert(card);
		}
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < size; i++) {
			str += i+1 + "). " + deck[i].toString() + "\n"; //throwing null pointer exception... idk why
		}
		return str;
	}
}
