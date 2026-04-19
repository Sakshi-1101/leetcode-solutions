
import java.util.*;

class Node {
    int data;
    Node next;
    Node random;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.random = null;
    }
}

public class CloneLLWithRandomPointer {

     public static void main(String[] args) {

        // Create nodes
        Node head = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);

        // next pointers (linear list)
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // random pointers
        head.random = null;   // 7 → null
        n1.random = head;     // 13 → 7
        n2.random = n4;       // 11 → 1
        n3.random = n2;       // 10 → 11
        n4.random = head;     // 1 → 7


        Node replicaBrute = replicateLLBrute(head);
        Node replicaOptimal = replicateLLOptimal(head);

        printList(replicaBrute);
        printList(replicaOptimal);

    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(N) + O(N) = O(2N) -> Assuming the Hashmap operations are happening in O(1) time complexity
    // SC: O(N) + O(N) = O(2N) -> map space + nodes creation space (this is required, can't solve without this) 
    // Approach: In this approach, we use a HashMap to store the mapping of original nodes to their corresponding new nodes. 
    //           We first create a copy of each node in the original list and store it in the map. Then, we traverse the 
    //           original list again and connect the next and random pointers for each new node according to the original 
    //           list's structure using the map.
    public static Node replicateLLBrute(Node head) {
        // Step1: create new nodes or copy of each node and store it in the map
        Node temp = head; // for traversal on original LL
        HashMap<Node, Node> map = new HashMap<>(); // <Original Node, Copy Node>

        // traverse the original list
        while(temp != null) {
            Node newNode = new Node(temp.data); // create new node
            map.put(temp, newNode); // put nodes in map
            temp = temp.next;
        }

        // Step 2: Connect the next and random pointers for each node acc to original LL
        temp = head; // reset temp to head for traversal

        // traverse the list
        while(temp != null) {
            // get the corresponding node of the curr node of original list
            Node newNode = map.get(temp);

            // make the same connection for the new nodes as per the original list
            newNode.next = map.get(temp.next);
            newNode.random = map.get(temp.random);

            temp = temp.next;
        }

        // return the new node head of the list
        return map.get(head);

    }

    // TC: O(N) + O(N) + O(N) = O(3N)
    // SC: O(N) -> to create new nodes 
    // Approach: In this approach, we first insert new nodes between each pair of adjacent nodes in the original list. Then, 
    //           we connect the random pointers for the new nodes. Finally, we separate the new nodes from the original list 
    //           by connecting the next pointers of the new nodes to form the new list and return the head of the new list.
    public static Node replicateLLOptimal(Node head) {
        //base case
        if(head == null) {
            return head;
        }

        //Step 1: Insert new nodes between each pair of adjacent nodes in the original list. 
        // For eg:, if the original list is A → B → C, it will become A → A' → B → B' → C → C', where A', B', and C' are the 
        //          new nodes created as copies of A, B, and C respectively.
        insertNodesInBetween(head);
        
        // Step 2: Connect the random pointers for the new nodes. 
        connectRandomPointers(head);

        // Step 3: Separate the new nodes from the original list by connecting the next pointers of the new nodes to form the 
        //         new list. Finally, return the head of the new list.
        return getLLByConnectingNextPointers(head);
    }

    // helper function to insert new nodes between each pair of adjacent nodes in the original list
    private static void insertNodesInBetween(Node head) {
        Node temp = head;

        // traverse the original list and insert new nodes in between
        while(temp != null) {
            Node newNode = new Node(temp.data); // create new node with same data as temp node

            // insert the new node between temp and temp.next
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }
    }

    // helper function to connect the random pointers for the new nodes
    private static void connectRandomPointers(Node head) {
        Node temp = head;

        // traverse the list and connect random pointers for new nodes
        while(temp != null) {
            // get the corresponding new node of the curr node of original list
            Node newNode = temp.next;

            // if the random pointer of the original node is null, then directly set the random pointer of the new node to null. 
            if(temp.random == null) {
                newNode.random = null;
            } else {
                // connect the random pointer of the new node to the corresponding new node of the original node's random pointer
                newNode.random = temp.random.next;
            }

            // move two steps to go to next node of original list (skip the new node)
            temp = temp.next.next;
        }
    }

    // helper function to separate the new nodes from the original list by connecting the next pointers of the new nodes to 
    // form the new list and return the head of the new list
    private static Node getLLByConnectingNextPointers(Node head) {
        // dummy node to handle edge cases
        Node dummyNode = new Node(-1);
        Node temp1 = dummyNode; // pointer to build the new list
        Node temp2 = head; // pointer to traverse the original list

        // traverse the original list and connect the next pointers of the new nodes to form the new list
        while(temp2 != null) {
            // connect the next pointer of the new node to the next node of the curr node of original list
            temp1.next = temp2.next;
            temp1 = temp1.next;

            // move the curr node next pointer of original list to the next to next node to skip the new node and point to the 
            // next original node
            temp2.next = temp2.next.next;
            temp2 = temp2.next;
        }

        // return the next of dummy node as the head of the new list
        return dummyNode.next;

    }
    
}
