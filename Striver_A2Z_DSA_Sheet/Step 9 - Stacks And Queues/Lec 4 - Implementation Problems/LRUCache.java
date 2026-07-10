
import java.util.HashMap;

// TC: O(1) -> overall
// SC: O(capacity)
// Approach: In this approach, we will use a doubly linked list to maintain the order of nodes from most recently used to least 
//           recently used. The head of the list will point to the most recently used node, while the tail will point to the least 
//           recently used node. We will also use a HashMap to store the mapping of keys to their corresponding nodes in the 
//           linked list for O(1) access.
public class LRUCache {

    public static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> map = new HashMap<>(); // to store <key, node address>
    int capacity; // max nodes the cache can have

    // dummy nodes
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    // TC: O(1) -> assuming map operations are taking O(1) otherwise O(logn)
    public int get(int key) {
        // if map doesn't contain the key
        if(!map.containsKey(key)) {
            return -1;
        }

        // if key present get the node
        Node keyNode = map.get(key);
        // get the val from node
        int val = keyNode.val;

        /* now since we have accessed this node, we need to put in front since it's most recently used & we maintain LRU cache 
           where we have nodes from most to least recently used
        */
        // Step 1: delete the node from the cache and map
        deleteNode(keyNode);
        map.remove(key);

        // Step 2: insert the node at the front after headNode and add it to the map
        insertNodeAfterHead(keyNode); // reuse same node, don't create new node
        map.put(key, head.next); // since we remove the keyNode node, we need to add it again with new node address (i.e. 1st node after head)


        return val;
    }
    
    // TC: O(1) -> assuming map operations are taking O(1) otherwise O(logn)
    public void put(int key, int value) {
        // if map contains the key, update the value
        if(map.containsKey(key)) {
            // get the node of the key
            Node keyNode = map.get(key);
            keyNode.val = value; // update with new value;

            // now that node is most recently used, so we need to delete and put the node in front
            deleteNode(keyNode);
            insertNodeAfterHead(keyNode);
        } else { // if node not in the map
            // if cache size is equal to capacity of cache
            if(map.size() == capacity) {
                // delete the least recently used node (last node before dummy tail node)
                Node lruNode = tail.prev;
                map.remove(lruNode.key); // remove the occurrence of this LRU <key, node> from map also
                deleteNode(lruNode);
            }

            // insert a new node at the front and add it to the map also
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertNodeAfterHead(newNode);
        }
    }

    // TC: O(1)
    private void deleteNode(Node lruNode) {
        // we want to connect the prevNode from lruNode to tail node, so get the reference to that node
        Node prevNode = lruNode.prev;
        Node tailNode = lruNode.next;

        // make connections
        prevNode.next = tailNode;
        tailNode.prev = prevNode;
    }

    // TC: O(1)
    private void insertNodeAfterHead(Node newNode) {
        Node temp = head.next; // store the reference to the 1st node after head
        
        // insert newNode before 1st node after head
        newNode.next = temp;
        newNode.prev = head; // connect newNode prev with head

        // connect head with newNode to make it 1st node after head 
        head.next = newNode;
        temp.prev = newNode; // now the temp node (earlier 1st node) has become 2nd node so connect it's prev with newNode(now 1st node)
        
    }

    public static void main(String[] args) {
        // Create cache with capacity 2
        LRUCache cache = new LRUCache(2);

        // Put values in cache
        cache.put(1, 1);
        cache.put(2, 2);

        // Get value for key 1
        System.out.println(cache.get(1));

        // Insert another key (evicts key 2)
        cache.put(3, 3);

        // Key 2 should be evicted
        System.out.println(cache.get(2));

        // Insert another key (evicts key 1)
        cache.put(4, 4);

        // Key 1 should be evicted
        System.out.println(cache.get(1));

        // Key 3 should be present
        System.out.println(cache.get(3));

        // Key 4 should be present
        System.out.println(cache.get(4));
    }
    


}
