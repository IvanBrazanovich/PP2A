import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

class Nodo {
    int valor;
    Nodo hijoIzquierdo, hijoDerecho;

    Nodo(int valor) {
        this.valor = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
}

public class BinaryTree {
    Nodo raiz;

    public BinaryTree() {
        this.raiz = null;
    }

    // Insertar un valor en el arbol
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    // Insertar un valor en el arbol
    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (nodo.valor == valor) {
            return nodo;
        }

        if (valor < nodo.valor) {
            nodo.hijoIzquierdo = insertarRec(nodo.hijoIzquierdo, valor);
        } else {
            nodo.hijoDerecho = insertarRec(nodo.hijoDerecho, valor);
        }
        return nodo;
    }

    // Recorrido en orden (Inorder Traversal)
    public void recorrerInOrden() {
        recorrerInOrdenRec(raiz);
    }

    // Función recursiva para recorrer en orden
    private void recorrerInOrdenRec(Nodo raiz) {
        if (raiz != null) {
            recorrerInOrdenRec(raiz.hijoIzquierdo); // Recorrer izquierdo
            System.out.print(raiz.valor + " "); // Imprimir valor
            recorrerInOrdenRec(raiz.hijoDerecho); // Recorrer derecho
        }
    }

    public void recorrerBfs() {

        Nodo nodoActual = this.raiz;
        Queue<Nodo> bfsQueue = new LinkedList<Nodo>();
        Queue<Nodo> bfsVisitados = new LinkedList<Nodo>();

        bfsQueue.add(nodoActual);

        while (bfsQueue.size() > 0) {

            nodoActual = bfsQueue.poll();
            bfsVisitados.add(nodoActual);

            if (nodoActual.hijoDerecho != null) {
                bfsQueue.add(nodoActual.hijoDerecho);
            }
            if (nodoActual.hijoIzquierdo != null) {
                bfsQueue.add(nodoActual.hijoIzquierdo);
            }

        }

        System.out.println("En BFS: ");

        for (Nodo nodoVisitado : bfsVisitados) {
            System.out.print(nodoVisitado.valor + " ");
        }

    }

    // Preorder
    public void recorrerPreOrder() {
        recorrerPreOrderec(raiz);
    }

    private void recorrerPreOrderec(Nodo nodo) {
        if (nodo != null) {

            System.out.print(nodo.valor + " ");
            if (nodo.hijoIzquierdo != null) {
                recorrerPreOrderec(nodo.hijoIzquierdo);
            }
            if (nodo.hijoDerecho != null) {
                recorrerPreOrderec(nodo.hijoDerecho);
            }

        }

    }

    // postOder recursive
    public void recorrerPostOrder() {
        if (raiz == null) {
            return;
        }
        recorrerPostOrderec(raiz);
    }

    private void recorrerPostOrderec(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        recorrerPostOrderec(nodo.hijoIzquierdo);
        recorrerPostOrderec(nodo.hijoDerecho);
        System.out.print(nodo.valor + " ");
    }

    // Function to delete a node
    public Nodo deleteNode(Nodo root, int key) {
        if (root == null) {
            return null; // Base case: tree is empty
        }

        // Traverse the tree to find the node to delete
        if (key < root.valor) {
            root.hijoIzquierdo = deleteNode(root.hijoIzquierdo, key); // Go left
        } else if (key > root.valor) {
            root.hijoDerecho = deleteNode(root.hijoDerecho, key); // Go hijoDerecho
        } else { // Found the node to delete
            // Case 1: No child (leaf node)
            if (root.hijoIzquierdo == null && root.hijoDerecho == null) {
                return null;
            }
            // Case 2: One child
            else if (root.hijoIzquierdo == null) {
                return root.hijoDerecho; // Return the hijoDerecho child
            } else if (root.hijoDerecho == null) {
                return root.hijoIzquierdo; // Return the left child
            }
            // Case 3: Two children
            else {
                // Find the in-order successor (smallest in the hijoDerecho subtree)
                Nodo successor = findMin(root.hijoDerecho);
                root.valor = successor.valor; // Replace value with successor's value
                root.hijoDerecho = deleteNode(root.hijoDerecho, successor.valor); // Delete successor
            }
        }
        return root;
    }

    // Helper function to find the minimum value node
    private Nodo findMin(Nodo node) {
        while (node.hijoIzquierdo != null) {
            node = node.hijoIzquierdo;
        }
        return node;
    }

    // Print the binary tree as an actual tree from top to bottom
    public void printTree() {
        if (raiz == null) {
            System.out.println("(empty tree)");
            return;
        }

        int height = treeHeight(raiz);
        int maxWidth = (int) Math.pow(2, height - 1);
        int totalWidth = maxWidth * 4; // Adjust spacing based on tree width

        // Queue for BFS
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        int currentLevel = 0;
        int elementsInLevel = 1; // Starts with root

        while (!queue.isEmpty()) {
            int spacesBetween = totalWidth / elementsInLevel; // Adjust spacing for the level
            int spacesBefore = spacesBetween / 2;

            Queue<Nodo> nextLevelQueue = new LinkedList<>();
            System.out.println();

            // Print current level
            for (int i = 0; i < elementsInLevel; i++) {
                Nodo current = queue.poll();

                // Print leading spaces for alignment
                for (int j = 0; j < spacesBefore; j++) {
                    System.out.print(" ");
                }

                // Print the node value or placeholder for null
                if (current != null) {
                    System.out.print(current.valor);
                    nextLevelQueue.add(current.hijoIzquierdo);
                    nextLevelQueue.add(current.hijoDerecho);
                } else {
                    System.out.print(" ");
                    nextLevelQueue.add(null);
                    nextLevelQueue.add(null);
                }

                // Print trailing spaces
                for (int j = 0; j < spacesBetween - spacesBefore; j++) {
                    System.out.print(" ");
                }
            }

            // Prepare for the next level
            elementsInLevel = nextLevelQueue.size();
            queue = nextLevelQueue;
            currentLevel++;

            // Stop printing further levels if only nulls remain
            if (nextLevelQueue.stream().allMatch(n -> n == null))
                break;
        }

        System.out.println();
    }

    // Helper function to compute the height of the tree
    private int treeHeight(Nodo nodo) {
        if (nodo == null)
            return 0;
        return 1 + Math.max(treeHeight(nodo.hijoIzquierdo), treeHeight(nodo.hijoDerecho));
    }

    // Método para buscar un nodo en el árbol
    public Nodo buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    // Método recursivo para buscar el nodo
    private Nodo buscarRec(Nodo nodo, int valor) {
        if (nodo == null || nodo.valor == valor) {
            return nodo; // Devuelve el nodo si es encontrado o null si no existe
        }

        if (valor < nodo.valor) {
            return buscarRec(nodo.hijoIzquierdo, valor); // Buscar en el subárbol izquierdo
        } else {
            return buscarRec(nodo.hijoDerecho, valor); // Buscar en el subárbol derecho
        }
    }

    public static void main(String[] args) {
        BinaryTree arbol = new BinaryTree();
        arbol.insertar(40);
        arbol.insertar(100);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(50);
        arbol.insertar(10);
        arbol.insertar(30);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(90);

        System.out.println("En orden: ");

        // Recorrer en orden: que significa que se recorre el árbol de forma que
        // se imprima el valor de cada nodo en orden ascendente
        // arbol.recorrerInOrden();
        // System.out.println();

        // System.out.println("En BFS: ");
        // arbol.recorrerBfs();

        // Print tree
        arbol.printTree();

        System.out.println();
        System.out.println("Preorder: ");
        arbol.recorrerPreOrder();
        System.out.println();

        System.out.println("Postorder: ");
        arbol.recorrerPostOrder();

        System.out.println();
        System.out.println("En orden: ");
        arbol.recorrerInOrden();

        System.out.println();

        // Eliminar nodo con valor 10
        /*
         * System.out.println("Eliminando nodo con valor 10");
         * Nodo nodo = arbol.deleteNode(arbol.raiz, 10);
         * arbol.printTree();
         * 
         * System.out.println();
         * 
         * // Eliminar nodo con valor 15
         * System.out.println("Eliminando nodo con valor 15");
         * nodo = arbol.deleteNode(arbol.raiz, 100);
         * arbol.printTree();
         */
        // Buscar nodo con valor 10
        System.out.println("Buscando nodo con valor 10");
        Nodo nodoBuscado = arbol.buscar(10);
        System.out.println(nodoBuscado.valor);

    }

}