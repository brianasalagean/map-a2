import Exceptions.InterpreterException;
import View.View;

public class Main {
    public static void main(String[] args) throws InterpreterException {
        View view = new View(false); // change this to true if you want to have it in debug mode
        view.runApp();
    }
}