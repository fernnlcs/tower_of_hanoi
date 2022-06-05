package contracts;

public interface StackInterface<Type> {
    // TODO adicionar exceções às interfaces e classes
    void push(Type value);
    Type pop();

    Type peek();

    boolean isEmpty();

    void show();
}
