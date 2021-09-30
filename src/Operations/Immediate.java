package Operations;
import java.util.Stack;

public class Immediate extends Operation {

    public static final String TOKEN = "";
    private int value;

    public Immediate(int value) {
        this.value = value;
    }

    @Override
    public int execute(Stack<Integer> s) {
        s.push(value);
        return 0;
    }

    @Override
    public String getRep() {
        return "" + value;
    }
}
