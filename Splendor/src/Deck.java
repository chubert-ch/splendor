import java.util.ArrayList;
import java.util.List;

class Deck{
  List<Card> cards = new ArrayList<Card>();
  
  List<Card> visible(){
    List<Card> vis=new ArrayList<Card>();
    for(int color=0; color<Math.min(4, cards.size()); color++){
      vis.add(cards.get(color));
    }
    return vis;
  }
}