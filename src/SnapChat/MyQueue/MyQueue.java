class MyQueue {
    Stack<Integer> stIn = new Stack<>();
    Stack<Integer> stOut = new Stack<>();
    // Push element x to the back of queue.
    public void push(int x) {
        stIn.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(stOut.isEmpty()) {
            while(!stIn.isEmpty()) {
                stOut.push(stIn.pop());
            }
        }
        stOut.pop();
    }

    // Get the front element.
    public int peek() {
        if(stOut.isEmpty()){
            while(!stIn.isEmpty()) {
                stOut.push(stIn.pop());
            }
        }
        return stOut.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stIn.isEmpty() && stOut.isEmpty();
    }
}