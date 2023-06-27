package grafos;

import java.util.Comparator;

public class CmpArcoDest implements Comparator<Arco>{

  @Override
  public int compare(Arco o1, Arco o2) {
    return (o1.getVerticeDestino() - o2.getVerticeDestino());
  }
  
}
