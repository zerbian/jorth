package operations;

import java.util.Stack;

public class Immediate implements Operation {

    private int value;

    public Immediate(int value) {
        this.value = value;
    }

    @Override
    public void execute(Stack<Integer> s) {
        s.push(value);        
    }
        
}
