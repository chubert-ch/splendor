
public class ColorUtils {

	static int[] colorWheel(int color, int x, int i, int ii, int iii, int iv) {
		int[] cols=ColorUtils.newColors();
		cols[(5+color)%5]=x;
		cols[(5+color-1)%5]=i;
		cols[(5+color-2)%5]=ii;
		cols[(5+color-3)%5]=iii;
		cols[(5+color-4)%5]=iv;
		return cols;
	}

	static int[] newColors(){
		int[] cols=new int[5];
		cols[0]=0;
		cols[1]=0;
		cols[2]=0;
		cols[3]=0;
		cols[4]=0;
		return cols;
	}

	static int[] newColors(int w, int u, int g, int r, int b){
		int[] cols=new int[5];
		cols[0]=w;
		cols[1]=u;
		cols[2]=g;
		cols[3]=r;
		cols[4]=b;
		return cols;
	}

	static String color(int color){
		switch (color){
		case 0: return "white";
		case 1: return "blue";
		case 2: return "green";
		case 3: return "red";
		case 4: return "black";
		default: return "error";
		}
	}
	
	static String printColors(int[] colors){
		return printColors(colors[0], colors[1], colors[2], colors[3], colors[4]);
	}
	
	static String printColors(int w, int u, int g, int r, int b){
		return w + " white, " + u + " blue, " + g + " green, " 
				+ r + " red, " + b + " black";
	}
	
	static String printAnsiColors(int[] colors){
		return printAnsiColors(colors[0], colors[1], colors[2], colors[3], colors[4]);
	}
	
	static String printAnsiColors(int w, int u, int g, int r, int b){
		return printAnsiColor(0, w) + ", " +
				printAnsiColor(1, u) + ", " +
				printAnsiColor(2, g) + ", " +
				printAnsiColor(3, r) + ", " +
				printAnsiColor(4, b) + ", ";
	}
	
	static String printAnsiColor(int color, int num){
		return colorCode(color, ""+num);
	}
	
	static String printAnsiNonZeroColors(int[] colors){
		return printAnsiNonZeroColors(colors[0], colors[1], colors[2], colors[3], colors[4]);
	}
	
	static String printAnsiNonZeroColors(int w, int u, int g, int r, int b){
		return printAnsiNonZeroColor(0, w) + " " +
				printAnsiNonZeroColor(1, u) + " " +
				printAnsiNonZeroColor(2, g) + " " +
				printAnsiNonZeroColor(3, r) + " " +
				printAnsiNonZeroColor(4, b) + " ";
	}
	
	static String printAnsiNonZeroColor(int color, int num){
		return num==0?"":colorCode(color, ""+num);
	}
	
	static String colorCode(int color, String str){
		return (char)27 + colorCode(color) + str + (char)27 + "[00;00m";
	}

	static String colorCode(int color){
		switch (color){
		case 0: return "[01;30m";
		case 1: return "[01;34m";
		case 2: return "[01;32m";
		case 3: return "[01;31m";
		case 4: return "[00;00m";
		default: return "error";
		}
	}
}
