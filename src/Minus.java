import java.util.Stack;

public class Minus extends Operation {

    public static final String TOKEN = "-";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(b - a);
        return 0;
    }
}
