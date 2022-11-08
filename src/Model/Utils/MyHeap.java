package Model.Utils;

import Exceptions.InterpreterException;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Set;

public class MyHeap implements MyIHeap{
    HashMap<Integer, Value> heap;
    Integer freeLocation;

    public int newValue() {
        freeLocation += 1;
        while(freeLocation == 0 || heap.containsKey(freeLocation))
            freeLocation += 1;
        return freeLocation;
    }

    public MyHeap() {
        heap = new HashMap<>();
        freeLocation = 1;
    }

    @Override
    public int getFreeValue() {
        return freeLocation;
    }

    @Override
    public HashMap<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public void setContent(HashMap<Integer, Value> newHeap) {
        heap = newHeap;
    }

    @Override
    public int add(Value value) {
        heap.put(freeLocation, value);
        Integer valueToReturn = freeLocation;
        freeLocation = newValue();
        return valueToReturn;
    }

    @Override
    public void update(Integer key, Value value) throws InterpreterException {
        if (!heap.containsKey(key))
            throw new InterpreterException(String.format("%d is not in the heap", key));
        heap.put(key, value);
    }

    @Override
    public Value get(Integer key) throws InterpreterException {
        if (!heap.containsKey(key))
            throw new InterpreterException(String.format("%d is not in the heap", key));
        return heap.get(key);
    }

    @Override
    public boolean containsKey(Integer key) throws InterpreterException {
        return heap.containsKey(key);
    }

    @Override
    public Set<Integer> keySet() {
        return heap.keySet();
    }

    @Override
    public void remove (Integer key) throws InterpreterException{
        if (!containsKey(key))
            throw new InterpreterException(key + " is not defined.");
        freeLocation = key;
        heap.remove(key);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
