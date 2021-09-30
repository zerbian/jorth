import Operations.*;

import java.util.Stack;

public class ExecutionStack {
    private Node head = null;
    private Node tail = null;
    private Node opRef = null;

    private final Stack<Node> loopStack = new Stack<>();

    int ifEndLevel = 0;

    private class Node {
        private final Operation value;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node pointer = head;
        while(pointer != null) {
            s.append(pointer.value.getRep()).append(" ");
            pointer = pointer.next;
        }
        return s.toString();
    }

    public void execute() {
        Stack<Integer> stack = new Stack<>();
        opRef = head;
        while (opRef != null) {
            Operation curr = opRef.value;
            int status = curr.execute(stack);
            switch (curr.getRep()) {
                case If.TOKEN:
                    if (status == 0) {
                        advanceIfElseEnd();
                    } else {
                        ifEndLevel++;
                    }
                    break;
                case Else.TOKEN:
                    advanceToMatchingEnd(); // if we hit an else we need to skip it
                    break;
                case While.TOKEN:
                    loopStack.push(opRef); // save pointer where to go back
                    break;
                case Do.TOKEN:
                    if (status == 0) {
                        advanceToMatchingEnd();
                        loopStack.pop();
                    }
                    break;
                case End.TOKEN:
                    if (ifEndLevel > 0) { // if end
                        ifEndLevel--;
                    } else { // while end
                        Object o = ((End)curr).reference;
                        if (o == null) {
                            if (!loopStack.isEmpty()) {
                                o = loopStack.peek();
                            } else {
                                assert false : "Loops should always have return-references";
                            }
                        }
                        opRef = (Node)o;
                    }
            }
            opRef = opRef != null ? opRef.next : null;
        }
    }

    private void advanceToMatchingEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            Operation o = opRef.value;
            if (o instanceof If || o instanceof Do) nested++;
            if (o instanceof End) nested--;
        } while (nested > 0);
    }

    private void advanceIfElseEnd() {
        int nested = 1;
        do {
            opRef = opRef.next;
            if (opRef.value instanceof If || opRef.value instanceof Do) nested++;
            // only check for else block, if we are in the right "nested" level
            if (nested == 1 && opRef.value instanceof Else || opRef.value instanceof End) nested--;
        } while (nested != 0);
    }
}
