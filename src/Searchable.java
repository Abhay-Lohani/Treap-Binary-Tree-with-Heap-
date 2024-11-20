import java.util.List;

public interface Searchable {


    boolean add(String key, int heapValue); // Adds a key with a heap value
    boolean build(String[] keys, int[] heapValues); // Builds the structure with arrays of keys and heap values
    boolean remove(String key); // Removes the specified key
    boolean changeOrder(String key, int newHeapValue); // Changes the heap value (order) of the specified key

   List<String> findPath(String key);//I imported the class for list here

    int size();
}
