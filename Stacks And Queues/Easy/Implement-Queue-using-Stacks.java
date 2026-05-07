1class MyQueue {
2    Stack<Integer> st1;
3    Stack<Integer> st2;
4
5    public MyQueue() {
6        st1 = new Stack<>();
7        st2 = new Stack<>();
8    }
9
10    public void push(int x) {
11        st1.add(x);
12    }
13
14    public int pop() {
15        if (st1.isEmpty()) {
16            return -1;
17        }
18
19        while (st1.size() > 1) {
20            st2.add(st1.pop());
21        }
22
23        int val = st1.pop();
24
25        while (st2.size() > 0) {
26            st1.add(st2.pop());
27        }
28
29        return val;
30    }
31
32    public int peek() {
33        if (st1.isEmpty()) {
34            return -1;
35        }
36
37        while (st1.size() > 1) {
38            st2.add(st1.pop());
39        }
40
41        int val = st1.peek();
42
43        while (st2.size() > 0) {
44            st1.add(st2.pop());
45        }
46
47        return val;
48    }
49
50    public boolean empty() {
51        return st1.isEmpty();
52    }
53}
54
55/**
56 * Your MyQueue object will be instantiated and called as such:
57 * MyQueue obj = new MyQueue();
58 * obj.push(x);
59 * int param_2 = obj.pop();
60 * int param_3 = obj.peek();
61 * boolean param_4 = obj.empty();
62 */