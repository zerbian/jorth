import java.util.Stack;

public class End implements Operation {

    public Object reference = null;

    @Override
    public int execute(Stack<Integer> s) {
        return 0;
    }

    @Override
    public String getRep() {
        return "end";
    }

    public Object getReference() {
        return reference;
    }
}