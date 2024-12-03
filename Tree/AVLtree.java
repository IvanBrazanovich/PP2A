class AVLTree {

    // Nodo del árbol AVL
    class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    public class AVLtree {

    }

    private Node root;

    // Obtener la altura de un nodo
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Obtener el factor de balance de un nodo
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Rotación derecha
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realizar la rotación
        x.right = y;
        y.left = T2;

        // Actualizar alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotación izquierda
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realizar la rotación
        y.left = x;
        x.right = T2;

        // Actualizar alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insertar un nodo en el árbol AVL
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        // 1. Realizar la inserción normal de un BST
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // No se permiten claves duplicadas

        // 2. Actualizar la altura del nodo actual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Obtener el factor de balance
        int balance = getBalance(node);

        // 4. Balancear el árbol si está desbalanceado

        // Caso Izquierda-Izquierda
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // Caso Derecha-Derecha
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // Caso Izquierda-Derecha
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Método para imprimir el árbol en orden
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    // Método para imprimir el árbol de forma visual
    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.key);
            printTree(node.left, prefix + (isTail ? "    " : "│   "), false);
            printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("In-order traversal del árbol AVL:");
        tree.inOrderTraversal();

        System.out.println();

        // print
        tree.printTree();
    }
}
