import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Class that solves blackout math puzzles.
 * 
 * 
 * Due Date: 4/3/2020
 * 
 * @author 
 * 
 */
public class BlackOutMath{
    private static final String OPERATORS = "-+*/^";
    private static final String OPEN      = "([{";
    private static final String CLOSE     = ")]}";
    private static final int[]  OPERATORPRECEDENCE = {1,1,2,2,3};

    /**
     * Evaluates the given infix expression, returning its value.
     * This method should solve the expression with two stacks, and should NOT convert the expression into another form (such as postfix).
     * 
     * 
     * The specific function has a algorithm that goes through the equation and does any initial operations. For example 2+4/2, the 4/2 
     * will be spotted as a higher precedence and 4/2 will be done before 2+4. Then after the higher precedence equations are done. The program runs through the 
     * remaining stack of operators and numbers, in our example,2+2 will now be done. This means the algorithm has a worst case of 2n which makes for a O of  O(n).
     * 
     * 
     * @param infix a string representation of an infix expression. Should only contain characters that are digits, operators, or parentheses.
     * @return the value of the expression, or null if it cannot be evaluated.
     */
    public Integer evaluate(String infix) {
        Stack<Integer>   nums      = new Stack<Integer>(); 
        Stack<Character> operators = new Stack<Character>(); 
  
        for (int i = 0; i < infix.length(); i++){    
        	if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9'){ 
                String multiDigNum = ""; 
                while (i < infix.length() && infix.charAt(i) >= '0' && infix.charAt(i) <= '9') {
                	multiDigNum=multiDigNum+infix.charAt(i);
                	i++;
                    }
                nums.push(Integer.parseInt(multiDigNum)); 
            } 
            if(i < infix.length()){
            	if(isOpen(infix.charAt(i))){
            	   operators.push(infix.charAt(i)); 
            	}

            	if(isClose(infix.charAt(i))){ 
            		try{
            			while (!isMatch(operators.peek(),(infix.charAt(i))) ){                
            				nums.push(doOperation(operators.pop(), nums.pop(), nums.pop())); 
            			}
            			}catch (EmptyStackException e){
            				return null;
            			}
            		operators.pop(); 
            	} 
          
            	if (isOperator(infix.charAt(i))){ 
                while (!operators.empty()&& !isOpen(operators.peek()) && OPERATORPRECEDENCE[OPERATORS.indexOf(infix.charAt(i))] <= OPERATORPRECEDENCE[OPERATORS.indexOf(operators.peek())]){
                	try{
                		nums.push(doOperation(operators.pop(), nums.pop(), nums.pop()));
                		}catch(EmptyStackException e){
                			return null;
                		}
                	}
                operators.push(infix.charAt(i));  
            	}    	
            }
        }   
     while (!operators.empty()&&!nums.isEmpty()) {
    	 	try{
    	 		nums.push(doOperation(operators.pop(), nums.pop(), nums.pop()));
    	 	}
    	 	catch(EmptyStackException e){
    	 		return null;
    	 	}
     }
    if(!nums.isEmpty()){
      return nums.pop();
     }
	return null;
    } 
    
 /**
  * Algorithm used to actually do math. Adding, subtracting, multiplying, divividing and exponentation based on input
  * 	parameters.
  * @param op : The operation that will be performed on the equation (+ - * / ^ )
  * @param secondVal : Number on left side of expression 
  * @param firstVal : Number on right side of expression
  * @return Integer value of the equation given
  * 
  * O(1) as algorithm has constant running no matter input
  */
    public static int doOperation(char op, int secondVal, int firstVal) 
    { 
    	if(op == '+'){
    		return firstVal+secondVal;
    	}
    	if(op == '-'){
    		return firstVal-secondVal;
    	}
    	if(op == '*'){
    		return firstVal*secondVal;
    	}
    	if(op == '^'){
    		return (int) Math.pow(firstVal, secondVal);
    	}
    	if(op == '/' && secondVal!=0){
    		return firstVal/secondVal;
    	}
        return 0; 
    } 

    /**
     * This method makes for an easy boolean check to see if a character is part of 
     * 	the OPEN bracket array ( { [
     * @param ch : Character being tested
     * @return : If character is in the open bracket list of characters
     * O(1) 
     */
    private static boolean isOpen(char ch){
    	return OPEN.indexOf(ch)> -1;
    }
    
    /**
     * This method makes for an easy boolean check to see if a character is part of 
     * 	the CLOSE bracket array ( { [
     * @param ch : Character being tested
     * @return : If character is in the closed bracket list of characters
     * O(1) as algorithm has constant running no matter input
     */
    private static boolean isClose(char ch){
    	return CLOSE.indexOf(ch)> -1;
    }
    
    /**
     * This method tests if the two characters being tested are a "pair" of brackets
     * 	eg. ( and ) returns true, {  and  ] returns false
     * @param brack1 : First character being tested
     * @param brack2 : Second character being tested
     * @return : If the two brackets match
     */
    private static boolean isMatch(char brack1, char brack2){
    	if(isOpen(brack1)&&isClose(brack2)){
    		return OPEN.indexOf(brack1) == CLOSE.indexOf(brack2);
    	}
    	return false;
    }
    
    /**
     * Tells if the character passed in is part of the operator array
     * @param ch : Character being tested
     * @return : If the character is an operator
     */
    private static boolean isOperator(char ch){
    	return OPERATORS.indexOf(ch)> -1;
    }
    
    
    
    /**
     * 
     * Evaluates a blackout math puzzle.
     * 
     * This method takes as input a string representing an equation with two infix expressions separated by an equal sign.
     * It returns a string representing an infix equation that evaluates to true.
     * 
     * The output string should be the input string with exactly two characters removed. 
     * 
     * This method has nested loops. Both going to n. The way the loops are setup (i = 0, j = i+1) The runtime is n(n-1)/2. This part is O(n^2).
     *  This area inside the loops runs for time n. The asymptotic runtime for the 
     * 	algorithm is O(n^3) as the program needs to go n^2 times and each time do the math which is O(n). Making for a total of O(n^3).
     * 
     * @param infix An infix equation consisting of two infix expressions separated by an equals sign.
     * @return a string representation of the correct infix equation with two characters removed, or null otherwise.
     */
    public String solve(String infix){
    	StringBuilder leftUnchanged   = new StringBuilder();  
    	StringBuilder rightUnchanged  = new StringBuilder();  
    	StringBuilder leftSide       = new StringBuilder();  
    	StringBuilder rightSide      = new StringBuilder();  
    	boolean  doneBlackOut;
    	boolean  foundEquals  = false;
    	
    	for(int k = 0; k < infix.length(); k++){
    		if(infix.charAt(k)=='='){
    			foundEquals = true;
    		}else{
    			if(!foundEquals){
    				leftUnchanged.append(infix.charAt(k)); 
    			}else{
    				rightUnchanged.append(infix.charAt(k));
    			}
    		}
    	}
    	
    	leftSide.append(leftUnchanged);
    	rightSide.append(rightUnchanged);
    	
    	for(int i = 0; i < leftSide.length()+rightSide.length(); i++){
    		for(int j = i+1; j < leftSide.length() + rightSide.length();j++){
    				doneBlackOut = false;
    					if(i < leftSide.length() && j < leftSide.length() && !doneBlackOut){
    						 leftSide.deleteCharAt(i);    						 
    						 leftSide.deleteCharAt(j-1);
    						 doneBlackOut = true;
    					 }
    					if(i < leftSide.length() && j >= leftSide.length() && !doneBlackOut){
   						 	leftSide.deleteCharAt(i);
        					rightSide.deleteCharAt(j-leftSide.length()-1);
   						 	doneBlackOut = true;
    					 }
    					if(i >= leftSide.length() && j >= leftSide.length() && !doneBlackOut){
    						rightSide.deleteCharAt(i-leftSide.length());
    						rightSide.deleteCharAt(j-leftSide.length()-1);
       						doneBlackOut = true;
    					 }
    					
    					if(evaluate(leftSide.toString())==evaluate(rightSide.toString()) && evaluate(rightSide.toString())!=null&& evaluate(leftSide.toString())!=null){
			    			return leftSide+" = "+ rightSide;
			    		}
    				leftSide.setLength(0);
    		    	rightSide.setLength(0);
    		    	leftSide.append(leftUnchanged);
        		    rightSide.append(rightUnchanged);
    		}
    	}			
     return null;
    }
}

