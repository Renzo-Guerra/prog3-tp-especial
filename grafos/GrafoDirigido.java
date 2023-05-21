package grafos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
  private HashMap<Integer, HashSet<Arco<T>>> vertices;

	/**
	 * Complejidad: O(1)
	 */
  public GrafoDirigido(){ 
    this.vertices = new HashMap<>();
  }

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public void agregarVertice(int verticeId) {
    this.vertices.put(verticeId, new HashSet<>());
	}

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public void borrarVertice(int verticeId) {
    this.vertices.remove(verticeId);
	}
  
	/**
	 * Complejidad: O(1)
	 */
  private boolean existenLosVertices(int verticeId1, int verticeId2){
    return (contieneVertice(verticeId1) && contieneVertice(verticeId2));
  }

	/**
	 * Complejidad: O(1)
	 */
  @Override
  public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
    // Se verifica que existan ambos vertices
		if(existenLosVertices(verticeId1, verticeId2)){
      // Se crea un nuevo arco y se lo agrega, de manera que el vertice1 puede ir al vertice2. 
      // vertice1 --> vertice2
      this.vertices.get(verticeId1).add(new Arco<T>(verticeId1, verticeId2, etiqueta));
		}
	}
	
	/**
	 * Complejidad: O(1)
	 */
	private boolean esElArcoNecesario(int verticeId1, int verticeId2, Arco<T> arco){
		return (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2);
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de arcos donde el origen es verticeId1
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
    if(existenLosVertices(verticeId1, verticeId2)){
      Iterator<Arco<T>> it = this.vertices.get(verticeId1).iterator();
			
			while(it.hasNext()){
				Arco<T> arcoActual = it.next();
				// Verificamos 
				if(esElArcoNecesario(verticeId1, verticeId2, arcoActual)){
					this.vertices.get(verticeId1).remove(arcoActual);
					return;
				}

			}
		}
	}
	
	/**
	 * Complejidad: O(1)
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de arcos donde el origen es verticeId1
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(existenLosVertices(verticeId1, verticeId2)){
			Iterator<Arco<T>> it = this.vertices.get(verticeId1).iterator();
			
			while(it.hasNext()){
				// Verificamos 
				if(esElArcoNecesario(verticeId1, verticeId2, it.next()))
					return true;
			}
		}

		return false;
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de arcos almacenados dentro del HashSet el cual su key para acceder es el verticeId1 
	 * Ej: Se itera el HashSet el cual sea el "value" de la "key" "verticeId1" en el "HashMap"  
	 * 		HashMap<verticeId1, HashSet<Arcos<T>>>	--> 	Se itera 1 solo HashSet<Arcos<T>>
	 * 
	 * @return (Arco<T>) si lo encuentra, sino null. 
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existenLosVertices(verticeId1, verticeId2)){
			Iterator<Arco<T>> it = this.vertices.get(verticeId1).iterator();
			Arco<T> arcoActual;

			while(it.hasNext()){
				arcoActual = it.next();
				// Verificamos 
				if(esElArcoNecesario(verticeId1, verticeId2, arcoActual))
					return arcoActual;
			}
		}

		return null;
	}

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	/**
	 * Complejidad: O(2n)
	 * 
	 * Primero se itera sobre cada key del HashSet para obtener las keys, 
	 * y luego por cada key se pregunta su size para agregarlo a un acumulador
	 */
	@Override
	public int cantidadArcos() {
		// complejidad O(n) donde n es la cantidad de vertices, esto se debe a que por cada vertice se agrega un elemento a keys. 
		Set<Integer> keys = this.vertices.keySet();		
		int acumCantArcos = 0;
		
		for(Integer keyActual : keys)	// Complejidad O(n) donde n es la cantidad de vertices
			acumCantArcos += this.vertices.get(keyActual).size(); // Complejidad O(1)
		
		return acumCantArcos;
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de vertices
	 * A pesar de que devuelve un iterator, este se obtiene de llamar al metodo keySet, el cual 
	 * itera por todas las keys de HashMap, y las agrega a un Set, RECIEN AHI es donde aplicamos iterator()
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de adyacentes del verticeId
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(contieneVertice(verticeId)){
			Iterator<Arco<T>> it = this.vertices.get(verticeId).iterator();
			HashSet<Integer> adyacentes = new HashSet<>();

			while(it.hasNext())
				adyacentes.add(it.next().getVerticeDestino());

			return adyacentes.iterator();
		}

		return null;
	}

	/**
	 * Complejidad: O(m + sumatoria(n)) donde m es la cantidad de keys del HashMap y n 
	 * es la cantidad de elementos dentro de cada HashMap. 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Set<Integer> keys = this.vertices.keySet();				// Complejidad O(n) donde n es la cantidad de vertices
		Set<Arco<T>> arcosDevolver = new HashSet<>();
		
		// Complejidad O(sumatoria(n)) donde n es la cantidad de elementos de cada HashSet dentro del HashMap 
		for(Integer keyActual : keys){
			// Complejidad O(n) donde n es la cantidad de elementos en el HashSet que seleccionamos, por el addAll(), 
			// ya que este itera sobre la collection HashSetInterna (El value de la key "keyActual" es la que referencia al HashSet)).
			arcosDevolver.addAll(this.vertices.get(keyActual));		
		}
			
		return arcosDevolver.iterator();
	}

	/**
	 * Complejidad: O(1), porque solo devolvemos el iterator, no es que estamos iterandolo dentro de este metodo.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(contieneVertice(verticeId))
			return this.vertices.get(verticeId).iterator();

		return null;
	}

}
