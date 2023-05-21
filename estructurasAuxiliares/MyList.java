package estructurasAuxiliares;

import java.util.ArrayList;
import java.util.Iterator;

public class MyList<T> implements Iterable<T>{
  private NodoDoble<T> first;
  private NodoDoble<T> last;
  private int size;

  public MyList(){
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public MyList(Iterable<T> lista){
    this();
    this.push(lista);
  }

  public boolean isEmpty(){
    return this.size == 0;
  }

  /**
   * Permite ver el valor del proximo elemento en deQueue
   * @return (T) valor del dequeue sin eliminarlo de la fila
   */
  public T viewFirst(){
    if(this.isEmpty()){return null;}

    return this.first.getValue();
  }

  /**
   * Permite ver el valor del proximo elemento en deQueue
   * @return (T) valor del dequeue sin eliminarlo de la fila
   */
  public T viewLast(){
    if(this.isEmpty()){return null;}

    return this.last.getValue();
  }

  /**
   * Agrega un nuevo elemento al final de la lista
   * @param nuevoElem (T) elemento a agregar
   */
  public void push(T nuevoElem){
    NodoDoble<T> aux = new NodoDoble<T>(nuevoElem);
    
    if(this.isEmpty()){
      this.first = aux;
      this.last = aux;
    }else{
      aux.setPrev(this.last);
      this.last.setNext(aux);
      this.last = aux;
    }
    size++;
  }

  public void push(Iterable<T> nuevosElementos){
    Iterator<T> it = nuevosElementos.iterator();
    while(it.hasNext()){
      this.push(it.next());
    }
  }

    /**
   * Agrega un nuevo elemento al inicio de la lista
   * @param nuevoElem (T) elemento a agregar al inicio de la lista
   */
  public void unshift(T nuevoElem){
    NodoDoble<T> aux = new NodoDoble<T>(nuevoElem);
    
    if(this.isEmpty()){
      this.first = aux;
      this.last = aux;
    }else{
      aux.setNext(this.first);
      this.first.setPrev(aux);
      this.first = aux;
    }
    size++;
  }

  /**
   * Elimina y retorna el ultimo elemento de la lista
   * @return (T) ultimo elemento de la lista
   */
  public T pop(){
    if(this.isEmpty()){return null;}

    T aux = this.last.getValue();
    if(this.size == 1){
      this.first = null;
      this.last = null;
    }else{
      this.last = this.last.getPrev();
      this.last.setNext(null);
    }

    size--;

    return aux;
  }

  /**
   * Elimina y retorna el primer elemento de la lista
   * @return (T) primer elemento de la lista
   */
  public T shift(){
    if(this.isEmpty()){return null;}

    T aux = this.first.getValue();
    if(this.size == 1){
      this.first = null;
      this.last = null;
    }else{
      this.first = this.first.getNext();
      this.first.setNext(null);
    }

    size--;

    return aux;
  }

  public boolean contains(T element){
    Iterator<T> it = this.iterator();

    while(it.hasNext()){
      if(it.next() == element){
        return true;
      }
    }

    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new IteratorMyLista<>(this.first);
  }

  /**
   * Permite obtener un arraylist de los elementos de la fila, 
   * donde el primer elemento es el primero en haber sido ingresado, y el ultimo es el ultimo ingresado.
   * 
   * @return ArrayList<T> values de la pila
   */
  public ArrayList<T> getValues(){
    ArrayList<T> dev = new ArrayList<>();
    Iterator<T> it = this.iterator();
    
    while(it.hasNext()){
      dev.add(it.next());
    }

    return dev;
  }

  public int getSize(){
    return this.size;
  }

  public void clear(){
    this.first = null;
    this.last = null;
    size = 0;
  }
}
