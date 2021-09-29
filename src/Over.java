import java.util.Stack;

public class Over extends Operation {

    public static final String TOKEN = "over";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.peek();
        s.push(a);
        s.push(b);
        return 0;
    }
    
}
