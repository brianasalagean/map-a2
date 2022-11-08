package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type{
    public boolean equals(Object obj){
        if (obj instanceof StringType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "string ";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type clone() {
        return new StringType();
    }
}
