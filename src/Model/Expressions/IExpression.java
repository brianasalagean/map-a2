package Model.Expressions;

import Exceptions.InterpreterException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.Value;

public interface IExpression {
    Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws InterpreterException;

    IExpression deepCopy();
}
