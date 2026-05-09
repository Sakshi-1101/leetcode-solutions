1class MinStack {
2    Stack<Long> s; // Long used everywhere to avoid integer overflow
3    long min;
4
5        public MinStack() {
6        s = new Stack<>();
7        min = Long.MAX_VALUE;
8    }
9    
10    public void push(int val) {
11        long ele = val;
12        // pushing first element
13        if(s.isEmpty()) {
14            s.push(ele);
15            min = ele;
16            return;
17        }
18
19        if(ele > min) {
20            s.push(ele);
21        } else { // val < min -> insert the modified val in stack then update min
22            long modifiedVal = 2 * ele - min; // min = curr min
23            s.push(modifiedVal);
24            min = val;
25        }
26
27    }
28    
29    public void pop() {
30        if(s.isEmpty()) {
31            return;
32        }
33
34        // get the top of stack
35        long top = s.peek();
36
37        s.pop(); // pop the value from stack
38
39        // update min if modified value is present at top of stack otherwise don't do anything since you already popped the 
40        // ele which will be orignal ele in case this condition doesn't run
41        if(top < min) {
42            long prevMin = 2 * min - top; // get the prev min
43            min = prevMin;
44        }
45        
46    }
47    
48    public int top() {
49        if(s.isEmpty()) {
50            return -1;
51        }
52
53        // if modified value present at top of stack, the min value will have the actual top of stack value
54        if(s.peek() < min) {
55            return (int)min;
56        }
57
58        // if s.peek() > min, means original value is there in top of stack return that
59        long topValue = s.peek();
60        return (int)topValue; 
61        
62    }
63    
64    public int getMin() {
65        return (int)min;
66    }
67}
68
69/**
70 * Your MinStack object will be instantiated and called as such:
71 * MinStack obj = new MinStack();
72 * obj.push(val);
73 * obj.pop();
74 * int param_3 = obj.top();
75 * int param_4 = obj.getMin();
76 */