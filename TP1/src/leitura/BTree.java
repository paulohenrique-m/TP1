package leitura;

public class BTree {
//--> ATRIBUTOS
    private TreeNode root;
    // max order
    private static final int MAX_KEYS = 7;
//--> CONSTRUTOR
    public BTree() {
        root = new TreeNode();
    }
//--> METODOS
    // calling insert method
    public void insert(int id, Ufo ufo) {
        insert(root, id, ufo);
    }
    // calling search method
    public Ufo search(int id) {
        return search(root, id);
    }
    // method to insert a UFO object in a tree using his id
    private void insert(TreeNode node, int id, Ufo ufo) {
        if (node.getNumKeys() < MAX_KEYS) {
            // if the node is full, insert the key and data directly
            int index = node.getNumKeys();
            while (index > 0 && id < node.getKey(index - 1)) {
                node.setKey(index, node.getKey(index - 1));
                node.setUfoData(index, node.getUfoData(index - 1));
                index--;
            }
            node.setKey(index, id);
            node.setUfoData(index, ufo);
            node.setNumKeys(node.getNumKeys() + 1);
        } else {
            // otherwise, split the node and recursively isert into the parent node above
            split(node);
            int index = 0;
            if (id > node.getKey(0)) {
                while (index < node.getNumKeys() && id > node.getKey(index)) {
                    index++;
                }
            }
            insert(node.getChild(index), id, ufo);
        }
    }
    // method to split a node
    private void split(TreeNode node) {
        // Create 2 new nodes to split
        TreeNode leftChild = new TreeNode(node.isLeaf());
        TreeNode rightChild = new TreeNode(node.isLeaf());

        // Find a key and data in the middle 
        int middleKey = node.getKey(MAX_KEYS / 2);
        Ufo middleUfo = node.getUfoData(MAX_KEYS / 2);// Colocar getid

        // put the keys, data and children in left and right nodes
        for (int i = 0; i < MAX_KEYS / 2; i++) {
            leftChild.setKey(i, node.getKey(i));
            leftChild.setUfoData(i, node.getUfoData(i));
            leftChild.setChild(i, node.getChild(i));
            leftChild.setNumKeys(leftChild.getNumKeys() + 1);
    
            rightChild.setKey(i, node.getKey(i + (MAX_KEYS / 2) + 1));
            rightChild.setUfoData(i, node.getUfoData(i + (MAX_KEYS / 2) + 1));
            rightChild.setChild(i, node.getChild(i + (MAX_KEYS / 2) + 1));
            rightChild.setNumKeys(rightChild.getNumKeys() + 1);
        }
    
        leftChild.setChild(MAX_KEYS / 2, node.getChild(MAX_KEYS / 2));
        rightChild.setChild(MAX_KEYS / 2, node.getChild(MAX_KEYS));

        // update orinigal node to get middle key and data
        node.setKey(0, middleKey);
        node.setUfoData(0, middleUfo);
        node.setChild(0, leftChild);
        node.setChild(1, rightChild);
        node.setNumKeys(1);
    }
    // Method to search into a node using id
    private Ufo search(TreeNode node, int id) {
        int i = 0;
        while (i < node.getNumKeys() && id > node.getKey(i)) {
            i++;
        }
    
        if (i < node.getNumKeys() && id == node.getKey(i)) {
            return node.getUfoData(i); // Encontrou o ID, retornando os dados relacionados.
        } else if (node.isLeaf()) {
            return null; // Chegou a uma folha e não encontrou o ID.
        } else {
            return search(node.getChild(i), id); // Recursivamente, continue a busca nos filhos apropriados.
        }
    }
}
