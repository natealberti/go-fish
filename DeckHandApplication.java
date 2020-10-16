package defaultpackage;

import java.util.Random;
import java.util.Scanner;

public class DeckHandApplication {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println("***gofish and card decks***\n");
		do {
		int answer = 0;
		System.out.println("Which of the following would you like to do?");
		System.out.println("1). Test decks\n2). Play gofish against the computer\n3). Quit");
		answer = scan.nextInt();
		if(answer == 1)
			testDeckHand();
		else if(answer == 2)
			goFish();
		else {
			System.out.println("Program ended.");
			loop = false;
		}
		} while(loop);
	}
	
	//test deck hand branch of the program
	public static void testDeckHand() {
		DeckHand empty = new DeckHand();
		DeckHand filled = new DeckHand();
		Scanner scan2 = new Scanner(System.in);
		boolean loopProgram = false;
		
		//filling the poker deck
		filled.fillStandardDeck();
		
		do {
			DeckHand deck = new DeckHand();
			int deckChoice = 1;
			System.out.println("Select a deck: ");
			System.out.println("1). Empty deck");
			System.out.println("2). Standard deck");
			deckChoice = scan2.nextInt();
			
			if(deckChoice == 2) {
				//pre-filled deck
				deck = filled;
			}
			else {
				//empty deck
				deck = empty;
			}
				boolean loopDeck;
				do {
					int function = 1;
					System.out.println("Select a function: ");
					System.out.println("1). Insert a card at the back of the deck");
					System.out.println("2). Remove a specific card");
					System.out.println("3). Remove a random card");
					System.out.println("4). Count how many of one card you have");
					System.out.println("5). Count the size of the deck");
					System.out.println("6). Display the contents of the deck");
					function = scan2.nextInt();
				
					if(function == 1) {
						//insert card at back of deck
						String suit;
						int numSuit = 1;
						int num;
						
						System.out.println("Suit of your card: ");
						suit = scan2.next();
						switch(suit) {
							case("clubs"):
								numSuit = 1;
								break;	
							case("diamonds"):
								numSuit = 2;
								break;
							case("hearts"):
								numSuit = 3;
								break;
							case("spades"):
								numSuit = 4;
								break;
						} 
						System.out.println("Number of your card (1 for Ace, 11 for Jack, 12 for Queen, 13 for King): ");
						num = scan2.nextInt();
						Card insert = new Card(num, numSuit);
						deck.insert(insert);
						System.out.println(insert.toString() + " was added to the back of the deck.");
						System.out.println("\n. . . . . ");
					}
				
					else if(function == 2) {
						//remove last card
						String suit;
						int numSuit = 1;
						int num;
						
						System.out.println("Suit of the card: ");
						suit = scan2.next();
						System.out.println("Number of the card (1 for Ace, 11 for Jack, 12 for Queen, 13 for King): ");
						num = scan2.nextInt();
						System.out.println(deck.delete(num, suit) + " was removed from the deck.");
						System.out.println("\n. . . . . ");
					}
				
					else if(function == 3) { 
						//remove random card
						Card random = deck.deleteAny();
						if(random == null)
							System.out.println("There are no cards in the deck to delete!");
						else
							System.out.println(random + " was deleted from the deck.");
					}
				
					else if(function == 4) {
						//count number of a specific card
						String suit;
						int numSuit = 1;
						int num;
						
						System.out.println("Suit of your card: ");
						suit = scan2.next();
						System.out.println("Number of your card (1 for Ace, 11 for Jack, 12 for Queen, 13 for King): ");
						num = scan2.nextInt();
						int match = deck.count(num, suit);
						if(match == 0)
							System.out.println("Your card isn't in the deck!");
						else if(match == 1)
							System.out.println("Your card showed up one time.");
						else
							System.out.println("Your card showed up " + match + " times.");
					}
				
					else if(function == 5) {
						//return size of deck
						int size = deck.getSize();
						if(size == 0)
							System.out.println("The deck has no cards in it!");
						else if(size == 1)
							System.out.println("The deck has one card in it.");
						else
							System.out.println("The deck has " + size + " cards in it.");
					}
					
					else if(function == 6) {
						System.out.println(deck.toString());
					}
					
					System.out.println("\nDo you want to do another function on this deck? (y/n)");
					String str = "";
					str = scan2.next();
					if(str.equalsIgnoreCase("y"))
						loopDeck = true;
					else
						loopDeck = false;
				} while(loopDeck);
		} while(loopProgram);
	}

 
	public static void goFish() {
	
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		boolean loopProgram = true;
		int userBooks = 0;
		int computerBooks = 0;
		String winner = "You won the game with " + userBooks + " books!";
		
		//setting up decks
		DeckHand user = new DeckHand();
		DeckHand computer = new DeckHand();
		DeckHand stock = new DeckHand();
		stock.fillStandardDeck();
		
		for(int i = 0; i < 7; i++) {
			user.insert(stock.deleteAny());
			computer.insert(stock.deleteAny());
		}
		System.out.println("*** GO FISH ***");
		System.out.println("When a --- appears, press a key to continue.\n\n");
		//initial deck
		
		//full program
		do {
			System.out.println("Your deck: \n" + user.toString());
			System.out.println("Select a rank of a card to ask for (1 for Ace, 11 for Jack, 12 for Queen, 13 for King): ");
			int num = scan.nextInt();
			
			//user taking a turn
			DeckHandApplication.checkComputerDeck(num, user, computer, stock);
			userBooks += DeckHandApplication.checkBooks(user);
			System.out.println("You have " + userBooks + " books completed.");
			System.out.println();
			System.out.println("---");
			scan.next();
		
			//computer taking a turn
			int random = rand.nextInt(13) + 1;
			DeckHandApplication.checksUserDeck(random, computer, user, stock);
			computerBooks += DeckHandApplication.checkBooks(computer);
			
			//checking if anybody has won yet
			if(computer.getSize() == 0) {
				winner = "The computer won the game with " + computerBooks + " books!";
				loopProgram = false;
			}
			else if(user.getSize() == 0)
				loopProgram = false;
			else if(stock.getSize() == 0) {
				winner = "It's a draw!";
				loopProgram = false;
			}
		} while(loopProgram);
		System.out.println("Game ended.");
	}
	
	//for gofish
	//checks if computer's deck has any cards of a specific user ask
	public static void checkComputerDeck(int num, DeckHand user, DeckHand computer, DeckHand stock) {
		Scanner scan = new Scanner(System.in);
		//checking computer's deck for matches
		DeckHand matches = new DeckHand();
		for(int i = 1; i <= computer.getSize(); i++) {
			Card current = computer.getCard(i);
			if(current.getNum() == num)
				matches.insert(current);
		}
		
		//inserting matches into user's deck
		if(matches.getSize() > 0) {
			System.out.println("matches: " + matches.getSize() + "\n");
			for(int i = 1; i <= matches.getSize(); i++) {
				Card match = matches.getCard(i);
				System.out.println(match.toString());
				user.insert(match);
				computer.delete(match.getNum(), match.getSuit());
			}
		} else {
			System.out.println("Computer says: \"Go fish!\"");
			System.out.println();
			System.out.println("---");
			scan.next();
			Card draw = stock.deleteAny();
			user.insert(draw);
			System.out.println("You just drew a " + draw.toString() + " from the pond.");
			System.out.println();
			}
	}
	
	//for gofish
	//checks if user's deck has any cards of a specific computer ask
	public static void checksUserDeck(int num, DeckHand computer, DeckHand user, DeckHand stock) {
		Scanner scan = new Scanner(System.in);
		//checking computer's deck for matches
			DeckHand matches = new DeckHand();
			System.out.println("The computer is asking for " + num + "s");
			for(int i = 1; i <= user.getSize(); i++) {
				Card current = user.getCard(i);
				if(current.getNum() == num)
					matches.insert(current);
			}
			
			//inserting matches into user's deck
			if(matches.getSize() > 0) {
				System.out.println("matches: " + matches.getSize() + "\n");
				for(int i = 1; i <= matches.getSize(); i++) {
					Card match = matches.getCard(i);
					System.out.println(match.toString());
					computer.insert(match);
					user.delete(match.getNum(), match.getSuit());
				} System.out.println();
			}
			else {
				System.out.println("You say: \"Go fish!\"");
				Card draw = stock.deleteAny();
				computer.insert(draw);
				System.out.println();
				System.out.println("---");
				scan.next();
				}
	}
	
	//for gofish
	//returns the amount of "books" in a deckhand
	public static int checkBooks(DeckHand deck) {
		int books = 0;
		for(int e = 1; e <= deck.getSize(); e++) {
			int match = 0;
			int num = deck.getCard(e).getNum();
			for(int i = 0; i < deck.getSize(); i++) {
				if(deck.getCard(i+1).getNum() == num)
					match++;
			}
			if(match == 4) {
				System.out.println(deck + "completed a book!");
				for(int f = 0; f < deck.getSize(); f++) {
					Card card = deck.getCard(f+1);
					if(card.getNum() == num)
						deck.delete(card.getNum(), card.getSuit());
				} books++;
			}
			
		}
		return books;
	}
}