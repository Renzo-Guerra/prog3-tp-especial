<<<<<<< HEAD
package grafos.ComparatorsArco;

import java.util.Comparator;

import grafos.Arco;

=======
package grafos;

import java.util.Comparator;

>>>>>>> 424892c51a796fcf57c9aa82e0ac3041270a9198
public class CmpArcoOrig implements Comparator<Arco>{

  @Override
  public int compare(Arco o1, Arco o2) {
<<<<<<< HEAD
    return (o1.getVerticeOrigen() - o2.getVerticeOrigen());
=======
    return o1.getVerticeOrigen() - o2.getVerticeOrigen();
>>>>>>> 424892c51a796fcf57c9aa82e0ac3041270a9198
  }
  
}
