package Exceptions;

public class InterpreterException extends Exception{
    private String msg;

    public InterpreterException(String msg) {
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }
}
