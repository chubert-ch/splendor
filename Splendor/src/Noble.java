
public class Noble {
	int[] cost=new int[5];

	public Noble(int[] cost) {
		this.cost = cost;
	}
	public Noble(int w, int u, int g, int r, int b){
		this.cost=ColorUtils.newColors(w, u, g, r, b);
	}
}
