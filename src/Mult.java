import java.util.Stack;

public class Mult implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a * b);
        return 0;
    }

    @Override
    public String getRep() {
        return "*";   
    }

}
