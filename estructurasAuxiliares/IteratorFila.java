package estructurasAuxiliares;

import java.util.Iterator;
/**
 * Se utiliza para recorrer filas, empieza por el ultimo elemento e interactua con el metodo getPrev de la clase NodoDoble
 */
public class IteratorFila<T> implements Iterator<T>{
  private NodoDoble<T> nodo;

  public IteratorFila(NodoDoble<T> first){
    this.nodo = first;
  }
  @Override
  public boolean hasNext() {
    return this.nodo != null;
  }

  @Override
  public T next() {
    T aux = this.nodo.getValue();
    this.nodo = this.nodo.getPrev();

    return aux;
  }
  
}
