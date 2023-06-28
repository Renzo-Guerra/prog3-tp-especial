<<<<<<< HEAD
package grafos.ComparatorsArco;

import java.util.Comparator;

import grafos.Arco;

=======
package grafos;

import java.util.Comparator;

>>>>>>> 424892c51a796fcf57c9aa82e0ac3041270a9198
public class CmpArcoDest implements Comparator<Arco>{

  @Override
  public int compare(Arco o1, Arco o2) {
    return (o1.getVerticeDestino() - o2.getVerticeDestino());
  }
  
}
