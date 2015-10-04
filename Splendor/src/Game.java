import java.util.*;

public class Game {
	GameBoard gameBoard;
	int turns=0;
	List<AI> AIs=new ArrayList<AI>();
	
	Game(int numPlayers) {
		gameBoard=new GameBoard(numPlayers);
		AIs.add(new V1AICost(gameBoard.players.get(0)));
		for(int i=1; i<numPlayers; i++){
			AIs.add(new V1AI(gameBoard.players.get(i)));
		}
	}
	
	public AI runGame(boolean display){
		while(!gameBoard.victoryFlag){
			turns++;
			for(AI ai:AIs){
				gameBoard.actionLock=false;
				ai.takeActions(gameBoard);
			}
			if (display)
				display();
			if(turns>40){
				System.out.println("AI FAILURE!!!");
				break;
			}
		}
		System.out.println("\nFinal state "+ turns+":\n");
		int pnum=0;
		for(AI ai:AIs){
			pnum++;
			//System.out.println("Player " + (pnum++) + ": " + ai.name);
			//System.out.println(ai.player.toString());
			ai.name+=""+pnum;
		}
		//display();
		Collections.sort(AIs, new Comparator<AI>(){
			@Override
			public int compare(AI o1, AI o2) {
				if(o1.player.points>o2.player.points)
					return -1;
				if(o1.player.points<o2.player.points)
					return 1;
				return 0;
			}});
		return AIs.get(0);
	}

	private void display() {
		System.out.println("\nTurn: "+turns);
		System.out.println("Gems: " +ColorUtils.printColors(gameBoard.gems) + "; Gold: " + gameBoard.gold);
		int pnum=1;
		for(AI ai:AIs){
			System.out.println("Player " + (pnum++) + ": " + ai.name);
			System.out.println(ai.player.toString());
		}
		for (Card card: gameBoard.visibleCards()) {
			System.out.println(card);
		}
		for (Noble noble: gameBoard.nobles) {
			System.out.println("Noble: " +ColorUtils.printAnsiNonZeroColors(noble.cost));
		}
		System.out.println();
	}
	
}
