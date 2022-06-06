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

    public void add(int value) {
        if (!this.elements.isEmpty() && value > this.elements.peek()) {
            throw new GameException("As peças menores sempre ficam acima das maiores.");
        }
        this.elements.push(value);
    }

    public Integer remove() {
        return this.elements.pop();
    }

    public int peek() {
        return elements.peek();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public void show() {
        System.out.println(name);
        List<Integer> list = elements.toArray();

        System.out.print(">> ");

        for (int i = 0; i < list.size(); i++) {
            System.out.print("[");
            System.out.print(list.get(i));
            System.out.print("]");
        }

        System.out.println();
    }

    private void printBaseline() {
        List<Integer> list = elements.toArray();

        int size = this.name.length();
        if (!list.isEmpty()) {
            int greater = list.get(0) * 2;
            if (greater > size) {
                size = greater;
            }
        }

        size += 2;

        for (int i = 0; i < size; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        this.printSpaces((size - this.name.length())/2);
    }

    private void printSpaces(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }

    private void printBlock(int size) {
        System.out.print("[");
        for (int i = 0; i < size * 2; i++) {
            System.out.print("#");
        }
        System.out.print("]");
    }

    private void printBlocks(){
        List<Integer> list = elements.toArray();

        if (list.isEmpty()) {
            return;
        }

        int greater = list.get(0);

        for (int i = list.size() - 1; i >= 0; i--) {
            int block = list.get(i);
            this.printSpaces(greater - block);
            this.printBlock(block);
            System.out.println();
        }
    }

    public void show2() {
        this.printBlocks();
        this.printBaseline();
        System.out.println(this.name + "\n");
    }

    public Tower clone() {
        Tower result = new Tower(this.name);
        List<Integer> elements = this.elements.toArray();

        for (int i = 0; i < elements.size(); i++) {
            result.add(elements.get(i));
        }
        
        return result;
    }

    public boolean matches(Tower other) {
        List<Integer> thisStack = this.elements.toArray();
        List<Integer> thatStack = other.elements.toArray();

        if (thisStack.size() != thatStack.size()) {
            return false;
        }

        for (int i = 0; i < thisStack.size(); i++) {
            if (thisStack.get(i) != thatStack.get(i)) {
                return false;
            }
        }

        return true;
    }
}
