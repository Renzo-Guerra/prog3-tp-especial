import java.util.Iterator;
import java.util.List;

import grafos.GrafoDirigido;
import servicios.ServicioBFS;
import servicios.ServicioCaminos;
import servicios.ServicioDFS;

public class Main {
  public static void main(String[] args) {
    GrafoDirigido<Integer> grafo = new GrafoDirigido<>();

    grafo.agregarVertice(1);
    grafo.agregarVertice(2);
    grafo.agregarVertice(3);
    grafo.agregarVertice(4);
    grafo.agregarVertice(5);
    grafo.agregarVertice(6);
    grafo.agregarVertice(7);
    grafo.agregarVertice(8);
    grafo.agregarVertice(9);

    grafo.agregarArco(1, 2, 10);
    grafo.agregarArco(1, 5, 10);
    grafo.agregarArco(1, 4, 10);
    grafo.agregarArco(2, 5, 10);
    grafo.agregarArco(2, 9, 10);
    grafo.agregarArco(2, 6, 10);
    grafo.agregarArco(4, 8, 10);
    grafo.agregarArco(5, 7, 10);
    grafo.agregarArco(9, 3, 10);

    Iterator<Integer> it = grafo.obtenerVertices();

    System.out.println("Como los devuelve el grafo utilizando iterator:");
    System.out.print("[");
    while(it.hasNext()){
      System.out.print(it.next() + " ");
    }
    System.out.print("]\n");
    
    testearDFS(grafo); 
    testearBFS(grafo);
    
    //! IMPORTANTE: 
    //! - Una coma indica un caminos, ej: [1, 2, 5, 9] significa que hubo 3 caminos tomados
    //! - Si "origen" y "destino" son los dos por ej 1, esta bien que [1] sea una solucion valida,
    //!   quiere decir que desde el origen 1 no se necesita cruzar ni 1 solo camino para llegar a "destino".  
    probandoServicioCaminos();
  }

  public static void testearDFS(GrafoDirigido<Integer> grafo){
    ServicioDFS sservicioDFS = new ServicioDFS(grafo);

    System.out.println("DFS: ");
    System.out.println(sservicioDFS.dfsForest());
  }

  public static void testearBFS(GrafoDirigido<Integer> grafo){
    ServicioBFS sservicioBFS = new ServicioBFS(grafo);

    System.out.println("BFS: ");
    System.out.println(sservicioBFS.bfsForest());
  }

  public static void probandoServicioCaminos(){
    System.out.println("\nProbandoServicioCaminos: \n");

    GrafoDirigido<Integer> grafoCaminos1 = new GrafoDirigido<>();

    grafoCaminos1.agregarVertice(1);
    grafoCaminos1.agregarVertice(2);
    grafoCaminos1.agregarVertice(3);
    grafoCaminos1.agregarVertice(4);
    grafoCaminos1.agregarVertice(5);
    grafoCaminos1.agregarVertice(6);
    grafoCaminos1.agregarVertice(7);
    grafoCaminos1.agregarVertice(8);
    grafoCaminos1.agregarVertice(9);
   
    grafoCaminos1.agregarArco(1, 2, 10);
    grafoCaminos1.agregarArco(1, 4, 10);
    grafoCaminos1.agregarArco(1, 5, 10);
    
    grafoCaminos1.agregarArco(2, 3, 10);
    grafoCaminos1.agregarArco(2, 5, 10);
    grafoCaminos1.agregarArco(2, 6, 10);
    
    grafoCaminos1.agregarArco(3, 6, 10);
    
    grafoCaminos1.agregarArco(4, 5, 10);
    grafoCaminos1.agregarArco(4, 8, 10);
    grafoCaminos1.agregarArco(4, 7, 10);
    
    grafoCaminos1.agregarArco(5, 6, 10);
    grafoCaminos1.agregarArco(5, 8, 10);
    grafoCaminos1.agregarArco(5, 9, 10);
    
    grafoCaminos1.agregarArco(6, 9, 10);
    
    grafoCaminos1.agregarArco(7, 8, 10);
    
    grafoCaminos1.agregarArco(8, 9, 10);
    int origen = 1, destino = 9, limite = 3;
    ServicioCaminos serCam = new ServicioCaminos(grafoCaminos1, origen, destino, limite);
    List<List<Integer>> posiblesCaminos = serCam.caminos();
    int i = 1;
    System.out.println("Origen: " + origen + " Destino: " + destino + " Limite: " + limite);
    for(List<Integer> lista: posiblesCaminos){
      System.out.println("Posible " + i++ + " camino: " + lista);
    }
  }
}
