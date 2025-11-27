import java.util.*;

public class PascalsTriangle {

    public static void main(String[] args) {
        int n = 5;
        int row = 5;
        int col = 3;

        int[][] arr = new int[n + 1][n + 1];

        // This approach will basically return the value present at (row,col) index in pascal's triangle
        int posBrute = printBruteMyApproach(arr, n, row, col);
        long posBetter = printBetter(row, col);

        System.out.println(posBrute);
        System.out.println(posBetter);

        // print the nth row of pascal's triangle
        printNthRowBrute(n);
        printNthRowBetter(n);

        // print pascal triangles with n rows
        List<List<Integer>> ansBrute = pascalTriangleBrute(n);
        List<List<Integer>> ansBetter = pascalTriangleBetter(n);
        List<List<Integer>> ansApproach2 = pascalTriangleBetterApproach2(n);

        for(List<Integer> list : ansBrute) {
            for(int ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        
        for(List<Integer> list : ansBetter) {
            for(int ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }

        for(List<Integer> list : ansApproach2) {
            for(int ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }

    }


    /* ################################################## */
    /*                    VARIATON 1                      */
    /* ################################################## */


    // TC: O(N^2)
    // SC: O(N^2)
    // Approach: Generate the entire pascal's triangle and then retun the value at (r,c) position.
    public static int printBruteMyApproach(int[][] arr, int n, int row, int col) {

        for(int i = 1 ; i <= n ; i ++) {
            for(int j = 1 ; j <= i ; j ++) {
                if (j == i || j == 1) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
        }

        return arr[row][col];

        // NOTE: If declare arr[n][n] instead of arr[n+1][n+1] then the code changes will be as below
        /* 
            for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
        }

        // Convert 1-based row/col to 0-based indices
        return arr[row - 1][col - 1];

        */
    }

    // TC: O(r)
    // SC: O(1)
    // Approach: Using nCr formula to calculate the value at (r,c) position in pascal's triangle
    public static long printBetter(int r, int c) {
        // Element is C(r-1, c-1)
        int n = r - 1;
        int k = c - 1;

        long res = 1; // this will store the value present at (r,c) position in pascal's triangle

        // calculating nCk = n! / (k! * (n-k)!)
        for(int i = 0 ; i < k ; i ++) {
            res = res * (n - i);
            res = res / (i + 1);
        }

        return res;

    }

    /* ################################################## */
    /*                    VARIATON 2                      */
    /* ################################################## */

    // TC: O(n * r) -> O(n) is for traversal and O(r) is for calculating nCr
    // SC: O(1)
    // Approach: Using nCr formula to print the nth row of Pascal's triangle
    public static void printNthRowBrute(int n) {

        // print nCr for nth row where r = n-1 and c varies from 1 to n
        for(int c = 1 ; c <= n ; c ++) {
            // passing (n,c) bcoz printBetter expects 1-based row/col and internally computes C(r-1, c-1).
            System.out.println(printBetter(n, c) + " ");

            /*
                NOTE: if the func printBetter was calculating nCr instead of (n-1)C(r-1) then we would have made the following call:
                      System.out.println(printBetter(n - 1, c - 1) + " ");
            */
        }
    }

    // TC: O(n)
    // SC: O(1)
    // Approach: Using the property that each element in nth row can be calculated using the previous element
    //           After analysing pattern, formula: ans * ((row - col) / col)
    public static void printNthRowBetter(int n) {
        int ans = 1; // the first element of every nth row in pascal's triangle is 1

        System.out.println(ans);

        // traversing the nth row as per the 0 based indexing
        for(int c = 1 ; c < n ; c ++) {
            ans = ans * (n - c); // n = nth row and c = column
            ans = ans / c;

            System.out.println(ans);
        }
    }

    /* ################################################## */
    /*                    VARIATON 3                      */
    /* ################################################## */


    // TC: O(N^2 * R) ~ O(N^3)
    // SC: O(N^2)
    // Approach: Generating the entire pascal's triangle with n rows using nCr formula
    public static List<List<Integer>> pascalTriangleBrute(int n) {
        List<List<Integer>> ans = new ArrayList<>();

        // traversing rows from 1 to n
        for(int row = 1 ; row <= n ; row++) {
            List<Integer> rowList = new ArrayList<>();

            // col will go till row number bcoz each nth row has n elements
            for(int col = 1 ; col <= row ; col++) {
                long ele = printBetter(row, col); // getting the value at (row,col) position in pascal's triangle
                rowList.add((int)ele);
            }

            ans.add(rowList);
        }

        return ans;

    }

    // TC: O(N^2)
    // SC: O(N^2)
    // Approach: Generating the triangle using the property that each element in nth row can be calculated using the formula: ans * ((row - col) / col)
    public static List<List<Integer>> pascalTriangleBetter(int n) {
        List<List<Integer>> res = new ArrayList<>();

        // traversing rows from 1 to n
        for(int row = 1 ; row <= n ; row++) {
            List<Integer> rowList = new ArrayList<>();

            // first element of every row is 1
            int ans = 1;
            rowList.add(ans);
            
            // building the row using the property that each element in nth row can be calculated using the formula
            for(int c = 1 ; c < row ; c ++) {
                ans = ans * (row - c); // c = column
                ans = ans / c;

                rowList.add(ans);

            }

            res.add(rowList);
        }

        return res;
    }

    // TC: O(N^2)
    // SC: O(N^2)
    // Approach: Building each row using the previous row
    public static List<List<Integer>> pascalTriangleBetterApproach2(int n) {
        List<List<Integer>> res = new ArrayList<>();

        // traversing rows from 0 to n-1
        for(int i = 0 ; i < n ; i ++) {
            List<Integer> rowList = new ArrayList<>();
            rowList.add(1); // first element of every row is 1

            // building the row using previous row
            for(int j = 1 ; j < i ; j ++) {
                int val = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j); // current value = sum of two values from previous row
                rowList.add(val);
            }

            // last element of every row is 1 but we need to add it only if i >= 1 bcoz for 0th row there is only one element
            if(i >= 1) { 
                rowList.add(1);
            }

            res.add(rowList);
        }

        return res;

    }

}
