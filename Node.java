package huffman2;

public class Node {
	
	private String character;
	private int frequency;
	private String code;
	
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	
	public Node(String character) {
		this.character = character;
		this.frequency = 1;
	}
	
	public Node(String character, int frequency) {
		this.character = character;
		this.frequency = frequency;
	}
	
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency() {
		this.frequency = this.frequency + 1;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	

}
