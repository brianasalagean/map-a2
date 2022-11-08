package Model.Utils;

import Exceptions.InterpreterException;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Set;

public interface MyIHeap {
    int getFreeValue();

    HashMap<Integer, Value> getContent();

    void setContent(HashMap<Integer, Value> newHeap);

    int add(Value value);

    void update(Integer key, Value value) throws InterpreterException;

    Value get(Integer key) throws InterpreterException;

    boolean containsKey(Integer key) throws InterpreterException;

    Set<Integer> keySet();

    void remove(Integer key) throws InterpreterException;
}
