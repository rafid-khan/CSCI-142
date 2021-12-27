package rit.cs;

public class DivExpression implements Expression {
    private Expression left;
    private Expression right;

    public DivExpression(Expression left, Expression right){

        // Initializes the node, where left is the left expression
        // and right is the right expression.

        this.left = left;
        this.right = right;
    }

    public int evaluate(){

        // returns the quotient between the left and right evaluation

        return left.evaluate() / right.evaluate();
    }

    public String emit(){

        // returns the string

        return "(" + left.emit() + " / "  + right.emit() + ")";
    }
}
