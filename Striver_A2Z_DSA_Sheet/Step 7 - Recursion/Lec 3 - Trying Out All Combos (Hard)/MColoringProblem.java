import java.util.Arrays;

public class MColoringProblem {

    public static void main(String[] args) {
        int n = 4; // no. of nodes
        int m = 3; // no. of colors

        // create a graph
        boolean[][] graph = new boolean[101][101];
        graph[0][1] = graph[1][0] = true;
        graph[1][2] = graph[2][1] = true;
        graph[2][3] = graph[3][2] = true;
        graph[3][0] = graph[0][3] = true;
        graph[0][2] = graph[2][0] = true;

        // create a color aray to store curr color of the node
        int[] colours = new int[n];
        Arrays.fill(colours, 0); // 0 denotes no color

        boolean ans = colorNodes(graph, 0, n, m, colours);

        System.out.println(ans);
    }
    

    // TC: O(N^m) -> for each n node we try m colors
    // SC: O(N) + O(N) -> N for recursion stack space + N for color array
    // Approach: In this approach, we will try to color each node with every possible color and check if it's safe to color the 
    //           node with that color. If it's safe then we will call recursion to color the next node. If we are able to color 
    //           all nodes then we return true else we backtrack and try other colors for the current node.
    public static boolean colorNodes(boolean[][] graph, int node, int n, int m, int[] colours) {
        // base case: curr node is equal to n
        if(node == n) {
            return true; // means we were able to color all nodes
        }

        // try coloring node with every possible color
        for(int color = 1 ; color <= m ; color ++) {
            // if adjacent nodes don't have same color, assign this color to curr node
            if(ifPossible(graph, node, n, color, colours)) {
                // assign color to curr node
                colours[node] = color;

                // call recursion on the next node to assign the appropriate color
                if(colorNodes(graph, node + 1, n, m, colours)) {
                    return true; // if this call returns true means it was able to assign color to rest of nodes
                }

                // remove color from the last colored node while backtracking
                colours[node] = 0;
            }
        }

        // it's not possible to color the node with any of the colors from 1 to m
        return false;
    }

    // helper function to check if any adjacent nodes have the same color then we can't assign that color to curr node
    public static boolean ifPossible(boolean[][] graph, int node, int n, int color, int[] colours) {
        // Loop through all nodes in the graph
        for (int k = 0; k < n; k++) {

            // Skip checking the node itself
            if (k != node) {

                // graph[k][node] == true means there is an edge between node and k (k is a neighbor of node)
                // color[k] == col means adjacent node k already has the same color we are trying to assign

                // If both conditions are true, coloring is NOT safe
                if (graph[k][node] == true && colours[k] == color) {
                    return false;
                }
            }
        }

        // If no adjacent node has the same color, it is safe to color this node
        return true;

    }
}
