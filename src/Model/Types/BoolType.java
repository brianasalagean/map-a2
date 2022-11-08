package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type{
    public boolean equals(Object obj){
        if (obj instanceof BoolType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "bool ";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type clone() {
        return new BoolType();
    }
}
