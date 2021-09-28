package operations;

import java.util.Stack;

public class Plus implements Operation {

    @Override
    public void execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a + b);        
    }
    
}
