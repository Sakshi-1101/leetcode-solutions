
import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class FindIntersectionOfTwoLL {

    public static void main(String[] args) {

        Node head1 = new Node(1, null);
        Node n1 = new Node(3, null);
        Node n2 = new Node(1, null);
        Node n3 = new Node(2, null);
        Node n4 = new Node(4, null);

        head1.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;


        Node head2 = new Node(3, null);
        Node n5 = new Node(4, null);

        head2.next = n3;
        n3.next = n5;

        Node headBrute = intersectBrute(head1, head2);
        Node headBetter = intersectBetter(head1, head2);
        Node headOptimal1 = intersectOptimal1(head1, head2);
        Node headOptimal2 = intersectOptimal2(head1, head2);

        System.out.println(headBrute.data);
        System.out.println(headBetter.data);
        System.out.println(headOptimal1.data);
        System.out.println(headOptimal2.data);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }
    
    // TC: O(n * m)
    // SC: O(1)
    // Approach: In this approach, we use two nested loops to compare each node of the first linked list with every node of 
    //           the second linked list. If we find a match (i.e., the same node reference), we return that node as the 
    //           intersection point. If we exhaust all nodes in the first list without finding a match, we return null, 
    //           indicating no intersection.
    public static Node intersectBrute(Node head1, Node head2) {
        Node temp1 = head1;

        // For each node in list1, check if it exists in list2. If a match is found, return the intersecting node. If no match 
        // is found after checking all nodes in list1, return null.
        while(temp1 != null) {
            Node temp2 = head2;

            while(temp2 != null) {
                if(temp1 == temp2) {
                    return temp1;
                }

                temp2 = temp2.next;
            }

            temp1 = temp1.next;
        }

        // If no intersection is found after checking all nodes in list1,
        return null;

    }

    // TC: O(n + m)
    // SC: O(n)
    // Approach: In this approach, we utilize a HashSet to store the nodes of the first linked list. We traverse the first 
    //           list and add each node to the set. Then, we traverse the second linked list and check if any of its nodes 
    //           exist in the set. If we find a node from the second list in the set, it means we have found the intersection 
    //           point, and we return that node. If no then we'll return null.
    public static Node intersectBetter(Node head1, Node head2) {
        // set to store nodes of list1
        Set<Node> s = new HashSet<>();

        Node temp1 = head1;

        // traverse list1 and add each node to the set. This allows us to keep track of all nodes in list1 for quick lookup.
        while(temp1 != null) {
            s.add(temp1);
            temp1 = temp1.next;
        }

        Node temp2 = head2;

        // traverse list2 and check if any node exists in the set. If a node from list2 is found in the set, it means we have 
        // found the intersection point, and we return that node. 
        while(temp2 != null) {
            // so here instead of data we are comparing the node reference, because intersection means same node 
            // reference not just same value 
            if(s.contains(temp2)) {
                return temp2;
            }

            temp2 = temp2.next;
        }

        // If we finish traversing list2 without finding any node in the set, it means there is no intersection, and we
        return null;
        
    }

    // TC: O(2 × max(N, M)) + O(abs(N − M)) + O(min(N, M))
           /*
                Finding the length of both lists takes O(max) time since it's done simultaneously, then moving one pointer by 
                the difference in lengths, and finally searching for the intersection.
            */
    // SC: O(1)
    // Approach: In this approach, we first calculate the difference in lengths of the two linked lists. We then advance the 
    //           pointer of the longer list by the difference in lengths to align both pointers for the intersection search. 
    //           After aligning, we traverse both lists simultaneously, comparing the nodes. If we find a match (i.e., the 
    //           same node reference), we return that node as intersection point. If we exhaust both lists without finding a 
    //           match, we return null, indicating no intersection. 
    public static Node intersectOptimal1(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        // Calculate the difference in lengths of the two lists. This will help us determine how many nodes to skip in the 
        // longer list to align both pointers for the intersection search.
        int skipNodes = getDifference(temp1, temp2);

        // length of head2 list is greater, skip nodes in head2 list
        if (skipNodes < 0) {
            while(temp2 != null) {
                if(skipNodes == 0) {
                    break;
                }

                skipNodes--;
                temp2 = temp2.next;
            }
        } else { // length of head1 list is greater, skip nodes in head1 list
            while(temp1 != null) {
                if(skipNodes == 0) {
                    break;
                }

                skipNodes--;
                temp1 = temp1.next;
            }
        }

        // After aligning the pointers, we traverse both lists simultaneously. If the pointers point to the same node, 
        // we have found the intersection and return that node. 
        while(temp1 != null) {
            if(temp1 == temp2) {
                return temp1;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return null;  // Return null if no intersection
    }

    // Helper method to calculate the difference in lengths of the two linked lists. It traverses both lists simultaneously,
    // counting the number of nodes in each list. The difference is returned as an integer, where a positive value indicates 
    // that the first list is longer, and a negative value indicates that the second list is longer. 
    private static int getDifference(Node head1, Node head2) {
        int len1 = 0, len2 = 0;
        while (head1 != null || head2 != null) {
            if (head1 != null) {
                len1++;
                head1 = head1.next;
            }
            if (head2 != null) {
                len2++;
                head2 = head2.next;
            }
        }
        return len1 - len2;  // If negative, length of list2 > length of list1, else vice-versa
    }

    // TC: O(2 × max(length of list1, length of list2)) -> Each pointer traverses at most 2 passes and each pass is bounded by the larger list
    // SC: O(1)
    // Approach: In this approach, we use two pointers to traverse both linked lists. We initialize one pointer at the head of 
    //           the first list and another pointer at the head of the second list. We then move both pointers forward one node 
    //           at a time. If a pointer reaches the end of its respective list, we redirect it to the head of the other list. 
    //           This way, both pointers will traverse the same total distance (length of list1 + length of list2) and will 
    //           eventually meet at the intersection point if it exists. If there is no intersection, both pointers will 
    //           eventually become null at the same time, and we can return null to indicate no intersection.
    /*
        NOTE: 
        Two lists:
            A: a → b → c → d → e
            B: x → y → d → e
            They have different lengths, but share a tail.
            The challenge: Align both pointers so they reach the intersection at the same time.
        
        This trick removes length calculation done in above solution:
        Instead of: “Let me calculate lengths” It says: “Let both pointers travel equal total distance somehow”

        Force both pointers to travel: Length(A) + Length(B)
        Meaning:
            When d1 finishes list A → send it to list B
            When d2 finishes list B → send it to list A
        
        Pointer d1 travels: A + B  Pointer d2 travels: B + A
        Total distance becomes equal!

        Why they meet at intersection?
        Let:
            Length A = a + c
            Length B = b + c
            c = common part
        Then:
        After switching:
            d1 travels: a + c + b
            d2 travels: b + c + a
        Both reach the start of c at same time.

        This comes from a pattern: “If two pointers need alignment, make their paths equal.”

        This solution works because it equalizes the total distance traveled by both pointers without explicitly computing lengths.
     */
    public static Node intersectOptimal2(Node head1, Node head2) {
        // Initialize two pointers to the heads of the two linked lists. These pointers will traverse the lists to find the intersection.
        Node temp1 = head1;
        Node temp2 = head2;

        // loop stops if at any point (temp1 == temp2) or (temp1 = temp2 = null)
        while(temp1 != temp2) {
            temp1 = (temp1 == null) ? head2 : temp1.next;
            temp2 = (temp2 == null) ? head1 : temp2.next;
        }

        // if no intersection temp1 will be null otherwise value in temp1 will be intersection
        return temp1; // OR return temp2
    }

}
