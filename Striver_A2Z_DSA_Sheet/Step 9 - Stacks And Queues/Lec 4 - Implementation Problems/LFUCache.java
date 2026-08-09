
import java.util.HashMap;

// TC: O(N) -> overall
// SC: O(capacity)
// Approach: In this approach, we will use a doubly linked list to maintain the order of nodes from most recently used to least
//           recently used. The head of the list will point to the most recently used node, while the tail will point to the 
//           least recently used node. We will also use a HashMap to store the mapping of keys to their corresponding nodes in 
//           the linked list for O(1) access. Additionally, we will maintain a frequency map to keep track of the frequency of 
//           access for each node, allowing us to implement the LFU (Least Frequently Used) cache eviction policy.
class DLLNode {
    int key;
    int val;
    int freq;
    DLLNode prev;
    DLLNode next;

    public DLLNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1; // keep the initial freq as always 1
    }
}

class DoublyLL {
    DLLNode head; // dummy head
    DLLNode tail; // dummy tail
    int listSize;

    public DoublyLL() {
        this.head = new DLLNode(0, 0);
        this.tail = new DLLNode(0, 0);
        head.next = tail;
        tail.prev = head;
        this.listSize = 0;
    }

    // TC: O(1)
    public void removeNode(DLLNode lfuNode) {
        // we want to connect the prevNode from lruNode to tail node, so get the reference to that node
        DLLNode prevNode = lfuNode.prev;
        DLLNode nextNode = lfuNode.next;

        // make connections
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        listSize--; // decrement the size of the list
    }

    // TC: O(1)
    public void addNodeAtFront(DLLNode newNode) {
         DLLNode temp = head.next; // store the reference to the 1st node after head
        
        // insert newNode before 1st node after head
        newNode.next = temp;
        newNode.prev = head; // connect newNode prev with head

        // connect head with newNode to make it 1st node after head 
        head.next = newNode;
        temp.prev = newNode; // now the temp node (earlier 1st node) has become 2nd node so connect it's prev with newNode(now 1st node)

        listSize++; // increment the size of the list
    }
}

public class LFUCache {

    private int capacity; // total capacity of lfu cache
    private int currSize; // current size of the cache
    private int minFreq; // min freq of entire LFU cache where the LFU node will be present
    private HashMap<Integer, DLLNode> keyNodeMap; //<key, node> is stored in the map -> This is the LFU Cache
    private HashMap<Integer, DoublyLL> freqMap; // <freq, list of nodes with that freq> stores in the map

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
        this.minFreq = 0;
        this.keyNodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        
    }
    
    // get node value by key and then update node frequency as well as relocate the node
    // TC: O(1) -> assuming map operations are taking O(1) otherwise O(logn)
    public int get(int key) {
        // check if key exists in keyNodeMap
        if(keyNodeMap.containsKey(key)) {
            DLLNode node = keyNodeMap.get(key);
            int val = node.val;

            // relocate the node
            updateNode(node);
            return val;
        }

        return -1;
    }
    
    // put a new node in the cache or update the value of an existing node and relocate it
    // TC: O(1) -> assuming map operations are taking O(1) otherwise O(logn)
    public void put(int key, int value) {
        // corner case: check cache capacity initialisation
        if(capacity == 0) {
            return;
        }

        // check if cache already contains the key
        if(keyNodeMap.containsKey(key)) {
            DLLNode node = keyNodeMap.get(key); // get the key
            node.val = value; // update the value

            // since we have accessed this node now, we'll relocate the node
            updateNode(node);
        } else { // if cache doesn't have the key
            currSize++; // increment the count of nodes in cache since we'll be adding a new node

            // if curr capacity > capacity of cache, we need to remove the LFU keyNode before adding new node
            if(currSize > capacity) {
                DoublyLL minFreqList = freqMap.get(minFreq); // get the LFU list
                // remove the last node before tail in the list bcoz that will be the least frequently used node in the minFreqList
                keyNodeMap.remove(minFreqList.tail.prev.key);
                minFreqList.removeNode(minFreqList.tail.prev); // remove that node from minFreqList also
                currSize--; // decrement the LFU cache size by 1 bcoz you have removed an LFU node
            }

            // set the min freq to 1 since it will be a new element as it came in the else block and didn't exist earlier 
            //if it's a new element, reset the minFreq to 1 bcoz then it'll be the minFreq for LFU
            minFreq = 1;
            DLLNode node = new DLLNode(key, value); // create a node for this new element

            // add this new node in the freqMap having freq 1 (if exists other create new list)
            DoublyLL newList = freqMap.getOrDefault(1, new DoublyLL());
            newList.addNodeAtFront(node); // add new node in the list
            freqMap.put(1, newList); // add the list with new node in the freq map
            keyNodeMap.put(key, node); // add the new node in the cache also
        }
        
    }

    // to update the node frequency and relocate the node in the freqMap
    // TC: O(1) -> assuming map operations are taking O(1) otherwise O(logn)
    public void updateNode(DLLNode node) {
        // STEP 1: DELETE THE NODE FROM CURR POSITION
        int currNodeFreq = node.freq; // get the freq of currNode
        DoublyLL currNodeList = freqMap.get(currNodeFreq); // fetch DLL that belong to that currNode freq
        currNodeList.removeNode(node); // remove the curr node from the list

        // STEP 2: CHECK IF MINIMUM FREQUENCY NEEDS TO BE UPDATED
        // if minFreq of LFU Cache belongs to the current list fetched and the node that was just removed was from this list,
        // then this list will now become empty, hence we need to remove this list and increment the minFreq
        if(currNodeFreq == minFreq && currNodeList.listSize == 0) {
            minFreq++;
        }

        // STEP 3: INCREMENT THE FREQUENCY OF THE NODE
        // increment the freq of currNode since this node was just accessed.
        node.freq++;

        // STEP 4: INSERT THE NODE TO THE DLL OF THE INCREMENTED FREQUENCY MAP
        // fetch the DLL for the currNode incremented freq, if no list present then create a new DLL
        DoublyLL newList = freqMap.getOrDefault(node.freq, new DoublyLL());
        newList.addNodeAtFront(node); // insert the node in the new list
        freqMap.put(node.freq, newList); // put the newList along with currNode incremented freq in map

    }
    
    public static void main(String[] args) {
        // LFU Cache
        LFUCache cache = new LFUCache(2);
        
        // Queries
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " ");
        cache.put(3, 3);
        System.out.print(cache.get(2) + " ");
        System.out.print(cache.get(3) + " ");
        cache.put(4, 4);
        System.out.print(cache.get(1) + " ");
        System.out.print(cache.get(3) + " ");
        System.out.print(cache.get(4) + " ");
        
    }
}
