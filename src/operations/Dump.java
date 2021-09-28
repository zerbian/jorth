package operations;

import java.util.Stack;

public class Dump implements Operation {

    @Override
    public void execute(Stack<Integer> s) {
        System.out.println(s.pop());        
    }
    
}
