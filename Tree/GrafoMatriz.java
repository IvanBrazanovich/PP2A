
public class GrafoMatriz {
    public static void main(String[] args) {
        int n = 4; // Número de vértices
        int[][] grafo = new int[n][n];

        // Agregar aristas
        agregarArista(grafo, 0, 1);
        agregarArista(grafo, 0, 2);
        agregarArista(grafo, 1, 2);
        agregarArista(grafo, 2, 3);

        // Mostrar la matriz de adyacencia
        mostrarGrafo(grafo);
    }

    // Método para agregar una arista (No dirigido)
    public static void agregarArista(int[][] grafo, int i, int j) {
        grafo[i][j] = 1;
        grafo[j][i] = 1; // Para grafos no dirigidos
    }

    // Método para mostrar la matriz de adyacencia
    public static void mostrarGrafo(int[][] grafo) {
        System.out.println("Matriz de Adyacencia:");
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                System.out.print(grafo[i][j] + " ");
            }
            System.out.println();
        }
    }
}