class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        // Sort the intervals based on the start time
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            // If list is empty OR no overlap → add new interval
            if (ans.isEmpty() || intervals[i][0] > ans.get(ans.size() - 1)[1]) {
                ans.add(intervals[i]);
            } 
            // Else merge with last interval
            else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    // Approach 2:
    public int[][] mergeAlternate(int[][] intervals) {
        List<int[]> li = new ArrayList<>();
        
        if(intervals.length == 0 || intervals == null){
            return li.toArray(new int[0][]);
        }
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for(int i = 0 ; i < intervals.length ; i ++){
            if(intervals[i][0] <= end){ // merge the intervals
                end = Math.max(end, intervals[i][1]);
            }
            else{ // when next value don't overlap first add the previous interval in the list then update start and end to new interval
                li.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        
        //last interval needs to be added
        li.add(new int[]{start, end});
        
        return li.toArray(new int[0][]);
    }
}