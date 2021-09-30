package Operations;
import java.util.Stack;

public class Dup extends Operation {

    public static final String TOKEN = "dup";

    @Override
    public int execute(Stack<Integer> s) {
        int value = s.peek();
        s.push(value);
        return 0;
    }
    @Override
    public String getRep() {
        return TOKEN;
    }
}
