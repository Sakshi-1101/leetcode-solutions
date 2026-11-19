class Solution {
    public int findContentChildren(int[] g, int[] s) {
        //sort the arrays
        Arrays.sort(g);
        Arrays.sort(s);

        int n = g.length;
        int m = s.length;

        int i = 0;
        int j = 0;

        while(j < m && i < n) {
            if(s[j] >= g[i]) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i;
        
    }
}