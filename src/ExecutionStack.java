import java.util.Stack;

public class ExecutionStack { 
    private Node head = null;
    private Node tail = null;
    private Node opRef = null;

    private Stack<Node> loopStack = new Stack<>();

    int ifEndLevel = 0;

    private class Node {
        private Operation value;
        private Node next = null;

        Node(Operation value) {
            this.value = value;
        }
    }

    public void push(Operation a) {
        if (head == null) {
            head = tail = new Node(a);
        } else {
            tail.next = new Node(a);
            tail = tail.next;
        }
    }

    // public Operation pop() {
    //     Operation value = tail.value;
    //     tail = tail.prev;
    //     tail.next = null;
    //     return value;
    // }

    @Override
    public String toString() {
        String s = "";
        Node pointer = head;
        while(pointer != null) {
            s += pointer.value.getRep() + " ";
            pointer = pointer.next;
        }
        return s;
    }

    public void execute() {
        Stack<Integer> stack = new Stack<>();
        opRef = head;
        while (opRef != null) {
            int t = opRef.value.execute(stack);
            // check if we hit an IF statement
            if (opRef.value instanceof If) {
                if  (t == 0) {
                    advanceToMatchingEnd();
                } else {
                    ifEndLevel++;
                }
            } else if (opRef.value instanceof While) {
                loopStack.push(opRef); // save pointer to go back
            } else if (opRef.value instanceof Do) {
                if (t == 0) {
                    advanceToMatchingEnd();
                    loopStack.pop();
                }
            } else if (opRef.value instanceof End) {
                // check if end is an if end oder while end
                if (ifEndLevel > 0) { // if end
                    ifEndLevel--;
                } else { // while end
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
            }
            opRef = opRef.next;
        }
    }

    private void advanceToMatchingEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            if (opRef.value instanceof If || opRef.value instanceof Do) nested++;
            if (opRef.value instanceof End) nested--;
        } while (nested != 0);
    }
}
