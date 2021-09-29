import java.util.Stack;

public class Dup implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        int value = s.pop();
        s.push(value);
        s.push(value);
        return 0;
    }
    
}
