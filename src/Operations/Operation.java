package Operations;

import java.util.Stack;

public abstract class Operation {
    public static final String TOKEN = null;

    public int execute(Stack<Integer> s) {
        return 0;
    }

    public String getRep() {
        return TOKEN;
    }
}