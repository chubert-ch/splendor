import java.util.*;

//White, Blue, Green, Red, Black
class GameBoard{
	Deck lvlOneCards, lvlTwoCards, lvlThreeCards;
	int[] gems = new int[5];
	int gold=5;
	List<Noble> nobles;
	boolean victoryFlag=false;
	List<Player> players = new ArrayList<Player>();
	boolean actionLock=false;
	String log="";

	GameBoard(int numPlayers){
		for(int i=0; i<numPlayers; i++){
			players.add(new Player());
		}
		nobles=NobleFactory.theNobles(numPlayers+1);
		lvlOneCards=DeckFactory.lvlOneDeck();
		lvlTwoCards=DeckFactory.lvlTwoDeck();
		lvlThreeCards=DeckFactory.lvlThreeDeck();
		int gemNum=7;
		if (numPlayers==3) gemNum=5;
		if (numPlayers==2) gemNum=4;
		int[] gems= {gemNum, gemNum, gemNum, gemNum, gemNum};
		this.gems=gems;
	}

	List<Card> visibleCards(){
		List<Card> vis=new ArrayList<Card>();
		vis.addAll(lvlOneCards.visible());
		vis.addAll(lvlTwoCards.visible());
		vis.addAll(lvlThreeCards.visible());
		return vis;
	}

	List<Card> affordable(Player player){
		List<Card> afford = new ArrayList<Card>();
		for(Card card:visibleCards())
			if (player.canAfford(card))
				afford.add(card);
		return afford;
	}

	boolean throwAway(Player player, int[] colors){
		for(int color=0; color<5; color++){
			if (player.gems[color]<colors[color])
				return false;
		}
		for(int color=0; color<5; color++)
			player.gems[color]-=colors[color];
		return true;
	}

	boolean take3(Player player, Set<Integer> colors){
		if (actionLock) return false;
		if (colors.size()>3 || player.numGems()+colors.size()>10)
			return false;
		for (int color:colors){
			if(gems[color]==0){
				return false;
			}
		}
		for (int color:colors){
			gems[color]--;
			player.gems[color]++;
		}
		actionLock=true;
		log+="take3: " + colors;
		return true;
	}

	boolean take2(Player player, int color){
		if (actionLock) return false;
		if (gems[color]<4 || player.numGems()>8)
			return false;
		gems[color]=gems[color]-1;
		player.gems[color]=player.gems[color]+1;
		actionLock=true;
		log+="take2: " + ColorUtils.color(color);
		return true;
	}

	boolean buy(Player player, Card card){
		if (actionLock) return false;
		if(!player.canAfford(card))
			return false;
		for(int color=0; color<5; color++){
			int cost=Math.max(0, card.cost[color]-player.cards[color].size());
			player.gems[color]-=cost;
			if (player.gems[color] < 0){
				player.gold+=player.gems[color];
				gold-=player.gems[color];
				player.gems[color]=0;
			}
			gems[color]+=cost;
		}
		if (player.reserve.contains(card))
			player.reserve.remove(card);
		else
			removeCard(card);
		player.cards[card.color].add(card);
		player.points+=card.points;
		nobleManage(player);
		if(player.points>=15)
			victoryFlag=true;
		actionLock=true;
		log+="buy: " + card;
		return true;
	}

	boolean reserve(Player player, Card card){
		if (actionLock) return false;
		if (player.numGems()>9 || gold == 0 || player.reserve.size() == 3)
			return false;
		player.gold++;
		gold--;
		removeCard(card);
		player.reserve.add(card);
		actionLock=true;
		log+="reserve: " + card;
		return true;
	}
	void nobleManage(Player player){
		x:  for (Noble noble:nobles){
			for (int color=0; color<5; color++){
				if (player.cards[color].size() < noble.cost[color])
					continue x;
			}
			player.nobles.add(noble);
			player.points+=3;
			nobles.remove(noble);
			return;
		}
	}


	private void removeCard(Card card) {
		if (lvlOneCards.visible().contains(card))
			lvlOneCards.cards.remove(card);
		if (lvlTwoCards.visible().contains(card))
			lvlTwoCards.cards.remove(card);
		if (lvlThreeCards.visible().contains(card))
			lvlThreeCards.cards.remove(card);
	}
}