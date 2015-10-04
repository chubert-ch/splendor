import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NobleFactory {
	static public List<Noble> theNobles(int numNobles){
		List<Noble> nobles = new ArrayList<Noble>();
		for(int color=0; color<5; color++){
			nobles.add(new Noble(ColorUtils.colorWheel(color, 4, 4, 0, 0, 0)));
			nobles.add(new Noble(ColorUtils.colorWheel(color, 3, 3, 3, 0, 0)));
		}
		Collections.shuffle(nobles);
		return nobles.subList(0, numNobles);
	}
}
