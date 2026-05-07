1class MyStack {
2    Queue<Integer> q1;
3    Queue<Integer> q2;
4
5    public MyStack() {
6        q1 = new LinkedList<>();
7        q2 = new LinkedList<>();
8        
9    }
10    
11    public void push(int x) {
12        q1.add(x);
13    }
14    
15    public int pop() {
16        if(q1.isEmpty()) {
17            return -1;
18        }
19
20        // pop all the elements from q1 except last added element and put in q2
21        while(q1.size() > 1) {
22            q2.add(q1.poll());
23        }
24
25        // pop the last element of q1
26        int poppedVal = q1.poll();
27
28        // Swap q1 and q2
29        Queue<Integer> temp = q1;
30        q1 = q2;
31        q2 = temp;
32
33        return poppedVal;
34        
35    }
36    
37    public int top() {
38        if(q1.isEmpty()) {
39            return -1;
40        }
41
42        // pop all the elements from q1 except last added element and put in q2
43        while(q1.size() > 1) {
44            q2.add(q1.poll());
45        }
46
47        // pop the last element of q1
48        int poppedVal = q1.peek();
49
50        // Move it to q2 as well
51        q2.add(q1.poll());
52
53        // Swap q1 and q2
54        Queue<Integer> temp = q1;
55        q1 = q2;
56        q2 = temp;
57
58        return poppedVal;
59        
60    }
61    
62    public boolean empty() {
63        return q1.isEmpty();
64    }
65}
66
67/**
68 * Your MyStack object will be instantiated and called as such:
69 * MyStack obj = new MyStack();
70 * obj.push(x);
71 * int param_2 = obj.pop();
72 * int param_3 = obj.top();
73 * boolean param_4 = obj.empty();
74 */