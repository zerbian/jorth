import java.util.Stack;

public class Do implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        if (s.pop() == 1) return 1;
        return 0;
    }
    
}
