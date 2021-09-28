import java.util.Stack;

public class Immediate implements Operation {

    private int value;

    public Immediate(int value) {
        this.value = value;
    }

    @Override
    public int execute(Stack<Integer> s) {
        s.push(value);
        return 0;
    }
        
}
