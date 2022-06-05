package game;

import java.util.List;

import structures.Stack;
import utils.GameException;

public class Tower {
    
    public String name;
    private Stack<Integer> elements = new Stack<Integer>();

    public Tower(String name) {
        this.name = name;
    }

    public void add(Integer value) {
        if (!this.elements.isEmpty() || value > this.elements.peek()) {
            throw new GameException("As peças menores sempre ficam acima das maiores.");
        }
        this.elements.push(value);
    }

    public Integer remove() {
        return this.elements.pop();
    }

    public void show() {
        System.out.println(name);
        List<Integer> list = elements.toArray();

        System.out.print("> ");

        for (int i = 0; i < list.size(); i++) {
            System.out.print("[");
            System.out.print(list.get(i));
            System.out.print("]");
        }

        System.out.println();
    }
}
