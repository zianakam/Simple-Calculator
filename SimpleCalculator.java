/**
 * Simple Calculator
 *
 * @author Ziana Kambere
 * Student Number: 300143238
 */
public class SimpleCalculator {
    static int top;
    static String[] stack = new String[100];

    /**
     * Main method
     *
     * @param args array of arguments
     */
    public static void main(String[] args) {
        String[] expression = {"(", "10", "+", "2", ")", "*", "(", "100", "/", "5", "^", "2", ")"};
        String[] outputExpression = infixToPostfix(expression);
        for (int i = 0; i < outputExpression.length; i++) {
            if (outputExpression[i] != null) {
                System.out.print(outputExpression[i] + " ");
            }
        }
        int finalAnswer = postfixEvaluation(outputExpression);
        System.out.println();
        System.out.println("Final answer = " + finalAnswer);

    }

    /**
     * Converts infix expression to postfix expression
     *
     * @param expression the infix expression
     * @return String array containing the postfix expression
     */
    public static String[] infixToPostfix(String[] expression) {
        int length = expression.length;
        String[] outputExpression = new String[length];
        int outputN = 0;
        top = -1;
        for (int i = 0; i < length; i++) {
            String temp = expression[i];
            if (isDigit(temp)) {
                outputExpression[outputN] = expression[i];
                outputN++;
            } else if (expression[i] == "^") {
                push(temp);
            } else if (expression[i] == "/") {
                outer:
                while (!isEmpty()) {
                    if (stack[top] == "^" || stack[top] == "/") {
                        outputExpression[outputN] = stack[top];
                        pop();
                        outputN++;
                    } else {
                        break outer;
                    }
                }
                push(temp);
            } else if (expression[i] == "*") {
                outer:
                while (!isEmpty()) {
                    if (stack[top] == "^" || stack[top] == "/" || stack[top] == "*") {
                        outputExpression[outputN] = stack[top];
                        pop();
                        outputN++;
                    } else {
                        break outer;
                    }
                }
                push(temp);
            } else if (expression[i] == "+") {
                outer:
                while (!isEmpty()) {
                    if (stack[top] == "^" || stack[top] == "/" || stack[top] == "*" || stack[top] == "+") {
                        outputExpression[outputN] = stack[top];
                        pop();
                        outputN++;
                    } else {
                        break outer;
                    }
                }
                push(temp);
            } else if (expression[i] == "-") {
                outer:
                while (!isEmpty()) {
                    if (stack[top] == "^" || stack[top] == "/" || stack[top] == "*" || stack[top] == "+" || stack[top] == "-") {
                        outputExpression[outputN] = stack[top];
                        pop();
                        outputN++;
                    } else {
                        break outer;
                    }
                }
                push(temp);
            } else if (expression[i] == "(") {
                push(temp);
            } else if (expression[i] == ")") {
                while (stack[top] != "(") {
                    outputExpression[outputN] = stack[top];
                    pop();
                    outputN++;
                }
                pop();
            }
        }

        while (!isEmpty()) {
            outputExpression[outputN] = stack[top];
            pop();
            outputN++;
        }

        return outputExpression;
    }

    /**
     * Evaluates postfix expressions
     *
     * @param outputExpression the infix expression
     * @return an integer result of that expression
     */
    public static int postfixEvaluation(String[] outputExpression) {
        int x, y, result, finalAnswer;
        for (int i = 0; i < outputExpression.length; i++) {
            if (outputExpression[i] != null) {
                String temp = outputExpression[i];
                if (isDigit(temp)) {
                    push(temp);
                } else if (outputExpression[i] == "^") {
                    x = Integer.parseInt(stack[top]);
                    pop();
                    y = Integer.parseInt(stack[top]);
                    pop();
                    result = y;
                    for (int j = 1; j < x; j++) {
                        result *= y;
                    }
                    String resultString = Integer.toString(result);
                    push(resultString);
                } else if (outputExpression[i] == "/") {
                    x = Integer.parseInt(stack[top]);
                    pop();
                    y = Integer.parseInt(stack[top]);
                    pop();
                    result = y / x;
                    String resultString = Integer.toString(result);
                    push(resultString);
                } else if (outputExpression[i] == "*") {
                    x = Integer.parseInt(stack[top]);
                    pop();
                    y = Integer.parseInt(stack[top]);
                    pop();
                    result = y * x;
                    String resultString = Integer.toString(result);
                    push(resultString);
                } else if (outputExpression[i] == "+") {
                    x = Integer.parseInt(stack[top]);
                    pop();
                    y = Integer.parseInt(stack[top]);
                    pop();
                    result = y + x;
                    String resultString = Integer.toString(result);
                    push(resultString);
                } else if (outputExpression[i] == "-") {
                    x = Integer.parseInt(stack[top]);
                    pop();
                    y = Integer.parseInt(stack[top]);
                    pop();
                    result = y - x;
                    String resultString = Integer.toString(result);
                    push(resultString);
                }
            }
        }
        finalAnswer = Integer.parseInt(stack[top]);
        pop();
        return finalAnswer;
    }

    /**
     * Tests to see if a string is a digit
     *
     * @param test the string to be evaluated
     * @return whether the string is a digit or not
     */
    public static boolean isDigit(String test) {
        try {
            Double.parseDouble(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Pushes a string to the stack
     *
     * @param temp temporary string that holds the value
     * @return whether the push was successful or not
     */
    public static boolean push(String temp) {
        if (isFull(stack)) {
            return false;
        }
        top++;
        stack[top] = temp;
        return true;
    }

    /**
     * Pops values from the stack
     *
     * @return whether the pop was successful or not
     */
    public static boolean pop() {
        if (isEmpty()) {
            return false;
        }
        top--;
        return true;
    }

    /**
     * Determines if the stack is empty
     *
     * @return whether the stack is empty or not
     */
    public static boolean isEmpty() {
        if (top < 0) {
            return true;
        }
        return false;
    }

    /**
     * Determines if the stack is full
     *
     * @param stack the stack
     * @return whether the stack is full or not
     */
    public static boolean isFull(String[] stack) {
        if (top == stack.length) {
            return true;
        }
        return false;
    }

}
