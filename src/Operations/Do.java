package Operations;

import java.util.Stack;

public class Do extends Operation {

    public static final String TOKEN = "do";

    @Override
    public int execute(Stack<Integer> s) {
        if (s.pop() == 1) return 1;
        return 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}
