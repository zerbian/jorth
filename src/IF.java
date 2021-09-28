import java.util.Stack;

public class IF implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        return s.pop();
    }
    
}
