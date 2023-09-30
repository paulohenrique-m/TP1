package leitura;

public class TreeNode {
//--> ATRIBUTOS
    int[] keys; // Array de chaves
    Ufo[] ufoData; // Array de dados relacionados às chaves
    TreeNode[] children; // Array de filhos
    int numKeys; // Número de chaves atualmente no nó
    boolean isLeaf; // Indica se o nó é uma folha
//--> CONSTRUTOR
    public TreeNode(boolean leaf) {
        this.isLeaf = leaf;
        this.keys = new int[7]; // Tamanho máximo de 7 chaves em um nó
        this.ufoData = new Ufo[7];//Mudar para ARQUIVO
        this.children = new TreeNode[8]; // Máximo de 8 filhos em um nó
        this.numKeys = 0;
    }
//--> SETTERS & GETTERS
    public int getKey(int index) {
        return keys[index];
    }

    public void setKey(int index, int key) {
        keys[index] = key;
    }

    public Ufo getUfoData(int index) {
        return ufoData[index];
    }

    public void setUfoData(int index, Ufo ufo) {
        ufoData[index] = ufo;
    }

    public TreeNode getChild(int index) {
        return children[index];
    }

    public void setChild(int index, TreeNode child) {
        children[index] = child;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public boolean isLeaf() {
        return isLeaf;
    }
}