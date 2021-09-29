import java.util.Stack;

public class Drop extends Operation {

    public static final String TOKEN = "drop";

    @Override
    public int execute(Stack<Integer> s) {
        s.pop();
        return 0;
    }
    
}
