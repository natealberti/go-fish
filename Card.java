package defaultpackage;

public class Card {

	private int _num;
	private int _suit;
	
	public Card(int num, int suit) {
		_num = num;
		_suit = suit;
	}
	
	//returns the card number (including ace, jack, queen, king) as a string
	public int getNum() {
		return _num;
	}
	
	
	//returns the suit of the card
	public String getSuit() {
		String str = "";
		switch(_suit) {
			case(1):
				str = "clubs";
				break;
			case(2):
				str = "diamonds";
				break;
			case(3):
				str = "hearts";
				break;
			case(4):
				str = "spades";
				break;
		
		}
		return str;
	}
	
	public String toString() {
		String str = "";
		switch(_num) {
				case(1):
					str = "Ace";
					break;
				case(11):
					str = "Jack";
					break;
				case(12):
					str = "Queen";
					break;
				case(13):
					str = "King";
					break;
				default:
					str = "" + _num;
					break;
			}
		return str + " of " + getSuit();
	}
	
}
