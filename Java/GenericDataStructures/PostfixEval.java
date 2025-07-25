//********************************************************************
//
//  Developer:     Tiffany Pham 
//
//  Program #:     Five
//
//  File Name:     PostfixEval.java
//
//  Course:        ITSE 2317 Intermediate Java Programming
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       21
//
//  Description:   Evaluates postfix expression using a custom stack 
//                 and calling PostfixEval class.
//
//********************************************************************

    //***************************************************************
    //
    //  Class:        PostfixEval
    //
    //  Description:  Evaluates a postfix expression using 
    //                evaluatePostfixInput, evaluates input, and 
    //                calculates input.
    //
    //  Parameters:    N/A
    //
    //  Returns:       N/A
    //
    //***************************************************************

public class PostfixEval {
    private Stack<Integer> stack;

    public PostfixEval() {
        stack = new Stack<>();
    }

    //***************************************************************
    //
    //  Method:       evaluatePostfixInput
    //
    //  Description:  Evaluates a postfix expression passed as a StringBuffer.
    //
    //  Parameters:   StringBuffer expression 
    //
    //  Returns:      Stack unless error
    //
    //***************************************************************

    public int evaluatePostfixInput(StringBuffer expression) {
        stack = new Stack<>(); //start with a clean slate
        int index = 0;
    
        //add in throw errors 
        while (index < expression.length()) {
            char currentChar = expression.charAt(index);
    
            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
    
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    number.append(expression.charAt(index));
                    index++;
                }
    
                int value = Integer.parseInt(number.toString());
                stack.push(value);
            }
            else if (isOperator(currentChar)) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough operands for operator '" + currentChar + "'");
                }
    
                int op2 = stack.pop();
    
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough operands for operator '" + currentChar + "'");
                }
    
                int op1 = stack.pop();
    
                int result = calculate(op1, op2, currentChar);
                stack.push(result);
                index++;
            }
            else if (currentChar == ')') {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Stack is empty at the end of evaluation.");
                }
    
                int finalResult = stack.pop();
    
                if (!stack.isEmpty()) {
                    throw new IllegalArgumentException("Too many operands. Stack not empty after evaluation.");
                }
    
                return finalResult;
            }
            else if (currentChar == ' ' || currentChar == '\t') {
                index++; // skip whitespace
            }
            else {
                throw new IllegalArgumentException("Invalid character '" + currentChar + "'");
            }
        }
    
        throw new IllegalArgumentException("Missing closing parenthesis ')'");
    }
    
    
    //***************************************************************
    //
    //  Method:       isOperator
    //
    //  Description:  Checks if a character is a valid arithmetic operator.
    //
    //  Parameters:   char ch 
    //
    //  Returns:      ch
    //
    //***************************************************************
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '%';
    }

    
    //***************************************************************
    //
    //  Method:       calculate
    //
    //  Description:  Performs an operation on two operands.
    //
    //  Parameters:   int op1 – left operand
    //                int op2 – right operand
    //                char operator – final operation 
    //
    //  Returns:      Result of expression or error
    //***************************************************************
    private int calculate(int op1, int op2, char operator) {
        return switch (operator) {
            case '+' -> op1 + op2;
            case '-' -> op1 - op2;
            case '*' -> op1 * op2;
            case '/' -> op1 / op2;
            case '%' -> op1 % op2;
            case '^' -> (int) Math.pow(op1, op2);
            default  -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }    
}
