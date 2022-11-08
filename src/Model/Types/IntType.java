package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type{
    public boolean equals(Object obj){
        if (obj instanceof IntType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "int ";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type clone() {
        return new IntType();
    }
}
