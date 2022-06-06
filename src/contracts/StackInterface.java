package contracts;

public interface StackInterface<Type> {
    void push(Type value);
    Type pop();

    Type peek();

    boolean isEmpty();

    void show();
}
