package huffman2;

public class Tree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void printTree(Node n) {
		if (n.getLeftChild() == null && n.getRightChild() == null)
			System.out.println(n.getCharacter() + " " + n.getCode() + " "
					+ n.getFrequency());
		else {
			printTree(n.getLeftChild());
			printTree(n.getRightChild());
		}
		
//			System.out.println(n.getCharacter() + " " + n.getFrequency());
//			if(n.getLeftChild() != null)
//				printTree(n.getLeftChild());
//			if(n.getRightChild() != null)
//				printTree(n.getRightChild());	
	}

}
