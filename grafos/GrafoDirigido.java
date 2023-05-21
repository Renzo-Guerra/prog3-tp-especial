package grafos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
  private HashMap<Integer, HashSet<Arco<T>>> vertices;

  public GrafoDirigido(){ 
    this.vertices = new HashMap<>();
  }

	@Override
	public void agregarVertice(int verticeId) {
    this.vertices.put(verticeId, new HashSet<>());
	}

	@Override
	public void borrarVertice(int verticeId) {
    this.vertices.remove(verticeId);
	}
  
  private boolean existeVertice(int verticeId){
    return this.vertices.containsKey(verticeId);
  }
  
  private boolean existenLosVertices(int verticeId1, int verticeId2){
    return (existeVertice(verticeId1) && existeVertice(verticeId2));
  }

  @Override
  public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
    // Se verifica que existan ambos vertices
		if(existenLosVertices(verticeId1, verticeId2)){
      // Se crea un nuevo arco y se lo agrega, de manera que el vertice1 puede ir al vertice2. 
      // vertice1 --> vertice2
      this.vertices.get(verticeId1).add(new Arco<T>(verticeId1, verticeId2, etiqueta));
		}
	}
	
	private boolean esElArcoNecesario(int verticeId1, int verticeId2, Arco<T> arco){
		return (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2);
	}

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
	
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

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

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existeArco(verticeId1, verticeId2)){
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

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		Set<Integer> it = this.vertices.keySet();
		int acumCantArcos = 0;
		
		for(Integer keyActual : it)	
			acumCantArcos += this.vertices.get(keyActual).size();
		
		return acumCantArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(existeVertice(verticeId)){
			Iterator<Arco<T>> it = this.vertices.get(verticeId).iterator();
			HashSet<Integer> adyacentes = new HashSet<>();

			while(it.hasNext())
				adyacentes.add(it.next().getVerticeDestino());

			return adyacentes.iterator();
		}

		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Set<Integer> keys = this.vertices.keySet();
		Set<Arco<T>> arcosDevolver = new HashSet<>();
		
		for(Integer keyActual : keys){
			arcosDevolver.addAll(this.vertices.get(keyActual));
			
		}
			
		return arcosDevolver.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(existeVertice(verticeId))
			return this.vertices.get(verticeId).iterator();

		return null;
	}

}
