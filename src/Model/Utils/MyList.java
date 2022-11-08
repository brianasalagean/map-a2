package Model.Utils;

import Exceptions.InterpreterException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList() {
        list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public T pop() throws InterpreterException {
        if (list.isEmpty())
            throw new InterpreterException("list is empty");
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
