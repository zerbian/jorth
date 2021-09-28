package operations;

import java.util.Stack;

public class Mult implements Operation {

    @Override
    public void execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a * b);
        
    }

}
