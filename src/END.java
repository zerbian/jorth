import java.util.Stack;

public class End implements Operation {

    Object returnReference = null;

    @Override
    public int execute(Stack<Integer> s) {
        return 0;
    }

    public Object getReference() {
        return returnReference;
    }
    
}