import java.util.ArrayList;
import java.util.List;

class Player{
	int points=0;
	@SuppressWarnings("unchecked")
	List<Card>[] cards = new List[5];
	List<Card> reserve = new ArrayList<Card>();
	int[] gems = new int[5];
	List<Noble> nobles = new ArrayList<Noble>();
	int gold=0;

	Player(){
		cards[0]=new ArrayList<Card>();
		cards[1]=new ArrayList<Card>();
		cards[2]=new ArrayList<Card>();
		cards[3]=new ArrayList<Card>();
		cards[4]=new ArrayList<Card>();
	}

	boolean canAfford(Card card){
		int deficit=0;
		for(int color=0; color<5; color++){
			deficit += Math.max(0, card.cost[color]- (cards[color].size() + gems[color]));
		}
		if (deficit > gold)
			return false;
		return true;
	}

	int[] cost(Card card){
		int goldCost=0;
		int[] cost=new int[6];
		for(int color=0; color<5; color++){
			int gemCost=Math.max(0, card.cost[color]-cards[color].size());
			if (gemCost>gems[color]){
				goldCost+=gemCost-gems[color];
				cost[color]=gems[color];
			}
			else
				cost[color]=gemCost;
		}
		cost[5]=goldCost;
		return cost;
	}

	int numGems(){
		return gold+gems[0]+gems[1]+gems[2]+gems[3]+gems[4];
	}

	public String toString(){
		return "Gems:  " + ColorUtils.printAnsiColors(gems) + "; " + gold + " gold\n"+
				"Cards: " +
				ColorUtils.printAnsiColors(cards[0].size(), cards[1].size(), cards[2].size(), cards[3].size(), cards[4].size())
				+ "\n" +
				"Points: " + points;
	}

}