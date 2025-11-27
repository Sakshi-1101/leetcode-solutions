class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        // traversing rows from 1 to n
        for(int row = 1 ; row <= numRows ; row++) {
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
}