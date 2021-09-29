import java.util.Stack;

public class If implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        return s.pop() == 1 ? 1 : 0;
    }
    
    @Override
    public String getRep() {
        return "if";   
    }
}
