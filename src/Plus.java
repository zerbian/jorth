import java.util.Stack;

public class Plus implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a + b);
        return 0;
    }
    
}
