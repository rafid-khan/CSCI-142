package rit.cs;

public class SubExpression implements Expression{
    private Expression left;
    private Expression right;

    public SubExpression(Expression left, Expression right){

        // Initializes the node, where left is the left expression
        // and right is the right expression.

        this.left = left;
        this.right = right;
    }

    public int evaluate() {

        // returns the difference between the left and right evaluation

        return left.evaluate() - right.evaluate();
    }

    public String emit() {

        // returns the string

        return "(" + left.emit() + " - " + right.emit() + ")";
    }
}
