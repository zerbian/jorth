import java.util.Stack;

public class Swap implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        int a = s.pop();
        int b = s.pop();
        s.push(a);
        s.push(b);
        return 0;
    }

    @Override
    public String getRep() {
        return "swap";   
    }
    
}
