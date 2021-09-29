import java.util.Stack;

public class ExecutionStack { 
    private Node head = null;
    private Node tail = null;
    private Node opRef = null;

    private Stack<Node> loopStack = new Stack<>();

    private class Node {
        private Operation value;
        private Node prev = null;
        private Node next = null;

        Node(Operation value) {
            this.value = value;
        }

        Node(Operation value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    public void push(Operation a) {
        if (head == null) {
            head = tail = new Node(a);
        } else {
            tail.next = new Node(a, null, tail);
            tail = tail.next;
        }
    }

    // public Operation pop() {
    //     Operation value = tail.value;
    //     tail = tail.prev;
    //     tail.next = null;
    //     return value;
    // }

    public void execute() {
        Stack<Integer> stack = new Stack<>();
        opRef = head;
        while (opRef != null) {
            int t = opRef.value.execute(stack);
            // check if we hit an IF statement
            if (opRef.value instanceof If && t != 0) {
                advanceToIfEnd();
            } else if (opRef.value instanceof While) {
                loopStack.push(opRef); // save pointer to go back
            } else if (opRef.value instanceof Do) {
                if (t == 1) {
                    advanceToWhileEnd();
                    loopStack.pop();
                }
            } else if (opRef.value instanceof End) {
                Object o = ((End)(opRef.value)).getReference();
                if (o == null) {
                    if (!loopStack.isEmpty()) {
                        o = loopStack.peek();
                    } else {
                        assert false : "Loops should always have returnreferences";
                    }
                }
                opRef = (Node)o;
            }
            opRef = opRef.next;
        }
    }

    private void advanceToIfEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            if (opRef.value instanceof If) nested++;
            if (opRef.value instanceof End) nested--;
        } while (nested != 0);
    }

    private void advanceToWhileEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            if (opRef.value instanceof Do) nested++;
            if (opRef.value instanceof End) nested--;
        } while (nested != 0);
    }
}
