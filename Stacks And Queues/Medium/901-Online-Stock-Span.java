class StockSpanner {

    // Stores the price along with the day on which it occurred
    class Pair {
        int day;
        int price;

        Pair(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }

    // Monotonic decreasing stack.
    // The stack stores prices that can potentially be
    // the previous greater price for future days.
    Stack<Pair> st;

    // Keeps track of the current day/index.
    // First stock price comes on day 0.
    int currentDay;

    public StockSpanner() {
        st = new Stack<>();
        currentDay = 0;
    }

    public int next(int price) {

        // Remove all previous prices that are <= today's price.
        // They cannot be the previous greater price for today
        // or for any future price that is >= today's price.
        while (!st.isEmpty() && st.peek().price <= price) {
            st.pop();
        }

        int span;

        // No previous greater price exists.
        // Therefore, today's price is greater than or equal to
        // all previous prices, so the span includes all days.
        if (st.isEmpty()) {
            span = currentDay + 1;
        } 
        // The top of the stack is the nearest previous greater price.
        // All days from that price's next day through today
        // are part of today's span.
        else {
            span = currentDay - st.peek().day;
        }

        // Store today's price and its day for future calls.
        st.push(new Pair(currentDay, price));

        // Move to the next day.
        currentDay++;

        return span;
    }
}