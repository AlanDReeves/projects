package Chapter17_lab;

import java.util.*;

public class BSTCheckerGPT {
    //this class borrowed from chatGPT. I can't believe it works.

    public static Node checkBSTValidity(Node root) {
        Node loopCheck = checkForLoop(root);
        if (loopCheck != null) {
            return loopCheck;
        }
        return checkBSTValidity(root, null, null);
    }

    // Helper method to recursively check the validity of the BST
    private static Node checkBSTValidity(Node node, Integer min, Integer max) {
        // Base case: if the node is null, return null (no violation)
        if (node == null) {
            return null;
        }

        // Check if the current node violates the min/max constraints
        if ((min != null && node.key <= min) || (max != null && node.key >= max)) {
            return node;
        }

        // Recursively check the left subtree, updating the max constraint
        Node leftViolation = checkBSTValidity(node.left, min, node.key);
        if (leftViolation != null) {
            return leftViolation; // Return the first violation found
        }

        // Recursively check the right subtree, updating the min constraint
        Node rightViolation = checkBSTValidity(node.right, node.key, max);
        if (rightViolation != null) {
            return rightViolation; // Return the first violation found
        }

        // If no violations found, return null
        return null;

}

//the following is my own code

private static Node checkForLoop(Node root) {
    HashSet<Node> nodeList = new HashSet<>();
    return checkForLoop(root, nodeList);
}


//returns the wrong node somehow.
private static Node checkForLoop(Node root, HashSet<Node> nodeList) {
    if (root == null) {
        return null;
    }

    if (root.left != null && nodeList.contains(root.left)) {
        return root;
    }
    if (root.right != null && nodeList.contains(root.right)) {
        return root;
    }

    nodeList.add(root);

    Node leftCheck = checkForLoop(root.left, nodeList);
    Node rightCheck = checkForLoop(root.right, nodeList);

    if (leftCheck != null) {
        return leftCheck;
    }
    if (rightCheck != null) {
        return rightCheck;
    }
    
    nodeList.remove(root);


    return null;
    }

    private static Node noSetLoopCheck(Node root) {
        


        return null;
    }


}
