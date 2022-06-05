package contracts;

public interface ListInterface <Type> {
    int addFirst(Type value);
    int addLast(Type value);
    int addAfter(Type value, int id);

    Type peekFirst();
    Type peekLast();

    Type search(int id);

    Type removeFirst();
    Type removeLast();
    Type remove(int id);

    void show();
}
