package rit.cs;

public class IntExpression implements Expression {
    private int value;
    public IntExpression(int value){

        // Initializes value variable in the constructor

        this.value = value;
    }

    public int evaluate(){

        // returns the integer value

        return value;
    }

    public String emit(){

        // returns int as a string

        return value + "";
    }
}
