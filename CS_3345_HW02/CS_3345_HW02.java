import java.io.*;
import java.util.Scanner;

public class CS_3345_HW02 {

	public static void main(String[] args) {
				
		AVLTree tree = new AVLTree();
			
		String inputFile ="input.txt";
		
		//Adds the ISBN numbers to the AVL tree
		try
		{
			Scanner input = new Scanner(new FileReader(inputFile));
			while(input.hasNextInt())
			{
				int isbn = input.nextInt();
				tree.root = tree.insert(tree.root, isbn);
			}
			input.close();
		} 
		
		catch(FileNotFoundException filenotfoundexception)
		{
			System.out.println("File not found.");
		}
		
		System.out.println("Preorder of tree: ");
		tree.preOrder(tree.root);
	}

}
