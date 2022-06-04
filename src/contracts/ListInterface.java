package contracts;

public interface ListInterface <Type> {
    void addFirst(Type value);
    void addLast(Type value);
    void addAfter(Type value, int id);

    Type peekFirst();
    Type peekLast();

    Type search(int id);

    Type removeFirst();
    Type removeLast();
    Type remove(int id);

    void show();
}
