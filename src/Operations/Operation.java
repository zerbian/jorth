package Operations;

import java.util.Stack;

public abstract class Operation {
    public static final String TOKEN = null;
    public abstract int execute(Stack<Integer> s);
    public String getRep() {
        return TOKEN;
    }
}