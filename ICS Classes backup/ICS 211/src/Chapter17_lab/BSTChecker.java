package Chapter17_lab;

import java.util.*;

public class BSTChecker {
	public static Node checkBSTValidity(Node rootNode) {
	   
	   if (cycleChecker(rootNode) != null) {
	      return cycleChecker(rootNode);
	   }
	   //System.out.println("cycleChecker passed");
	   
	   if (leftSubtreeChecker(rootNode) != null) {
		   return leftSubtreeChecker(rootNode);
	   }
	   //System.out.println("leftSubtreeChecker passed");
	   
	   if (rightSubtreeChecker(rootNode) != null) {
	      return rightSubtreeChecker(rootNode);
	   }
	   //System.out.println("rightSubtreeChecker passed");
	   
	   
	   
	   //place holder.
	   return null;
	}
	
	public static Node leftSubtreeChecker(Node rootNode) {
	   	   
	   //check only node to the left on first case, save key value
	   int key = rootNode.key;
	   if (rootNode.left.key > key) {
	      return rootNode.left;
	   }
	   while (rootNode.left != null) {
	      //move to left node
	      rootNode = rootNode.left;
	      //check that left and right node are not more than key value
	      //repeat
	      return leftCheckHelper(rootNode, key);
	   }
	   
   return null;
	}
	
	public static Node leftCheckHelper(Node rootNode, int key) {
	   if (rootNode.left != null) {
	      if (rootNode.left.key > key) {
	         return rootNode.left;
	      }
	      return leftCheckHelper(rootNode.left, key);
	   }
	   if (rootNode.right != null) {
	      if (rootNode.right.key > key) {
	         return rootNode.right;
	      }
	      return leftCheckHelper(rootNode.right, key);
	   }
	   
	   return null;
	}
	
	public static Node rightSubtreeChecker(Node rootNode) {
	   int key = rootNode.key;
	   if (rootNode.right != null) {
	      if (rootNode.right.key < key) {
	         return rootNode.right;
	      }
	   }
	   
	   while (rootNode.right != null) {
	   rootNode = rootNode.right;
	   
	   return rightCheckHelper(rootNode, key);
	   }
	   return null;
	}
	
	public static Node rightCheckHelper(Node rootNode, int key) {
	   if (rootNode.left != null) {
	      if (rootNode.left.key < key) {
	         return rootNode.left;
	      }
	      return rightCheckHelper(rootNode.left, key);
	   }
	   if (rootNode.right != null) {
	      if (rootNode.right.key < key) {
	         return rootNode.right;
	      }
	      return rightCheckHelper(rootNode.right, key);
	      
	   }
	   return null;
	}
	
// 	public static Node ancestorChecker(Node rootNode) {
// 	   ArrayList<Integer> ancestorList = new ArrayList<>();
// 	   ancestorList.add(rootNode.key);
	   
// 	   return ancestorHelper(rootNode, ancestorList);
//    	}
	
// 	public static Node ancestorHelper(Node rootNode, ArrayList<Integer> ancestorList) {
// 	   ancestorList.add(rootNode.key);
// 	   if (rootNode.left != null) {
// 	      if (ancestorList.contains(rootNode.left.key)) {
// 	         return rootNode.left;
// 	      }
// 	      return ancestorHelper(rootNode.left, ancestorList);
// 	   }
// 	   if (rootNode.right != null) {
// 	      if (ancestorList.contains(rootNode.right.key)) {
// 	         return rootNode.right;
// 	      }
// 	      return ancestorHelper(rootNode.right, ancestorList);
// 	   }
// 	   return null;
// 	}

   public static Node cycleChecker(Node root) {
      HashSet<Node> ancestors = new HashSet<>();
      return cycleHelper(root, ancestors);
      
   }
   
   public static Node cycleHelper(Node root, HashSet<Node> ancestors) {
      if (ancestors.contains(root)) {
         return root;
      }
      ancestors.add(root);
      
      if (root.left != null) {
         Node left_cycle = cycleHelper(root.left, ancestors);
         return left_cycle;
      }
      if (root.right != null) {
         Node right_cycle = cycleHelper(root.right, ancestors);
         return right_cycle;
      }
      
      
      return null;
      
   }


   //scratch notes and unfinished ideas

   public static Node bstCheck(Node node) {

	int max = node.key;
	int min = node.key;


	//base case
	if (node.left == null && node.right == null) {
		return null;
	} 
	//recursive case
	else {
		//check if left node violates
		if (node.left != null) {
			if (node.left.key > max) {
				return node.left;
			}
		}

	}

	return null;
   }

   public static Node leftSideCheck(Node node, int firstKey) {
	//this method takes the first left node and checks that no greater key value exists

	if (node.left != null) {
		node = node.left;
	}

	//base case
	if (node.key > firstKey) {
		return node;
	}


	//recursive case
	if (node.left != null) {
		Node leftNode = leftSideCheck(node.left, firstKey);
		if (leftNode != null) {
			return leftNode;
		}
	}
	if (node.right != null) {
		Node rightNode = leftSideCheck(node.right, firstKey);
		if (rightNode != null) {
			return rightNode;
		}
	}

	return null;
   }

//    public static Node bstCheck2(Node node) {

// 	if (node == null) {
// 		return null;
// 	}

// 	return null;
//    }
   
   
}
