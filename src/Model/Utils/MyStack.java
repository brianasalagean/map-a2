package Model.Utils;

import Exceptions.InterpreterException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<>();
    }

    @Override
    public T pop() throws InterpreterException {
        if (stack.isEmpty())
            throw new InterpreterException("stack error: stack is empty");
        return stack.pop();
    }

    @Override
    public T getTop() throws InterpreterException{
        if (stack.isEmpty())
            throw new InterpreterException("stack error: stack is empty");
        return stack.peek();
    }

    @Override
    public T push(T elem) {
        return stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        if (stack.empty())
            return true;
        return false;
    }

    @Override
    public Stack<T> getStack() {
        return stack;
    }
}
