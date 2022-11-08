package Model.State;

import Exceptions.InterpreterException;
import Model.Statements.IStatement;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIList;
import Model.Utils.MyIStack;
import Model.Values.Value;

import java.util.ArrayList;
import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> output;
    private MyIHeap heap;
    private IStatement originalProgram;

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String, Value> symTable, MyIList<Value> output, MyIHeap heap, IStatement org) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.output = output;
        this.heap = heap;
        this.originalProgram = org.deepCopy();
        this.exeStack.push(org);
    }

    public void setExeStack(MyIStack<IStatement> newStack){
        this.exeStack = newStack;
    }

    public void setSymTable(MyIDictionary<String, Value> newSymTable){
        this.symTable = newSymTable;
    }

    public void setOutput(MyIList<Value> newOutput) {
        this.output = newOutput;
    }

    public void setHeap(MyIHeap newHeap) {
        this.heap = newHeap;
    }

    public MyIStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return this.symTable;
    }

    public MyIList<Value> getOutput() {
        return this.output;
    }

    public MyIHeap getHeap() {
        return this.heap;
    }

    public String exeStackToString() throws InterpreterException {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = new ArrayList<>();
        MyIStack<IStatement> newStack= exeStack;
        while (!exeStack.isEmpty()){
            stack.add(newStack.getTop());
            newStack.pop();
        }
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws InterpreterException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.getKeys()) {
            symTableStringBuilder.append(String.format("%s->%s\n", key, symTable.get(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outputToString() {
        StringBuilder outputStringBuilder = new StringBuilder();
        for (Value value: output.getList()){
            outputStringBuilder.append(String.format("%s\n", value.toString()));
        }
        return outputStringBuilder.toString();
    }

    public String heapToString() throws InterpreterException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d->%s\n", key, heap.get(key).toString()));
        }
        return heapStringBuilder.toString();
    }

    @Override
    public String toString() {
        try {
            return "Execution stack:\n" + exeStackToString() +
                    "\nSymbol table:\n" + symTableToString() +
                    "\nOutput list:\n" + outputToString() + "\n";
        } catch (InterpreterException e) {
            System.out.println(e.toString());
        }
        return "";
    }

    public String programStateToString() throws InterpreterException {
        return "Execution stack:\n" + exeStackToString() +
                "\nSymbol table:\n" + symTableToString() +
                "\nOutput list:\n" + outputToString() +
                "\nHeap memory:\n" + heapToString() + "\n";
    }
}
