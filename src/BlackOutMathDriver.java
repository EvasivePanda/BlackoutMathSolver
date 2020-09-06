

/**
 * Driver for the BlackOutMath Class. 
 * Tests the methods of the BlackOutMathClass
 * 
 * This file contains a few example test cases to get you started.
 * Your completed version should be documented and contain many more test cases.
 * 
 * 
 * 
 * @author YOUR NAME HERE
 * 
 */
public class BlackOutMathDriver{


    public static void main(String[] args){

        BlackOutMath b = new BlackOutMath();


        String exampleExpression   = "[(21+9)/4]*(2^3-2)";      //should return 42
        String exampleExpressions   = "[(7-4)-3]-3*2";      //should return 30
        String exampleExpressiona   = "3+2*(3^2)";      //should return 30
        String MalformedExpression = "[5+1";                 //should return null

        String exampleEquation       = "[(137-3)/2]^2=3+2^20+6*7"; //should return [(17-3)/2]^2=3+2^2+6*7 
        String exampleEquationPuzzle = "189/24+8=11*3-16";         //should return the string "18/2+8=11*3-16"
        String exampleEquationPuzzles = "(8+2)=(9+1)/7";         //should return the string "18/2+8=11*3-16"

       System.out.println("Example evaluate method " + MalformedExpression   + " : " + b.evaluate(MalformedExpression));
       System.out.println("Example evaluate method " + exampleExpressiona   + ": " + b.evaluate(exampleExpressiona));
       
       System.out.println("Example on solve method " + exampleEquationPuzzles    + ": " + b.solve(exampleEquationPuzzles));
       System.out.println("Example on solve method " + exampleEquation    + ": " + b.solve(exampleEquation));

       
       
    }
}