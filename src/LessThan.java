import java.util.Stack;

public class LessThan implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a < b ? 1 : 0);
        return 0;
    }
    
}
