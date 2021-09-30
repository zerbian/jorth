package Operations;

import java.util.Stack;

public class While extends Operation {

    public static final String TOKEN = "while";

    @Override
    public int execute(Stack<Integer> s) {
        return 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}

