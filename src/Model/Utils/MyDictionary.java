package Model.Utils;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2>{
    private HashMap<T1, T2> dict;

    public MyDictionary(){
        dict = new HashMap<>();
    }

    @Override
    public void put(T1 key, T2 value) {
        dict.put(key, value);
    }

    @Override
    public T2 get(T1 key) {
        return dict.get(key);
    }

    @Override
    public boolean containsKey(T1 key) {
        return dict.containsKey(key);
    }

    @Override
    public Set<T1> getKeys() {
        return dict.keySet();
    }

    @Override
    public void remove(T1 key) {
        dict.remove(key);
    }

    @Override
    public Map<T1, T2> getContent() {
        return dict;
    }

    @Override
    public MyIDictionary copy() {
        MyDictionary<T1, T2> copyDict = new MyDictionary<>();
        for (T1 key: dict.keySet())
            copyDict.put(key, dict.get(key));
        return copyDict;
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append("{ ");
        for (T1 key: this.dict.keySet() ){
            if (this.dict.get(key) instanceof BufferedReader) {
                sb.append(key.toString());
                sb.append(",");
            }
            else {
                sb.append(key.toString());
                sb.append("->");
                sb.append(this.dict.get(key).toString());
                sb.append(",");
            }
        }
        sb = sb.replace(sb.length()-1, sb.length(), "}");
        return sb.toString();
    }
}
