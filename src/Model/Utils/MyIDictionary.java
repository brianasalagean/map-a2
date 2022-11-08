package Model.Utils;

import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    void put (T1 key, T2 value);
    T2 get(T1 key);
    boolean containsKey(T1 key);
    Set<T1> getKeys();
    void remove(T1 key);
    Map<T1, T2> getContent();
    MyIDictionary<T1, T2> copy();
}
