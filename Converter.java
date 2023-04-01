/*
 * Name: Isaac Francisco Marte
 * Class : CMSC 350
 * Lab 1
 *
 * Purpose:
 * The Converter class methods convert from prefix to postfix and vice versa.
 * It tokenizes the input and pushes them within either the reversal or operand stack and pops
 * them to with the specified conversion.
 *
 *  * */
package cmsc350projects.project1;

import java.util.Stack;

// Converter class
public class Converter {

    // Method converts prefix to postfix expression and returns the value from stack
    protected static String prefixToPostfix(String expression) throws SyntaxError {

        Stack<String> reversalStack = new Stack<>();  // Stores token to be reversed & Operators
        Stack<String> operandStack = new Stack<>();
        expression = expression.trim();
        String temp = "";

        // Tokenizes string and push each token onto reversal stack
        for(int i = 0 ; i < expression.length(); i++) {
            reversalStack.push(Character.toString(expression.charAt(i))); // Pushes token onto reversal stack
        }

        // checks that the reversal stack is not empty before initiating loop
        while (!reversalStack.isEmpty()) {

            /* The conditional statement looks at the top value of the reversal stack
            and peeks at value if the value is not an operator.pushes the specific token
            on to the operand stack and pops the value from the reversal stack.
            Else if it is an operator it will pop to operands and add to the operator
            and pop the new string back to the operand stack.
            add repeats until reversal stack is empty*/

            if (!isOperator(reversalStack.peek())) {
                operandStack.push(reversalStack.pop());
            } else {
                String operand1 = operandStack.peek();
                operandStack.pop();

                String operand2 = operandStack.peek();
                operandStack.pop();

                String postExpression = operand1 + operand2 + reversalStack.pop();
                operandStack.push(postExpression);
            }
        }
        return operandStack.pop();
    }


    // Method which does conversion from postfix to prefix notation
    protected static String postfixToPrefix(String expression) throws SyntaxError {

        Stack<String> reversalStack = new Stack<>();  // Stores token to be reversed & Operators
        Stack<String> operandStack = new Stack<>();  // Stores operand tokens


        // Tokenizes string and push each token onto reversal stack
        for(int i = 0 ; i < expression.length(); i++) {
            reversalStack.push(Character.toString(expression.charAt(i))); // Pushes token onto reversal stack
        }


        // verifies that the stack is not empty before initiating loop
        while (!reversalStack.isEmpty()) {

            /* The conditional statement looks at the top value of the reversal stack
            and peeks at value if the value is not an operator.pushes the specific token
            on to the operand stack and pops the value from the reversal stack.
            Else if it is an operator it will pop to operands and add them behind the operator
            and pop the new string back to the operand stack.*/
            if (!isOperator(reversalStack.peek())) {
                operandStack.push(reversalStack.pop());
            } else {

                String operand1 = operandStack.pop();
                String operand2 = operandStack.pop();
                String prefixExpression = reversalStack.pop() + operand1 + operand2;
                operandStack.push(prefixExpression);
            }
        }
        return operandStack.pop();
    }


    // Boolean method checks for operator is one of the 4 listed cases below and is already inverted
    private static boolean isOperator(String x){
        return switch (x) {
            case "+", "-", "*", "/" -> true;
            default -> false;
        };
    }
}
