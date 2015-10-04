import java.util.*;


public class RandomAI extends AI {

	RandomAI(Player player){
		super(player);
		name="Random AI";
	}
	@Override
	public void takeActions(GameBoard board) {
		for(Card card: board.visibleCards() ){
			if (player.canAfford(card)){
				board.buy(player, card);
				return;
			}
		}
		for(Card card: player.reserve ){
			if (player.canAfford(card)){
				board.buy(player, card);
				return;
			}			
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

}
