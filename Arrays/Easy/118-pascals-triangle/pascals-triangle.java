class Solution {
    // TC: O(N^2)
    // SC: O(N^2)
    // Approach: In this approach we will build each row using nCr formula but this time we will optimize it further by calculating nCr using previous element of the same row
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        // traversing rows from 0 to numRows-1
        for(int i = 0 ; i < numRows ; i ++)
        {
            List<Integer> li = new ArrayList<>();
            
            // first element of every row is 1
            int icj = 1 ;
            
            // building the row using nCr formula
            for(int j = 0 ; j <= i ; j ++)
            {
                // adding the current element to the row
                li.add(icj);
                
                // explanation of this formula: C(n, k+1) = C(n, k) * (n - k) / (k + 1)
                /*
                    icj holds C(i, j) for the current row index i and column j. Start with icj = 1 (C(i, 0) = 1), 
                    add it to the list, then compute the next element using the formula above.
                */
                int icjp1 = icj * (i - j) / (j  + 1); 
                icj = icjp1; // updating icj to icjp1 for next iteration
            }
            
            ans.add(li);
        
        }
        
        return ans ;
    }
}