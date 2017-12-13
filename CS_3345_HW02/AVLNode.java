//AVLNode class
class AVLNode {
	int key;
	Book value;
	int height;
	AVLNode leftPtr;
	AVLNode rightPtr;
	
	AVLNode(int d)
	{
		key = d;
		height = 1;
	}
}

class AVLTree{
	
	AVLNode root;
	
	//Get height function
	int getHeight(AVLNode N) {
		if(N == null)
			return 0;
		
		return N.height;
	}
	
	//Get max of two integers
	int max(int a, int b)
	{
		return (a > b) ? a : b;
	}
	
	//Right rotation function
	AVLNode rightRotate(AVLNode y)
	{		
		AVLNode x = y.leftPtr;
		AVLNode T2 = x.rightPtr;
		
		//Rotation
		x.rightPtr = y;
		y.leftPtr = T2;
		
		//Update heights
		y.height = max(getHeight(y.leftPtr), getHeight(y.rightPtr)) + 1;
		x.height = max(getHeight(x.leftPtr), getHeight(x.rightPtr)) + 1;
		

		//Returns root
		return x;
	}
	
	//Left rotation function
	AVLNode leftRotate(AVLNode x) 
	{
		AVLNode y = x.rightPtr;
		AVLNode T2 = y.leftPtr;
		
		//Rotation
		y.leftPtr = x;
		x.rightPtr = T2;
		
		//Update heights
		x.height = max(getHeight(x.leftPtr), getHeight(x.rightPtr)) + 1;
		y.height = max(getHeight(y.leftPtr), getHeight(y.rightPtr)) + 1;

		//Returns root
		return y;
	}
	
	//Balance factor of node N
	int getBalance(AVLNode N)
	{
		if(N == null)
			return 0;
		
		return getHeight(N.leftPtr) - getHeight(N.rightPtr);
	}
	
	//Insertion function
	AVLNode insert(AVLNode node, int key)
	{

		//Normal BST insertion
		if(node == null)
			return (new AVLNode(key));
		
		if(key < node.key)
			node.leftPtr = insert(node.leftPtr, key);
		else if (key > node.key)
			node.rightPtr = insert(node.rightPtr, key);
		else
			return node;
		
		//Update height of ancestor node
		node.height = 1 + max(getHeight(node.leftPtr), getHeight(node.rightPtr));
		
		//Check to see if node became unbalanced by getting balance factor of ancestor node
		int balance = getBalance(node);
		
		//Four cases for unbalanced nodes
		//These also output where the imbalance occurred and the rotation used to balance it
		//This is the left left rotation case 
		if(balance > 1 && key < node.leftPtr.key)
		{
			System.out.println("Imabalance occured at ISBN:" + node.key + " fixed in left left rotation");
			return rightRotate(node);
		}
		
		//Right Right rotation case
		if(balance < -1 && key > node.rightPtr.key)
		{
			System.out.println("Imabalance occured at ISBN:" + node.key + " fixed in right right rotation");
			return leftRotate(node);
		}
		//Left Right rotation case
		if(balance > 1 && key > node.leftPtr.key)
		{
			System.out.println("Imabalance occured at ISBN:" + node.key + " fixed in left right rotation");
			node.leftPtr = leftRotate(node.leftPtr);
			return rightRotate(node);
		}
		
		//Right Left rotation case
		if(balance < -1 && key < node.rightPtr.key)
		{
			System.out.println("Imabalance occured at ISBN:" + node.key + " fixed in right left rotation");
			node.rightPtr = rightRotate(node.rightPtr);
			return leftRotate(node);
		}
		//return node pointer
		return node;
	}
	
	//Preorder traversal of tree
	void preOrder(AVLNode node)
	{
		if(node != null)
		{
			System.out.print(node.key + " ");
			preOrder(node.leftPtr);
			preOrder(node.rightPtr);
		}
	}
}