# Prefix-Postfix-Converter

Main Class
The Main class extends the JFrame module and implements the action listener which provides the window for the graphical user interface that the user interacts with.  Using several methods within this class to add content and layout to the window as well as being able to respond to user input and handle exceptions that are thrown by the application such as Runtime Exception, Empty Stack, and Syntax exceptions. 

Main class constructor
The main class constructor instantiates the JPanel object and sets the main panel variable name and content pane for the JFrame/Window. Using the main panel, we initiate the empty border method which will reflect on the window. A Border Layout is used to manage the location of the additional panels used to position and encapsulate the functionality used by different methods based on tasks each panel holds within the program. The positions are set by methods which note their location within the border layout of North, Center, and South. 
 
North Panel Method
The north panel creates a JPanel object which is instantiated with the same variable name as the method. This panel is set north of the border layout and adds the label for the user to know where to input their expression, as well as the test field within the GUI, which displays at the top of the window and returns the northPanel/JPanel object.
 

Center Panel Method
The center panel is instantiated as new JPanel object and is positioned at the center of the border layout. The centerPanel holds the same variable name as its method and adds the Prefix button and Postfix button conversion onto its panel. It also holds the action listener to each button and returns the centerPanel / JPanel object. 
 

South Panel Method
The south panel initiates a JPanel object which is instantiated with the same variable name as the method itself. This panel is set south of the border layout and adds the label for the user to know where the result for their expression will be as well as a text field which displays the result of the conversion. The method returns the south panel object to provide the components to the bottom of the window. 
 
Action Performed Method
The action performed method provides and aligns the listeners for the buttons using the variable Action Event e within its parameters.  Once the user presses one of the buttons the if conditional within the method checks which button an action was performed and sets it to e with the getsource function which retrieves the input from user. 
The else if condition that proceeds it checks to see if the Prefix to Postfix button was pressed. Once any of the buttons are pressed the result field variable uses the set text function and within its parameters, we call the Converter class specific method to the button. The conversion method within its parameters has the expression field with the get text method. The expression holds the value of the user input and the get text retrieve the values to set the text once the method performs the conversion. The conditional statements are also within a try and catch statement which are used to capture Empty stack exception, Syntax Error, and runtime exceptions that could be thrown by the application. A JOptionPane is used to provide a GUI that displays an error message to the user informing what error was caught.  


 
 
Main Method ()
The main method instantiates the JFrame object within its parameters and sets it to the variable frame. With the frame variable we apply several frame methods to control the main aspects of the window such as Set visible to true so the user can see it, adding the set default to close so that the program terminates when window is closed, and setting the size of the window and setting the resizable method to false so the user can’t change the size of the window application. 
 

Converter Class
The converter class uses stacks to change the conversion of the input provided by the user as well as tokenizing the string input and providing the correct format for the specific conversion based on which button is pressed by user.

Prefix to Post Fix Method
The prefixToPostfix method carries the String variable named expression and throws Syntax Error. We instantiate 2 new stack objects named reversalStack and operand stack. The expression is tokenized by using the a for loop to loop through the expression and using the trim method we remove the whitespace and use the CharAt method to push the i/char of the expression value into the reversal stack. A while loop then initiates so long as the reversal stack is not empty the if conditional statement verifies if the character that is being peeked on is an operator and if it is not, it pushes them to the operand stack from the reversal stack. To accomplish this is operator method call holds within its parameters the reversal stack uses the peek module to look at the token, while the proceeding operand stack uses the push method and within its parameters and the reversal pops the token. If the else statements initiate the operand stack is pop 2 variables for each value and added to the reversal.pop() from the reversal stack and pushes it that format back to the operand stack. The method returns the operand.pop () which pops the values out in the postfix format. 
The Postfix to prefix method works similarly to this one except that once the else conditional is reached it sets the format to operator + Operand 1 + Operand 2. 
Prefix to post
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


Is Operator Method
Is operator method is called by either of the conversion methods to check if the operator values within its switch case match one of the tokens within the stack to properly handle. If so it will return true else it will return false. 
 

Syntax Error Class
Custom class which extends the runtime exception and uses a constructor which has a string variable named message. The super method has the message string within its parameters. This custom exception is used to address the user if the operand stack is not empty by throwing a syntax error which displays error 2 JOptionPane GUI Object along with the message informing the user to check the expression entered since the stack was never cleared after the conversion.  
 

Note: The application for the works well their is a small bug where not the full values of the output display after the conversion. I am currently working on correcting the minor bug but if you identify it or have any suggestions please email me @ marte07x@gmail.com
