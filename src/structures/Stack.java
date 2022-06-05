package structures;

import contracts.StackInterface;

public class Stack<Type> implements StackInterface<Type> {

    private DoubleLinkedList<Type> list = new DoubleLinkedList<Type>();

    @Override
    public void push(Type value) {
        this.list.addLast(value);;
    }

    @Override
    public Type pop() {
        return this.list.removeLast();
    }

    @Override
    public Type peek() {
        return this.list.peekLast();
    }

    @Override
    public boolean isEmpty() {
        return (this.list.size == 0);
    }

    @Override
    public void show() {
        this.list.show();
    }
    
    public Object[] toArray() {
        return this.list.toArray();
    }
}
