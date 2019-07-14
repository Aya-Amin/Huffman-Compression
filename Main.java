package huffman2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		
		FileResizer fr = new FileResizer();
		String line = new String();
		String fileName = new String();
		Scanner input = new Scanner(System.in);
		char c;

		System.out.println("Enter file name: ");
		fileName = input.nextLine();

		File file = new File(fileName);

		System.out.println("Choose:\n  1.Compress the file\n  2.Decompress the file");
		c = input.next().charAt(0);

		switch(c){
		
		case '1':
			/* Compression */
			ArrayList<Node> charactersList = new ArrayList<>();
			ArrayList<String> wholeFile = new ArrayList<>();
			MinPQ pq = new MinPQ<Node>(new NodeComparator());
			char[] lineArray;
			int numOfLine = 0;

			try {
				Scanner fileScanner = new Scanner(file);

				while (fileScanner.hasNext()) {
					line = fileScanner.nextLine();
					lineArray = line.toCharArray();

					for (int i = 0; i < lineArray.length; i++) {

						int j;
						for (j = 0; j < charactersList.size(); j++) {
							if (charactersList.get(j).getCharacter().equalsIgnoreCase(Character.toString(lineArray[i]))) {
								charactersList.get(j).setFrequency();
								break;
							}
						}
						if (j == charactersList.size())
							charactersList.add(new Node(Character.toString(lineArray[i])));
						wholeFile.add( Character.toString(lineArray[i]) );
					}
					numOfLine++;
					wholeFile.add("\n");
				}
				charactersList.add(new Node("\n", numOfLine));
				
				//should we insert eof??
//				charactersList.add(new Node("\u001a")); 

				for (int i = 0; i < charactersList.size(); i++)
					pq.insert(charactersList.get(i));

				Tree hfTree = fr.compress(pq, file);
//				hfTree.printTree(hfTree.getRoot());
				fr.writeToFile(fileName, wholeFile, charactersList);

			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}
			break;
			
		case '2':
			/* Decompression */
			try {
				Scanner fileScanner = new Scanner(file);
				HashMap<String, String> header = new HashMap<>();
				String[] headerLine = new String[2];

				line = fileScanner.nextLine(); 
				while (fileScanner.hasNext()) {

					headerLine = line.split(" ");
					if(headerLine.length == 1)
						header.put(headerLine[0], "\n");
					else
						header.put(headerLine[1], headerLine[0]);
					
					System.out.println(header);
	
//					System.out.print(header);
//					System.out.println();
					
					line = fileScanner.nextLine();
					
//					System.out.println(line);

					if ( line.length()>0 && Character.isDigit(line.charAt(0)))
						break;
				}
				fr.decompress(line, header, fileName);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

}
