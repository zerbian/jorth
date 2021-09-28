import java.util.Stack;

public class ExecutionStack { 
    private Node head = null;
    private Node tail = null;
    private Node opRef = null;

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

    public Operation pop() {
        Operation value = tail.value;
        tail = tail.prev;
        tail.next = null;
        return value;
    }

    public void execute() {
        Stack<Integer> stack = new Stack<>();
        opRef = head;
        while (opRef != null) {
            int t = opRef.value.execute(stack);
            // check if we hit an IF statement
            if (opRef.value instanceof IF && t != 0) {
                advanceToEnd();
            }
            opRef = opRef.next;
        }
    }

    private void advanceToEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            if (opRef.value instanceof IF) nested++;
            if (opRef.value instanceof END) nested--;
        } while (nested != 0);
    }
}
