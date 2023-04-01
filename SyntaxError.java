/*
 * Name: Isaac Francisco Marte
 * Class : CMSC 350
 * Lab 1
 *
 * Purpose:
 * Syntax Error class is called by other classes at runtime if an exception is caught such as
 * if an item is still in the stack
 *
 * */
package cmsc350projects.project1;

// This class extends the runtime exception module and creates our own syntax error exception to be thrown
public class SyntaxError extends RuntimeException{

// Constructor which has the string message variable that is replaced by the specified message
// when exception is thrown while running.
    public SyntaxError(String message){
        super(message);

    }

}
