
import java.util.*;

public class GrafoDFS {
    public static void main(String[] args) {
        int n = 4; // Número de vértices
        ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            grafo.add(new ArrayList<>());
        }

        // Agregar aristas
        agregarArista(grafo, 0, 1);
        agregarArista(grafo, 0, 2);
        agregarArista(grafo, 1, 2);
        agregarArista(grafo, 2, 3);

        // Realizar DFS
        boolean[] visitados = new boolean[n];
        System.out.println("DFS desde el nodo 0:");
        dfs(grafo, 0, visitados);
    }

    public static void agregarArista(ArrayList<ArrayList<Integer>> grafo, int i, int j) {
        grafo.get(i).add(j);
        grafo.get(j).add(i); // Para grafos no dirigidos
    }

    public static void dfs(ArrayList<ArrayList<Integer>> grafo, int nodo, boolean[] visitados) {
        visitados[nodo] = true;
        System.out.print(nodo + " ");
        for (int vecino : grafo.get(nodo)) {
            if (!visitados[vecino]) {
                dfs(grafo, vecino, visitados);
            }
        }
    }
}