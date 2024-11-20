
import java.util.List;//// for using in findPath method and nowhere else

public class Treap implements Searchable {

    private static class Node {
     public Node SuperiorNode;
     String key;
        int heapValue;
        Node left, right;

        Node(String key, int heapValue) {
            this.key = key;
            this.heapValue = heapValue;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public Treap() {
        this.root = null;
    }
//##################################################################################################################
    @Override
    public boolean add(String key, int heapValue) {
        // Check if the provided key is null.
        if (key == null) {
            return false; // Return false if key are null
        }

        // As stated in question, adhering to case insensitivity we need to change the key to lower case
        key = key.toLowerCase();

        //if the key is already present in the treap
        if (isKeyPresent(root, key)) {
            return false; // Return false if the key already exists
        }

        // Insertion of new node while following the Binary Search Tree property
        root = addHelper(root, key, heapValue);
        return true; // Return true indicating successful addition
    }

    // this method check if the key is already present in the treap
    private boolean isKeyPresent(Node node, String key) {
        //if the current node is null, that means key is not present
        if (node == null) {
            return false;
        }

        // using compareTo function to compare the lengths
        int ValCompare = key.compareTo(node.key);
        if (ValCompare < 0) {
            // If the key is smaller, search the left subtree
            return isKeyPresent(node.left, key);
        } else if (ValCompare > 0) {
            // If the key is larger, search the right subtree
            return isKeyPresent(node.right, key);
        } else {
            return true; // else key found in same spot in the treap
        }
    }

    // supporting method for add
    private Node addHelper(Node node, String key, int heapValue) {

        if (node == null) {
            return new Node(key, heapValue);
        }

        // Compare the key with the current node's key for BST ordering
        int ValCompare = key.compareTo(node.key);
        if (ValCompare < 0) {
            // If the key is smaller, insert it into the left subtree
            node.left = addHelper(node.left, key, heapValue);
        } else if (ValCompare > 0) {
            // If the key is larger, insert it into the right subtree
            node.right = addHelper(node.right, key, heapValue);
        } else {
            // the unusual case, we have already check for node
            return node;
        }

        // applying max-heap property after inserting using restoreHeap method
        return restoreHeap(node);
    }

    // restoreHeap method implementation
    private Node restoreHeap(Node node) {
        // if left child has a higher heap value then perform right rotation
        if (node.left != null && node.left.heapValue > node.heapValue) {
            node = rotateToright(node); // Rotate to the right to restore heap property
        }
        //if right child has a higher heap value then perform left rotation
        else if (node.right != null && node.right.heapValue > node.heapValue) {
            node = rotateToleft(node); // Rotate to the left to restore heap property
        }
        return node; // Return the rotated node
    }

    // performing the right rotation
    private Node rotateToright(Node node) {
        // Temporarily store the left child as the new root
        Node tempRoot = node.left;
        // Move the right subtree of the left child to the left of the current node
        node.left = tempRoot.right;
        // Set the current node as the right child of the new root
        tempRoot.right = node;
        return tempRoot; // Return the new root after the rotation
    }

    // performing left rotation
    private Node rotateToleft(Node node) {
        // Temporarily store the right child as the new root
        Node tempRoot = node.right;
        // Move the left subtree of the right child to the right of the current node
        node.right = tempRoot.left;
        // Set the current node as the left child of the new root
        tempRoot.left = node;
        return tempRoot; // Return the new root after the rotation
    }


    //##################################################################################################################




    //##################################################################################################################
    @Override

    public boolean build(String[] keys, int[] heapValues) {
        // Verify that the number of keys matches the number of heap values
        if (keys.length != heapValues.length) {
            return false; // If not, return false as the input arrays are inconsistent
        }

        // Loop through the keys and heap values to check for valid inputs
        for (int i = 0; i < keys.length; i++) {
            // Check for invalid cases: null key or non-positive heap values
            if (keys[i] == null || heapValues[i] <= 0) {
                return false; // Return false if any invalid input is found
            }
            // Convert each key to lowercase to ensure case-insensitive comparisons
            keys[i] = keys[i].toLowerCase();
        }

        // Sort the keys in lexicographical order and swap corresponding heap values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - i - 1; j++) {
                // Compare adjacent keys and swap if they are out of order
                if (keys[j].compareTo(keys[j + 1]) > 0) {
                    // Swap the keys
                    String SwapKey = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = SwapKey;

                    // Swap the corresponding heap values to maintain consistency
                    int SwapHeap = heapValues[j];
                    heapValues[j] = heapValues[j + 1];
                    heapValues[j + 1] = SwapHeap;
                }
            }
        }

        // Construct the treap using the sorted keys and their corresponding heap values
        root = buildHelper(keys, heapValues, 0, keys.length - 1);

        // Ensure the heap property is maintained throughout the treap
        adjustHeapProperty(root);

        return true; // Return true to indicate successful treap construction
    }

    // Helper method to recursively build the treap using a binary search approach
    private Node buildHelper(String[] keys, int[] heapValues, int begin, int end) {
        // if the range is invalid, return null
        if (begin > end) {
            return null;
        }

        // Find the middle element to be the root using a binary search approach
        int mid = (begin + end) / 2;

        // Create a new node with the middle key and its corresponding heap value
        Node node = new Node(keys[mid], heapValues[mid]);

        // Recursive calls for building the left and right subtrees
        node.left = buildHelper(keys, heapValues, begin, mid - 1); // Build the left subtree
        node.right = buildHelper(keys, heapValues, mid + 1, end);  // Build the right subtree

        return node; // created node becomes root of subtree
    }

    // method to enforce the heap property of the treap
    private void adjustHeapProperty(Node node) {
        // if the node is null, return (no need for adjustment)
        if (node == null) return;

        // Recursively adjust the left and right subtrees first
        adjustHeapProperty(node.left);
        adjustHeapProperty(node.right);

        // Check if the left child violates the max-heap property
        if (node.left != null && node.left.heapValue > node.heapValue) {
            node = rotateToright(node); // Perform right rotation to restore the heap property
        }

        // Check if the right child violates the max-heap property
        if (node.right != null && node.right.heapValue > node.heapValue) {
            node = rotateToleft(node); // Perform left rotation to restore the heap property
        }
    }



//##################################################################################################################







//###################################################################################################################
    @Override

    public boolean remove(String key) {
        // Return false if the key is null (invalid input)
        if (key == null) {
            return false;
        }

        // Convert the key to lowercase for case-insensitive comparison
        key = key.toLowerCase();

        // Call the helper method to remove the node with the given key
        root = removeHelper(root, key);
        return true; // Return true after removal process
    }

    private Node removeHelper(Node node, String key) {
        // If the node is null, return null (base case for recursion)
        if (node == null) {
            return null;
        }

        // Compare the key with the current node key
        int ValCompare = key.compareTo(node.key);
        if (ValCompare < 0) {
            // If key is smaller, recursion call on the left subtree
            node.left = removeHelper(node.left, key);
        } else if (ValCompare > 0) {
            // If key is larger, recursion call on the right subtree
            node.right = removeHelper(node.right, key);
        } else {
            // Node with matching key found, handle removal

            // If the node has only a right child, return the right child
            if (node.left == null) {
                return node.right;
            }
            // If the node has only a left child, return the left child
            else if (node.right == null) {
                return node.left;
            }

            // If the node has two children, find the successor (smallest node in the right subtree)
            Node successor = getMinNode(node.right);
            // Replace the current node's key and heap value with the successor's
            node.key = successor.key;
            node.heapValue = successor.heapValue;
            // Recursively remove the successor from the right subtree
            node.right = removeHelper(node.right, successor.key);
        }

        // Restore the heap property after removal
        return restoreHeap(node);
    }

    private Node getMinNode(Node node) {
        // Traverse to the leftmost (smallest) node in the subtree
        while (node.left != null) {
            node = node.left;
        }
        return node; // Return the node with the minimum key
    }




//##################################################################################################################




//##################################################################################################################

    @Override

    public boolean changeOrder(String key, int newHeapValue) {
        // Convert the key to lowercase for case-insensitive comparison
        key = key.toLowerCase();

        Node current = root;
        // We Traverse the treap to find the node with the matching key
        while (current != null) {
            int ValCompare = key.compareTo(current.key); // Compare the key with the current node's key
            if (ValCompare < 0) {
                // If the key is smaller, move to the left child
                current = current.left;
            } else if (ValCompare > 0) {
                // If the key is larger, move to the right child
                current = current.right;
            } else {
                // Key matches, check the new heap value
                if (newHeapValue <= 0) {
                    return false; // Invalid heap value, return false
                }

                // Update the current node heap value
                current.heapValue = newHeapValue;


                for (current = current; current != null && current.SuperiorNode != null && current.heapValue > current.SuperiorNode.heapValue; ) {
                    if (current == current.SuperiorNode.left) {
                        current = rotateToright(current.SuperiorNode); // Rotate right to maintain heap property
                    } else {
                        current = rotateToleft(current.SuperiorNode);  // Rotate left to maintain heap property
                    }
                }

                return true; // Return true after successfully updating and rebalancing
            }
        }

        return false; // Return false if the key is not found in the treap
    }

//##############################################################################################################################


//##############################################################################################################################

    public boolean find(String key) {
        // Change the key to lowercase so it doesn't matter if letters are upper or lowercase, as we done earlier also
        key = key.toLowerCase();
        // Use the helper method to look for the key starting from the root node
        return findhelper(root, key);
    }

    private boolean findhelper(Node node, String key) {
        // If the current node is null, it means the key is not in the tree
        if (node == null) {
            return false; // Key not found
        }

        // Compare the given key with the key in the current node
        int ValCompare = key.compareTo(node.key);

        if (ValCompare == 0) {
            return true; // Key found
        }

        // Search the left side of the tree first, then the right side
        return findhelper(node.left, key) || findhelper(node.right, key);
    }

/*
##################################################################################################################

##################################################################################################################

*/


    public List<String> findPath(String key) {
        List<String> path = new MyCustomList<>(); // Now this works

        // Use the helper method to find the key and its path, if found
        if (findPathHelper(root, key, path)) {
            return path;  // Return the path if found
        } else {
            System.out.println("Key not found.");
            return new MyCustomList<>();  // Return an empty custom list
        }
    }


    private boolean findPathHelper(Node currentNode, String key, List<String> path) {
        if (currentNode == null) {
            return false;  // Key not found in this branch
        }

        // Case-insensitive search
        int comparison = key.compareToIgnoreCase(currentNode.key);

        // Search in the left subtree
        if (comparison < 0) {
            if (findPathHelper(currentNode.left, key, path)) {
                path.add(currentNode.key);  // Add the current node to the path
                return true;
            }
        }

        // Search in the right subtree
        if (comparison > 0) {
            if (findPathHelper(currentNode.right, key, path)) {
                path.add(currentNode.key);  // Add the current node to the path
                return true;
            }
        }

        // If the key matches the current node
        if (comparison == 0) {
            path.add(currentNode.key);  // Start adding the path from this node
            return true;
        }

        return false;  // Key not found in this part of the tree
    }


  //##################################################################################################################

  //##################################################################################################################

    public int size() {
        if (root == null) {
            return 0;  // If the tree is empty, return 0
        } else {
            return sizeHelper(root);  // Call the helper method to count nodes
        }
    }

    // Helper method to recursively count the number of nodes
    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;  // If the node is null, return 0
        }
        // Count the current node, then recursively count left and right subtrees
        int leftSize = sizeHelper(node.left);
        int rightSize = sizeHelper(node.right);

        return 1 + leftSize + rightSize;  // Total count is 1 + (counts from both subtrees)
    }

}






