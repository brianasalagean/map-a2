package Model.Utils;

import Exceptions.InterpreterException;

import java.util.Stack;

public interface MyIStack<T> {
    T pop() throws InterpreterException;
    T getTop() throws InterpreterException;
    T push(T elem);
    boolean isEmpty();
    Stack<T> getStack();
}
