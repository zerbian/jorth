package Operations;
import java.util.Stack;

public class Equal extends Operation {

    public static final String TOKEN = "==";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a == b ? 1 : 0);
        return 0;
    }
    @Override
    public String getRep() {
        return TOKEN;
    }
}
