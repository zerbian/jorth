package Operations;
import java.util.Stack;

public class End extends Operation {

    public static final String TOKEN ="end";

    public Object reference;

    @Override
    public int execute(Stack<Integer> s) {
        return 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}

