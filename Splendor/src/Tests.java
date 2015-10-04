

public class Tests {
	static public void MyTests() {
		Deck deck=DeckFactory.lvlThreeDeck();
		for(Card c: deck.cards) {
			System.out.println(c);
		}
		System.out.println(deck.cards.size());
	}
}
