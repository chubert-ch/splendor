
abstract public class AI {
	Player player;
	String name="default name";

	AI(Player player){
		this.player=player;
	}
	public abstract void takeActions(GameBoard board);
}
