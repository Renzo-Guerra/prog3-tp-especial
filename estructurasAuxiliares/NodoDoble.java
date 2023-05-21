package estructurasAuxiliares;

public class NodoDoble<T> {
  private T value;
  private NodoDoble<T> next;
  private NodoDoble<T> prev;
  
  public NodoDoble(T value){
    this.value = value;
    this.next = null;
    this.prev = null;
  }

  public void setNext(NodoDoble<T> next) {
    this.next = next;
  }
  
  public void setPrev(NodoDoble<T> prev) {
    this.prev = prev;
  }

  public T getValue() {
    return value;
  }
  
  public NodoDoble<T> getNext() {
    return this.next;
  }
  
  public NodoDoble<T> getPrev() {
    return this.prev;
  }
}
