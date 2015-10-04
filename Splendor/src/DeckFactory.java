import java.util.Collections;


public class DeckFactory {
	static Deck lvlThreeDeck(){
		Deck lvl3 = new Deck();
		int[] cols;
		for(int color=0; color<5; color++){
			cols=ColorUtils.colorWheel(color, 0, 7, 0, 0, 0);
			lvl3.cards.add(new Card(4, 3, cols, color));
			cols=ColorUtils.colorWheel(color, 3, 7, 0, 0, 0);
			lvl3.cards.add(new Card(5, 3, cols, color));
			cols=ColorUtils.colorWheel(color, 3, 6, 3, 0, 0);
			lvl3.cards.add(new Card(5, 3, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 3, 5, 3, 3);
			lvl3.cards.add(new Card(5, 3, cols, color));
		}
		Collections.shuffle(lvl3.cards);
		return lvl3;
	}
	
	static Deck lvlTwoDeck(){
		Deck lvl2 = new Deck();
		int[] cols;
		for(int color=0; color<5; color++){
			cols=ColorUtils.colorWheel(color, 6, 0, 0, 0, 0);
			lvl2.cards.add(new Card(3, 2, cols, color));
			cols=ColorUtils.colorWheel(color, 5, 0, 0, 0, 0);		//Non-symmetric
			lvl2.cards.add(new Card(2, 2, cols, color));
			cols=ColorUtils.colorWheel(color, 3, 5, 0, 0, 0);		//Non-symmetric
			lvl2.cards.add(new Card(2, 2, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 2, 4, 1, 0);
			lvl2.cards.add(new Card(2, 2, cols, color));
			cols=ColorUtils.colorWheel(color, 2, 0, 3, 0, 3);
			lvl2.cards.add(new Card(2, 2, cols, color));
			cols=ColorUtils.colorWheel(color, 2, 0, 0, 3, 2);		//Non-symmetric
			lvl2.cards.add(new Card(2, 2, cols, color));
		}
		Collections.shuffle(lvl2.cards);
		return lvl2;
	}	
	
	static Deck lvlOneDeck(){
		Deck lvl1 = new Deck();
		int[] cols;
		for(int color=0; color<5; color++){
			cols=ColorUtils.colorWheel(color, 0, 1, 2, 0, 0);
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 2, 0, 0, 2);		//Non-symmetric
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 0, 0, 0, 3);		//Non-symmetric
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 1, 1, 1, 1);
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 0, 0, 4, 0);
			lvl1.cards.add(new Card(1, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 1, 1, 2, 1);
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 0, 1, 0, 2, 2);
			lvl1.cards.add(new Card(0, 1, cols, color));
			cols=ColorUtils.colorWheel(color, 1, 0, 0, 1, 3);		//Non-symmetric
			lvl1.cards.add(new Card(0, 1, cols, color));
		}
		Collections.shuffle(lvl1.cards);
		return lvl1;
	}
}
