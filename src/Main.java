public class Main {
    public static void main(String[] args) {
        Treap treap = new Treap();

        // Using add method to insert individual elements
        System.out.println("Adding individual elements:");
        System.out.println("Add 'Apple': " + treap.add("Apple", 5)); // true
        System.out.println("Add 'Banana': " + treap.add("Banana", 8)); // true
       // System.out.println("Add 'fox': " + treap.add("fox", -3));
        System.out.println("Add 'apple': " + treap.add("apple", 10)); // false (case-insensitive)
        System.out.println("Add 'Orange': " + treap.add("Orange", 3)); // true
        System.out.println("Add '': " + treap.add("", 7)); // true (empty string allowed)
        System.out.println("Add null: " + treap.add(null, 6)); // false (null not allowed)
        System.out.println("Add 'Mango': " + treap.add("Mango", 2)); // true
        System.out.println("Add 'Peach': " + treap.add("Peach", 9)); // true
        System.out.println("Add 'grape': " + treap.add("grape", 4)); // true
        System.out.println("Add 'Grape' (case-insensitive): " + treap.add("Grape", 5)); // false
        System.out.println("Add 'Watermelon': " + treap.add("Watermelon", 6)); // true
        System.out.println("Add 'Pineapple': " + treap.add("Pineapple", 11)); // true
        System.out.println("Add 'Lemon': " + treap.add("Lemon", 10)); // true
        System.out.println("Add 'Strawberry': " + treap.add("Strawberry", 12)); // true
        System.out.println("Add 'Blueberry': " + treap.add("Blueberry", 13)); // true
        System.out.println("Add 'Cherry': " + treap.add("Cherry", 14)); // true
        // Test the size method
        System.out.println("\nTreap size after additions: " + treap.size()); // should return 15

        // Test changeOrder method
        System.out.println("\nChanging heap order:");
        System.out.println("Change order of 'Apple' to 12: " + treap.changeOrder("Apple", 12)); // true
        System.out.println("Change order of 'banana' to 15: " + treap.changeOrder("banana", 15)); // true
        System.out.println("Change order of 'grape' to 9: " + treap.changeOrder("grape", 9)); // true

        // Building the Treap with an array of keys and heap values
        String[] keys = {"lion", "cat", "dog", "zebra", "apple", "kiwi", "melon", "pear", "plum"};
        int[] heapValues = {40, 50, 30, 20, 70, 60, 25, 35, 45};
        boolean isBuilt = treap.build(keys, heapValues);
        System.out.println("Treap built: " + isBuilt); // true
        // Test the size method after building the Treap
        System.out.println("\nTreap size after build: " + treap.size()); // should return updated size

        // Remove elements from the Treap
        System.out.println("\nRemoving elements:");
        System.out.println("Remove 'Banana': " + treap.remove("Banana")); // true
        System.out.println("Remove 'apple': " + treap.remove("apple")); // false (not in the Treap)
        System.out.println("Remove 'cat': " + treap.remove("cat")); // true
        System.out.println("Remove 'Lion': " + treap.remove("lion")); // true (case-insensitive)
        System.out.println("Remove '': " + treap.remove("")); // false (empty string was not added)
        System.out.println("Remove null: " + treap.remove(null)); // false (null not allowed)

        // Test the size method after removals
        System.out.println("\nTreap size after removals: " + treap.size()); // should return the updated number of elements

        ///////////////////////////////////////////////////////////////////below are test cases for SearchTree java

        SearchTree searchTree = new SearchTree();

        // Test adding keys
        System.out.println("Adding keys:");
        System.out.println("Add 'apple': " + searchTree.add("apple"));   // Should return true
        System.out.println("Add 'banana': " + searchTree.add("banana")); // Should return true
        System.out.println("Add 'orange': " + searchTree.add("orange")); // Should return true
        System.out.println("Add 'apple' again: " + searchTree.add("apple")); // Should return false (already exists)

        // Test finding keys
        System.out.println("\nFinding keys:");
        System.out.println("Find 'apple': " + searchTree.find("apple")); // Should return true
        System.out.println("Find 'banana': " + searchTree.find("banana")); // Should return true
        System.out.println("Find 'grape': " + searchTree.find("grape")); // Should return false (not in tree)

        // Test removing keys
        System.out.println("\nRemoving keys:");
        System.out.println("Remove 'orange': " + searchTree.remove("orange")); // Should return true
        System.out.println("Remove 'grape': " + searchTree.remove("grape")); // Should return false (not in tree)
        System.out.println("Remove 'apple': " + searchTree.remove("apple")); // Should return true
        System.out.println("Remove 'apple' again: " + searchTree.remove("apple")); // Should return false (already removed)
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Extended test cases for findPath method for Treap
        System.out.println("\nFinding paths in Treap:");
        System.out.println("Find path for 'lion': " + treap.findPath("lion")); // Should return the path to root
        System.out.println("Find path for 'cat': " + treap.findPath("cat")); // Should return an empty path (since removed)
        System.out.println("Find path for 'dog': " + treap.findPath("dog")); // Should return the path to root
        System.out.println("Find path for 'zebra': " + treap.findPath("zebra")); // Should return the path to root
        System.out.println("Find path for 'grape': " + treap.findPath("grape")); // Should return the path to root
        System.out.println("Find path for 'Lemon': " + treap.findPath("Lemon")); // Should return the path to root
        System.out.println("Find path for 'Strawberry': " + treap.findPath("Strawberry")); // Should return the path to root
        System.out.println("Find path for 'Blueberry': " + treap.findPath("Blueberry")); // Should return the path to root
        System.out.println("Find path for 'Cherry': " + treap.findPath("Cherry")); // Should return the path to root
        System.out.println("Find path for 'Pineapple': " + treap.findPath("Pineapple")); // Should return the path to root
        System.out.println("Find path for 'kiwi': " + treap.findPath("kiwi")); // Should return the path to root
        System.out.println("Find path for 'notInTreap': " + treap.findPath("notInTreap")); // Should return an empty list or null
        System.out.println("Find path for empty string: " + treap.findPath("")); // Should return the path (if empty string was added)
        System.out.println("Find path for null: " + treap.findPath(null)); // Should return an empty list or null
        System.out.println("Find path for 'melon': " + treap.findPath("melon")); // Should return the path to root
    }
}

