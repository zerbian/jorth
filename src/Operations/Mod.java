package Operations;
import java.util.Stack;

public class Mod extends Operation{

    public static final String TOKEN = "mod";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(b % a);
        return 0;
    }

    @Override
    public String getRep() {
        return TOKEN;
    }
}
