package Operations;

import java.util.Stack;

public class Print extends Operation {

    public static final String TOKEN = "print";

    @Override
    public int execute(Stack<Integer> s) {
        System.out.println(s.pop());
        return 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}
