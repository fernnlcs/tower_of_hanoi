package game;

import structures.Stack;

public class Tower<Type> {
    
    public String name;
    private Stack<Type> elements = new Stack<Type>();

    public Tower(String name) {
        this.name = name;
    }

    public void add(Type value) {
        this.elements.push(value);
    }

    public Type remove() {
        return this.elements.pop();
    }

    public void show() {
        System.out.println(name);
        Object[] list = elements.toArray();

        System.out.print("> ");

        for (int i = 0; i < list.length; i++) {
            System.out.print("[");
            System.out.print(list[i]);
            System.out.print("]");
        }

        System.out.println();
    }
}
