package grafos;

import java.util.Comparator;

public class CmpArcoOrigDest implements Comparator<Arco>{

  @Override
  public int compare(Arco o1, Arco o2) {
    return ((new CmpArcoOrig().compare(o1, o2) == 0) && (new CmpArcoDest().compare(o1, o2) == 0)) ? 0 : 1; 
  }
  
}
