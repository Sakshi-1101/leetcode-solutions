import java.util.*;

class Node {
    int data;
    Node next;
    Node child;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.child = null;
    }

    Node(int data, Node next, Node child) {
        this.data = data;
        this.next = next;
        this.child = child;
    }
}

public class FlattenLL {

    public static void main(String[] args) {

        Node head = new Node(3, null, null);
        Node n1 = new Node(2, null, null);
        Node n2 = new Node(1, null, null);
        Node n3 = new Node(4, null, null);
        Node n4 = new Node(5, null, null);

        // next pointers
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // child of 2 → 10
        Node c1 = new Node(10, null, null);
        n1.child = c1;

        // child of 1 → 7 → 11 → 12
        Node c2 = new Node(7, null, null);
        Node c3 = new Node(11, null, null);
        Node c4 = new Node(12, null, null);

        n2.child = c2;
        c2.child = c3;
        c3.child = c4;

        // child of 4 → 9
        Node c5 = new Node(9, null, null);
        n3.child = c5;

        // child of 5 → 6 → 8
        Node c6 = new Node(6, null, null);
        Node c7 = new Node(8, null, null);

        n4.child = c6;
        c6.child = c7;


        Node flatBrute = flatLLBrute(head);
        Node flatOptimal = flatLLOptimal(head);

        printList(flatBrute);
        printList(flatOptimal);

    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.child;
        }

        System.out.println();
    }

    // TC: O(N x M) + O(N x M log(N x M)) + O(N x M)
           /*
                EXPLANATION:
                1. O(N x M) because we traverse through all the nodes, iterating through N nodes along the next pointers and 
                            M nodes along the child pointers.
                2. O(N x M log(N x M)) because we sort the array containing N x M total elements.
                3. O(N x M) because we reconstruct the linked list from the sorted array by iterating over the N x M elements.
            */
    // SC: O(N x M) + O(N x M)
           /*
                EXPLANATION:
                1. O(N x M) for storing all the elements in an additional array for sorting.
                2. O(N x M) to reconstruct the linked list from the array after sorting.
            */
    // Approach: In this approach, we first traverse the LL and store all the node values in an arraylist. Then we sort the 
    //           arraylist and finally convert the sorted arraylist back to a LL and return the head of the flattened LL.
    public static Node flatLLBrute(Node head) {
        // arraylist to store values of all the nodes
        ArrayList<Integer> al = new ArrayList<>();

        Node temp1 = head;

        // traverse the horizontal nodes
        while(temp1 != null) {
            
            Node temp2 = temp1;

            // traverse the vertical nodes of each node
            while(temp2 != null) {
                // add the node values to the arraylist
                al.add(temp2.data);
                temp2 = temp2.child;
            }

            temp1 = temp1.next;
        }

        // sort the arraylist
        Collections.sort(al);

        // convert the arraylist to LL and return updated head 
        Node flatHead = convertALtoLL(al);
        return flatHead;
    }

    // helper function to convert Arraylist to vertical LL (using child pointer)
    private static Node convertALtoLL(ArrayList<Integer> al) {
        // create a dummy node to handle the edge case of an empty list and to simplify the logic of adding nodes to the new LL
        Node dummyNode = new Node(-1, null, null);
        Node temp = dummyNode;

        // iterate through the sorted arraylist and create new nodes for each element, linking them together using the 
        // child pointer
        for(Integer ele: al) {
            Node node = new Node(ele);
            /* 
                NOTE: If we wanted to make flattened horizontal LL, then we'll use next pointer
                Instead of:
                    1. temp.child = node; => temp.next = node;
                    2. return dummyNode.child => return dummyNode.next; 
             */
            temp.child = node;
            temp = node;
        }

        // return the next of dummy node as the head of the flattened linked list
        return dummyNode.child;
    }
    
    // TC: O(2 * N * M) -> recursion depth of N and 2M for merging two vertical lists (Assuming both the lists have M nodes each)
    // SC: O(N) -> recursive stack space
    // Approach: In this approach, we use recursion to flatten the linked list. We first recursively flatten the next node's 
    //           child lists and then merge the current node's child list with the already flattened next node's child list 
    //           using a helper function that merges two sorted linked lists (using child pointers) and returns the head of 
    //           the merged vertical list.
    public static Node flatLLOptimal(Node head) {
        //base case: if the head is null or there is only one node in the list
        if(head == null || head.next == null) {
            return head;
        }

        // call recursion on the next node child lists to flatten them first
        Node mergedHead = flatLLOptimal(head.next);
        
        // merge the current node's child list with the already flattened next node's child list and return the head of the 
        // merged list
        return mergeSortedLL(head, mergedHead);
    } 

    // helper function to merge two sorted linked lists (using child pointers) and return the head of the merged vertical list
    /*
        NOTE: this is a vertical-only list, no horizontal links
    */
    private static Node mergeSortedLL(Node head1, Node head2) {
        //dummy node to handle edge cases
        Node dummyNode = new Node(-1);
        Node temp = dummyNode; // temp pointer to build the merged list

        Node temp1 = head1;
        Node temp2 = head2;

        // merge the two sorted linked lists by comparing their node values and linking the smaller value node to the 
        // temp pointer and moving the temp pointer and the pointer of the list from which the node was taken forward
        while(temp1 != null && temp2 != null) {
            if(temp1.data <= temp2.data) {
                temp.child = temp1;
                temp = temp1;
                temp1 = temp1.child;
            } else {
                temp.child = temp2;
                temp = temp2;
                temp2 = temp2.child;
            }

            // set the next pointer of the temp node to null to ensure that the merged list is a vertical list 
            // using child pointers only
            temp.next = null;
        }

        // if any nodes remaining in either list, link them to the end of the merged list
        if(temp1 != null) {
            temp.child = temp1;
        }
        if(temp2 != null) {
            temp.child = temp2;
        }

        // set the next pointer of the dummy node to null to ensure that the merged list is a vertical list
        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        // return the next of dummy node as the head of the merged linked list
        return dummyNode.child;
    }
}
