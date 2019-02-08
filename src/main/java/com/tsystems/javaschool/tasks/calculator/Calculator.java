package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here

        if (statement == null | statement == ""){
            return null;
        }

        String addition = "+";
        String substraction = "-";
        String multiplication = "*";
        String division = "/";
        String leftbracket = "(";
        String rightbracket = ")";

        Stack<String> temporaryStack = new Stack<>();
        Stack<String> inputStack = new Stack<>();
        Stack<String> outputStack = new Stack<>();
        Stack<String> stackOperators = new Stack<>();
        Stack<String> reverseStack = new Stack<>();
        Stack<Double> computingStack = new Stack<>();

        String crutch = "crutch";
        stackOperators.push(crutch);
        inputStack.push(crutch);

        Double leftNumber;
        Double rightNumber;
        Double outResult;
        int stackCounter = 1;
        int reverseCounter = 0;

        String solution;

        if (statement.contains(",") | statement.contains("..") | statement.contains("//")| statement.contains("**") | statement.contains("++") |
                statement.contains("--")) {
            return null;
        }

//преобразование выражения в обратную польскую нотацию
        StringTokenizer stTok = new StringTokenizer(statement, "+-*/()", true);
        while (stTok.hasMoreTokens()) {
            temporaryStack.push(stTok.nextToken());
            stackCounter++;
        }
        while (!temporaryStack.isEmpty()){
            inputStack.push(temporaryStack.peek());
            temporaryStack.pop();
        }

        for (int i=1; i<stackCounter; i++){
            if (Character.isDigit(inputStack.peek().charAt(0))){
                outputStack.push(inputStack.peek());
                inputStack.pop();
            }
            else if (stackOperators.peek().equals(crutch) && (inputStack.peek().equals(addition) |
                    inputStack.peek().equals(substraction) | inputStack.peek().equals(multiplication) |
                    inputStack.peek().equals(division))){
                stackOperators.push(inputStack.peek());
                inputStack.pop();
            }
            else if (inputStack.peek().equals(addition) | inputStack.peek().equals(substraction)){
                while (stackOperators.peek().equals(addition) | stackOperators.peek().equals(substraction) |
                        stackOperators.peek().equals(multiplication) | stackOperators.peek().equals(division)){
                    outputStack.push(stackOperators.peek());
                    stackOperators.pop();
                }
                stackOperators.push(inputStack.peek());
                inputStack.pop();
            }
            else if (inputStack.peek().equals(multiplication) | inputStack.peek().equals(division)){
                while (stackOperators.peek().equals(multiplication) | stackOperators.peek().equals(division)){
                    outputStack.push(stackOperators.peek());
                    stackOperators.pop();
                }
                stackOperators.push(inputStack.peek());
                inputStack.pop();
            }
            else if (inputStack.peek().equals(leftbracket)){
                stackOperators.push(inputStack.peek());
                inputStack.pop();
            }
            else if (inputStack.peek().equals(rightbracket)){
                while (!stackOperators.peek().equals(leftbracket)){
                    outputStack.push(stackOperators.peek());
                    stackOperators.pop();
                }
                if (stackOperators.peek().equals(crutch)){
                    return null;
                }
                inputStack.pop();
                stackOperators.pop();
            }
        }
        while (!stackOperators.peek().equals(crutch)){
            if (stackOperators.peek().equals(leftbracket) | stackOperators.peek().equals(rightbracket)){
                return null;
            } else {
                outputStack.push(stackOperators.peek());
                stackOperators.pop();
            }

        }
        while (!outputStack.isEmpty()){
            reverseStack.push(outputStack.peek());
            outputStack.pop();
            reverseCounter++;
        }

//вычисление выражения
        for (int i=0; i<reverseCounter; i++){
            if (Character.isDigit(reverseStack.peek().charAt(0))) {
                computingStack.push(Double.parseDouble(reverseStack.peek()));
            }
            else if (reverseStack.peek().equals(addition)){
                rightNumber = computingStack.peek();
                computingStack.pop();
                leftNumber = computingStack.peek();
                computingStack.pop();
                outResult = leftNumber + rightNumber;
                computingStack.push(outResult);
            }
            else if (reverseStack.peek().equals(substraction)){
                rightNumber = computingStack.peek();
                computingStack.pop();
                leftNumber = computingStack.peek();
                computingStack.pop();
                outResult = leftNumber - rightNumber;
                computingStack.push(outResult);
            }
            else if (reverseStack.peek().equals(multiplication)){
                rightNumber = computingStack.peek();
                computingStack.pop();
                leftNumber = computingStack.peek();
                computingStack.pop();
                outResult = leftNumber * rightNumber;
                computingStack.push(outResult);
            }
            else if (reverseStack.peek().equals(division)){
                rightNumber = computingStack.peek();
                computingStack.pop();
                leftNumber = computingStack.peek();
                computingStack.pop();
                if (rightNumber!=0){
                    outResult = leftNumber / rightNumber;
                    computingStack.push(outResult);
                } else
                    return null;
            }
            reverseStack.pop();
        }

        DecimalFormat myFormatter = new DecimalFormat("#.####");
        String format = myFormatter.format(computingStack.peek());

        solution = format.replace( ',', '.');

        return solution;
    }

}


