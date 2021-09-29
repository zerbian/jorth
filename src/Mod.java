import java.util.Stack;

public class Mod extends Operation{

    public static final String TOKEN = "mod";

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a % b);
        return 0;
    }   
}
