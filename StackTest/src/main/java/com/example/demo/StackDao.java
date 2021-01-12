package com.example.demo;

public class StackDao {

    private int[] stackElements;
    private int capacity;
    int top;

    StackDao() {

    }

    StackDao(int capacity) {
        this.stackElements = new int[capacity];
        this.capacity = capacity;
        top = -1;
    }

    public boolean push(int item) {
        if (isFull()) {
            return true;
        }
        stackElements[++top] = item;
        return false;
    }

    public String pop() {
        if (isEmpty()) {
            return "Empty Stack";
        }
        return String.valueOf(stackElements[top--]);
    }

    public String getStackElements() {
        return this.toString();
    }

    public String peek() {
        if (!isEmpty())
            return String.valueOf(stackElements[top]);
        else
            return "Stack is empty";
    }

    public int size() {
        return top + 1;
    }

    public Boolean isFull() {
        return top == capacity - 1 || size() == capacity;
    }

    public Boolean isEmpty() {
        return top == -1 || size() == 0;
    }

    // Print stack contains
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        if(!isEmpty()) {
            result.append("[");
            for (int i = top; i >= 0; i--) {
                result.append(stackElements[i]).append(" ");
            }
            result.append("]");
        } else {
            result.append("Stack is empty");
        }

        return result.toString();
    }

}
