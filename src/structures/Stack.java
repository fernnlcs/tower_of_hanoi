package structures;

import contracts.StackInterface;

public class Stack<Type> implements StackInterface<Type> {

    SinglyLinkedList<Type> list = new SinglyLinkedList<Type>();

    @Override
    public void push(Type value) {
        this.list.addFirst(value);;
    }

    @Override
    public Type pop() {
        return this.list.removeFirst();
    }

    @Override
    public Type peek() {
        return this.list.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return (list.size == 0);
    }

    @Override
    public void show() {
        this.list.show();
    }
    
}
