package huffman2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileResizer {

	private int numOfNodes;
	private Tree huffmanTree;

	public Tree compress(MinPQ<Node> pq, File inputFile) {

		numOfNodes = pq.size();
		// Build tree
		huffmanTree = new Tree();
		while (pq.size() > 1) {

			//			System.out.println();
			//			for (Node n: pq)
			//				System.out.println(n.getCharacter() + " " + n.getFrequency());

			Node n1 = pq.delMin();
			Node n2 = pq.delMin();

			Node n = new Node(n1.getCharacter() + n2.getCharacter(), n1.getFrequency() + n2.getFrequency());
			n.setLeftChild(n1);
			n.setRightChild(n2);
			n1.setParent(n);
			n2.setParent(n);
			pq.insert(n);
		}
		huffmanTree.setRoot(pq.delMin());

		//		System.out.println(huffmanTree.getRoot().getCharacter() + " " + huffmanTree.getRoot().getFrequency());
		//		System.out.println(huffmanTree.getRoot().getLeftChild().getCharacter() + " " + huffmanTree.getRoot().getLeftChild().getFrequency());
		//		System.out.println(huffmanTree.getRoot().getRightChild().getCharacter() + " " + huffmanTree.getRoot().getRightChild().getFrequency());
		//		huffmanTree.printTree(huffmanTree.getRoot());

		// Traverse tree to assign codes to characters
		ArrayList<Integer> coding = new ArrayList<>();
		int i = 0;
		this.generateCode(huffmanTree.getRoot(), coding, i);

		return huffmanTree;
	}

	private void generateCode(Node current, ArrayList<Integer> code, int top) { 

		//		System.out.println(current.getCharacter() + " " + current.getFrequency());
		//		if(current.getLeftChild() != null)
		//			generateCode(current.getLeftChild());
		//		if(current.getRightChild() != null)
		//			generateCode(current.getRightChild());

		// Assign 0 to left edge and recur 
		if (current.getLeftChild() != null) { 
			code.add(top, 0); 
			generateCode(current.getLeftChild(), code, top + 1); 
		}
		
		// Assign 1 to right edge and recur 
		if (current.getRightChild() != null) { 
			code.add(top, 1); 
			generateCode(current.getRightChild(), code, top + 1); 
		} 

		// If this is a leaf node, then 
		// it contains one of the input 
		// characters, print the character 
		// and its code from arr[]

		if (current.getLeftChild() == null && current.getRightChild() == null) { 

			String str = new String();
			str = Integer.toString(code.get(0));
			for (int i=1; i < code.size(); i++)
				str = str + Integer.toString(code.get(i));
			current.setCode(str);       
		} 
	}

	public void writeToFile(String inputFileName, ArrayList<String> inputFile,
			ArrayList<Node> characters){

		try {
			File outputFile = new File("Compressed " + inputFileName);
			PrintWriter filePW = new PrintWriter(outputFile);
			HashMap<String, String> codeScheme = new HashMap<>();

			//write header first
			for(int i=0; i<characters.size(); i++){
				filePW.println( characters.get(i).getCharacter() + " " + characters.get(i).getCode() );
				codeScheme.put(characters.get(i).getCharacter(), characters.get(i).getCode());
				
//				System.out.println(characters.get(i).getCharacter() +  characters.get(i).getCode());	
			}
			//Write compressed data
			for(int i=0; i<inputFile.size(); i++){
				filePW.print( codeScheme.get(inputFile.get(i)) );
				
//				System.out.print(codeScheme.get(inputFile.get(i)));
//				System.out.println( codeScheme.get(inputFile.get(i)) + inputFile.get(i));
			}
			filePW.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decompress(String line, HashMap<String, String> header, String fileName){

		try {
			File outputFile = new File("Decompressed " + fileName);
			PrintWriter filePW = new PrintWriter(outputFile);
			char[] lineArray = line.toCharArray();
			String code = null;

			for (int i = 0; i < lineArray.length; i++) {

				if (code == null)
					code = Character.toString(lineArray[i]);
				else
					code = code + Character.toString(lineArray[i]);

				if (header.containsKey(code)) {
//					if(header.get(code).compareTo("\n") == 0)
//						filePW.println();
//					else
					
					filePW.print(header.get(code));
					
//					System.out.println(code);
//					System.out.print(header.get(code));
//					System.out.println();
					
					code = null;
				}
			}
			filePW.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}


	//	private void generateCode(Node current, ArrayList<Integer> code, int i) {
	//
	//		// if we go to left then add "0" to the code.
	//		// if we go to the right add"1" to the code.
	//
	//		// recursive calls for left and
	//		// right sub-tree of the generated tree.
	//		if (current.getLeftChild() != null) {
	//			code.add(i, 0);
	//			i++;
	//			generateCode(current.getLeftChild(), code, i++);
	//		}
	//
	//		if (current.getRightChild() != null) {
	//			code.add(i, 1);
	//			i++;
	//			generateCode(current.getRightChild(), code, i++);
	//		}
	//		
	//		if (current.getLeftChild() == null && current.getRightChild() == null) {
	//			current.setCode(code.toString());
	//			return;
	//		}
	//	}

}
