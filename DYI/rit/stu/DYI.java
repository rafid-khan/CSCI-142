package rit.stu;
import rit.cs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// Rafid Khan
// CSCI 142
// Professor Fathi
// SLI: Diogo A. & Ghislaine N.
// This file contains the main method and a helper method of the DYI Program.
// It utilizes the classes based on the Expression interface to accept operators
// and builds a parse tree based on the user inputs in order to return the correct
// answer.

public class DYI {
    public static void main(String[] args) {

        // This main method creates an instance of DYI and passes
        // a control to a method of that instance that handles the
        // processing loop.
        // It builds a list of the tokens for the prefix expression, and then
        // calls upon the helper function to build the current parse tree.

        DYI operator = new DYI();
        Scanner user_input = new Scanner(System.in);
        System.out.println("Derp Your Interpreter v1.0 :) ");
        String expression;

        System.out.print("> ");
        expression = user_input.nextLine();

        while(!expression.equals("quit")){
            List<String> tokens = new ArrayList<String>();
            StringTokenizer expressions = new StringTokenizer(expression, " ");
            while (expressions.hasMoreTokens()){
                tokens.add(expressions.nextToken());
            }
            Expression parsed = operator.parse((ArrayList) tokens);
            System.out.println("Emit: " + parsed.emit());
            System.out.println("Evaluate: " + parsed.evaluate());
            System.out.print("> ");
            expression = user_input.nextLine();
        }
        System.out.println("Goodbye! :( ");
    }

    public Expression parse(ArrayList tokens){

        // This is a helper method that uses recursion to
        // build the parse tree. It takes the current list of tokens
        // as an argument and returns the appropriate expression node
        // for the token at the front of the array.
        // The recursion ends when an integer is encountered in the array.

        String token = (String) tokens.remove(0);
        if ("+".equals(token)) {
            Expression left = parse(tokens);
            Expression right = parse(tokens);
            return new AddExpression(left, right);
        }
        else if ("-".equals(token)) {
            Expression left = parse(tokens);
            Expression right = parse(tokens);
            return new SubExpression(left, right);
        }

        else if ("*".equals(token)) {
            Expression left = parse(tokens);
            Expression right = parse(tokens);
            return new MulExpression(left, right);
        }

        else if ("/".equals(token)) {
            Expression left = parse(tokens);
            Expression right = parse(tokens);
            return new DivExpression(left, right);
        }

        else if ("%".equals(token)) {
            Expression left = parse(tokens);
            Expression right = parse(tokens);
            return new ModExpression(left, right);
        }
        int expression = Integer.parseInt(token);
        return new IntExpression(expression);
    }
}
