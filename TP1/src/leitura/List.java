package leitura;

class List {
//--> ATRIBUTOS
	private Node head;
	private Node rear;
	private int l_size;
	private static final int LMAXSIZE = 1000000;
//--> NODE
	private static class Node {
	//--> ATRIBUTOS
		private byte[] data;
		private int data_size;
		private Node next;		
	//--> CONSTRUTOR
		private Node (byte[] data) {
			this.data = data;
			data_size = data.length;
			next = null;
		}
	}//END_NODE
//--> CONSTRUTOR
	public List () {
		head = rear = null;
		l_size = 0;
	}
	
//--> METODOS
	// method to check if the list is empty 
	public boolean isEmpty () {
		if (l_size == 0)
			return true;
		return false;
	}
	// method to check if the list is full
	private boolean isFull (int size) {
		if (size + l_size >= LMAXSIZE)
			return true;
		return false;
	}
	// method to insert a byte array into the list
	public void insert (byte[] data) throws Exception {
		if (isFull(data.length)) 
			throw new Exception();
		try {
			if (head == null) {
				head = new Node(data);
				l_size += head.data_size;
				rear = head;
				return;
			}
			rear.next = new Node(data);
			rear = rear.next;
			l_size += rear.data_size;	
		} catch (Exception e) {
			raiseException(e);
		}
	}
	// method to remove a byte array in the head of the list
	public byte[] remove () {
		byte[] temp = head.data;
		l_size -= head.data_size;
		if (head.next == null)
			head = rear = null;
		else
			head = head.next;
		return temp;
	}
	// method to print a list from the beginning
	public void print () {
		Node temp = head;
		System.out.println(l_size);
		while (temp != null) {
			System.out.println(temp.data.toString());
			temp = temp.next;
		}
	}
}//END_LIST
