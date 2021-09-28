import java.util.Stack;

public class Dump implements Operation {

    @Override
    public int execute(Stack<Integer> s) {
        System.out.println(s.pop());
        return 0;        
    }
    
}
