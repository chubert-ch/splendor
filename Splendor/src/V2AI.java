import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class V2AI extends AI {

	V2AI(Player player){
		super(player);
		name="v1 AI";
	}

	Map<Card, int[]> costMap;
	
	public class CardGoodness implements Comparator<Card> {
		@Override
		public int compare(Card arg0, Card arg1) {
			if(arg0.points > arg1.points)
				return 1;
			if(arg0.points < arg1.points)
				return -1;
			if(cost(arg0) < cost(arg1))
				return 1;
			if(cost(arg0) > cost(arg1))
				return -1;
			return 0;
		}
		
		private int cost(Card card){
			int[] pCost=costMap.get(card);
			return pCost[0]+pCost[1]+pCost[2]+pCost[3]+pCost[4]+2*pCost[5];
		}
	}
	
	@Override
	public void takeActions(GameBoard board) {
		List<Card> affordable = new ArrayList<Card>();
		for(Card card: board.visibleCards())
			if (player.canAfford(card))
				affordable.add(card);
		for(Card card: player.reserve )
			if (player.canAfford(card))
				affordable.add(card);
		costMap=new HashMap<Card, int[]>();
		for (Card card: affordable){
			costMap.put(card, player.cost(card));
		}
		if (!affordable.isEmpty()){
			Collections.sort(affordable, new CardGoodness());
			board.buy(player, affordable.get(affordable.size()-1));
			return;
		}
		
		Set<Integer> colors = new HashSet<Integer>();
		if ((board.gems[0]==0?0:1)+
				(board.gems[1]==0?0:1)+
				(board.gems[2]==0?0:1)+
				(board.gems[3]==0?0:1)+
				(board.gems[4]==0?0:1)
					<3) {
			if(player.reserve.size()<3){
				board.reserve(player, board.visibleCards().get(0));
			}
			return;
		}
		while (colors.size()<3){
			int color=Math.abs(new Random().nextInt())%5;
			if (board.gems[color]>0)
				colors.add(color);
		}
		board.take3(player, colors);
	}
	
	/**
	 * -1 means you can't buy it for the forseeable future. 
	 */
	public int turnsToBuy(Card card, GameBoard board){
		int[] rawCost=rawCost(card);
		int[] excess=new int[5];
		int numGems=player.gold;
		int goldDebt=0;
		for(int color=0; color<5; color++){
			goldDebt=Math.max(0, player.gems[color]+board.gems[color]-rawCost[color]);
			excess[color]=Math.max(0,rawCost[color]-player.gems[color]);
			numGems+=player.gems[color]+excess[color];
		}
		if (goldDebt>player.gold + (board.gold>0?1:0))
			return -1;
		if(numGems>10)
			return -1;
		
		return -1;
	}
	
	public int[] rawCost(Card card){
		int[] cost=new int[5];
		for(int color=0; color<5; color++){
			int gemCost=Math.max(0, card.cost[color]-player.cards[color].size());
			cost[color]=gemCost;			
		}
		return cost;
	}
}
