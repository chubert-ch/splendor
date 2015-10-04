
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int games=0;
		int[] players={0,0,0,0};
		
		for(int i=0; i<2000; i++){
			Game game=new Game(4);
			games++;
			String name=game.runGame(false).name;
			int pnum=Integer.valueOf(name.substring(name.length()-1))-1;
			players[pnum]++;
			System.out.println("WINS: "+ players[0] + ", "+ players[1] + ", "+ players[2] + ", "+ players[3] + " in "+ games);
		}
		//Tests.MyTests();

	}

}
