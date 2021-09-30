package Operations;
import java.util.Stack;

public class If extends Operation {

    public static final String TOKEN = "if";

    @Override
    public int execute(Stack<Integer> s) {
        return s.pop() == 1 ? 1 : 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}
