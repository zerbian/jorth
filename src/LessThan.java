import java.util.Stack;

public class LessThan extends Operation {

    public static final String TOKEN = "<";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(b < a ? 1 : 0);
        return 0;
    }
}
