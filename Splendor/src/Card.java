
class Card{
	final int points;
	final int level;
	final int[] cost = new int[5];
	final int color;
	public Card(int points, int level, int[] cost, int color) {
		this.points = points;
		this.level = level;
		for(int colori=0; colori<5; colori++){
			this.cost[colori] = cost[colori];
		}
		this.color = color;
	}
	
	public String toString(){
		return ColorUtils.colorCode(color,ColorUtils.color(color)) + "(P " + points + ");" + 
				" Cost: " +	ColorUtils.printAnsiNonZeroColors(cost);
	}
	
}