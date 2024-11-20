public class SearchTree {

    private class Node {
        String key;
        int priority;
        Node left, right;

        Node(String key, int priority) {
            this.key = key.toLowerCase(); // Case-insensitive
            this.priority = priority;
            left = right = null;
        }
    }

    private Node root;
    private int priorityCount;

    public SearchTree() {
        root = null;
        priorityCount = 1; // Initially we take priorityCount as 1
    }

    // Right Rotation
    private Node rotateToright(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    // Left Rotation
    private Node rotateToleft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Add method with priorityCount
    public boolean add(String key) {
        if (key == null) return false; // if it is a null, then return false
        root = add(root, key, priorityCount);
        return true;
    }

    private Node add(Node node, String key, int priority) {
        if (node == null) return new Node(key, priority);
        int ValCompare = key.compareTo(node.key);

        if (ValCompare == 0) return node; // if comparator gives  0 then it already exist

        if (ValCompare < 0) {
            node.left = add(node.left, key, priority);
            if (node.left.priority > node.priority) {
                node = rotateToright(node);
            }
        } else {
            node.right = add(node.right, key, priority);
            if (node.right.priority > node.priority) {
                node = rotateToleft(node);
            }
        }
        return node;
    }

    // Modified Find method using priorityCount
    public boolean find(String key) {
        if (key == null) return false;
        priorityCount++; // Increment priorityCount on each search
        return find(root, key.toLowerCase());
    }

    private boolean find(Node node, String key) {
        if (node == null) return false;
        int ValCompare = key.compareTo(node.key);

        if (ValCompare == 0) {
            root = add(root, key, priorityCount); // Re-add the node with updated priorityCount
            return true;
        } else if (ValCompare < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    // Remove method (unchanged)
    public boolean remove(String key) {
        if (key == null) return false;
        if (!find(key)) return false;
        root = remove(root, key.toLowerCase());
        return true;
    }

    private Node remove(Node node, String key) {
        if (node == null) return null;

        int ValCompare = key.compareTo(node.key);
        if (ValCompare < 0) {
            node.left = remove(node.left, key);
        } else if (ValCompare > 0) {
            node.right = remove(node.right, key);
        } else {
            // Node to be removed found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            if (node.left.priority > node.right.priority) {
                node = rotateToright(node);
                node.right = remove(node.right, key);
            } else {
                node = rotateToleft(node);
                node.left = remove(node.left, key);
            }
        }
        return node;
    }
}
