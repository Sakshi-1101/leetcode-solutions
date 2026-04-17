1/*
2// Definition for a Node.
3class Node {
4    int val;
5    Node next;
6    Node random;
7
8    public Node(int val) {
9        this.val = val;
10        this.next = null;
11        this.random = null;
12    }
13}
14*/
15
16class Solution {
17    public Node copyRandomList(Node head) {
18//base case
19        if(head == null) {
20            return head;
21        }
22
23        //Step 1: Insert new nodes between each pair of adjacent nodes in the original list. 
24        // For eg:, if the original list is A → B → C, it will become A → A' → B → B' → C → C', where A', B', and C' are the 
25        //          new nodes created as copies of A, B, and C respectively.
26        insertNodesInBetween(head);
27        
28        // Step 2: Connect the random pointers for the new nodes. 
29        connectRandomPointers(head);
30
31        // Step 3: Separate the new nodes from the original list by connecting the next pointers of the new nodes to form the 
32        //         new list. Finally, return the head of the new list.
33        return getLLByConnectingNextPointers(head);
34    }
35
36    // helper function to insert new nodes between each pair of adjacent nodes in the original list
37    private static void insertNodesInBetween(Node head) {
38        Node temp = head;
39
40        // traverse the original list and insert new nodes in between
41        while(temp != null) {
42            Node newNode = new Node(temp.val); // create new node with same val as temp node
43
44            // insert the new node between temp and temp.next
45            newNode.next = temp.next;
46            temp.next = newNode;
47            temp = temp.next.next;
48        }
49    }
50
51    // helper function to connect the random pointers for the new nodes
52    private static void connectRandomPointers(Node head) {
53        Node temp = head;
54
55        // traverse the list and connect random pointers for new nodes
56        while(temp != null) {
57            // get the corresponding new node of the curr node of original list
58            Node newNode = temp.next;
59
60            // if the random pointer of the original node is null, then directly set the random pointer of the new node to null. 
61            if(temp.random == null) {
62                newNode.random = null;
63            } else {
64                // connect the random pointer of the new node to the corresponding new node of the original node's random pointer
65                newNode.random = temp.random.next;
66            }
67
68            temp = temp.next.next;
69        }
70    }
71
72    // helper function to separate the new nodes from the original list by connecting the next pointers of the new nodes to 
73    // form the new list and return the head of the new list
74    private static Node getLLByConnectingNextPointers(Node head) {
75        // dummy node to handle edge cases
76        Node dummyNode = new Node(-1);
77        Node temp1 = dummyNode; // pointer to build the new list
78        Node temp2 = head; // pointer to traverse the original list
79
80        // traverse the original list and connect the next pointers of the new nodes to form the new list
81        while(temp2 != null) {
82            // connect the next pointer of the new node to the next node of the curr node of original list
83            temp1.next = temp2.next;
84            temp1 = temp1.next;
85
86            // move the curr node next pointer of original list to the next next node to skip the new node and point to the 
87            // next original node
88            temp2.next = temp2.next.next;
89            temp2 = temp2.next;
90        }
91
92        // return the next of dummy node as the head of the new list
93        return dummyNode.next;
94
95    }
96}