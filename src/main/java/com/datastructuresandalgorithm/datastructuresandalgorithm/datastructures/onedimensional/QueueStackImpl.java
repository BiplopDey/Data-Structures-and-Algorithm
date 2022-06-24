package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

public class QueueStackImpl implements Queue{
    Stack stack  = new StackArrayImpl(5);
    Stack  reversedStack = new StackArrayImpl(5);

    @Override
    public void enqueue(int data) {
        stack.push(data);
    }

    //O(n)
    @Override
    public int dequeue() { // reversedStack only if empty
        if(isEmpty())
            throw new IllegalStateException();

        if(reversedStack.isEmpty())
            reverseStack();

        return reversedStack.pop();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty() && reversedStack.isEmpty();
    }

    private void reverseStack(){
        while(!stack.isEmpty())
            reversedStack.push(stack.pop());
    }


}
