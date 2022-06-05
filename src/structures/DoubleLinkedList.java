package structures;

import contracts.ListInterface;

public class DoubleLinkedList<Type> implements ListInterface<Type> {

    public Node head = null;
    public Node tail = null;
    public int nextId = 0;
    public int size = 0;

    /**
     * Node
     */
    public class Node {
    
        int id = nextId++;
        Type data;
        Node previous = null;
        Node next = null;

        public Node(Type data) {
            this.data = data;
        }
        
    }
    
    @Override
    public void addFirst(Type value) {
        Node oldHead = this.head;
        Node newHead = new Node(value);

        if (oldHead == null) {
            // Primeiro elemento de todos
            this.head = newHead;
            this.tail = newHead;
        } else {
            // Já tem outros
            newHead.next = oldHead;
            oldHead.previous = newHead;
            this.head = newHead;
        }

        // Incrementar tamanho
        this.size += 1;
    }

    @Override
    public void addLast(Type value) {
        Node oldTail = this.tail;
        Node newTail = new Node(value);

        if (oldTail == null) {
            // Primeiro elemento de todos
            this.head = newTail;
            this.tail = newTail;
        } else {
            // Já tem outros
            oldTail.next = newTail;
            newTail.previous = oldTail;
            this.tail = newTail;
        }

        // Incrementar tamanho
        this.size += 1;
    }

    @Override
    public void addAfter(Type value, int id) {
        Node target = this.searchNode(id);

        if (target == null) {
            System.out.println("Erro: não encontrei o id " + id);
            return;
        }

        Node nextAnyway = target.next;
        Node newNode = new Node(value);

        newNode.previous = target;
        newNode.next = nextAnyway;

        if (newNode.next != null) {
            // Define o novo nó como anterior do seu seguinte
            newNode.next.previous = newNode;
        }

        target.next = newNode;

        this.size += 1;
    }

    @Override
    public Type peekFirst() {
        if (this.head == null) {
            return null;
        }

        return this.head.data;
    }

    @Override
    public Type peekLast() {
        if (this.tail == null) {
            return null;
        }
        
        return this.tail.data;
    }

    private Node searchNode(int id) {
        Node current = this.head;

        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public Type search(int id) {
        Node result = this.searchNode(id);

        if (result != null) {
            return result.data;
        }

        return null;
    }

    @Override
    public Type removeFirst() {
        Node oldHead = this.head;
        
        if (oldHead == null) {
            System.out.println("Lista vazia. Nada removido.");
            return null;
        }

        Node newHead = oldHead.next;
        this.head = newHead;

        if (newHead == null) {
            // Se é o único elemento, a cauda também recebe o valor nulo
            // A cabeça já recebeu em 'this.head = newHead;'
            this.tail = newHead;
        } else {
            // Isola o item removido
            newHead.previous = null;
            oldHead.next = null;
        }

        this.size -= 1;

        return oldHead.data;
    }

    @Override
    public Type removeLast() {
        Node oldTail = this.tail;

        if (oldTail == null) {
            System.out.println("Lista vazia. Nada removido.");
            return null;
        }

        Node newTail = this.tail.previous;
        this.tail = newTail;
        
        newTail.next = null;

        if (oldTail == this.head) {
            // Se é o único elemento, a cabeça TAMBÉM recebe o valor nulo
            // A cauda já recebeu em ''
            this.head = null;

            return oldTail.data;
        }

        this.size -= 1;

        return oldTail.data;
    }

    @Override
    public Type remove(int id) {
        Node removable = this.searchNode(id);

        if (removable.id == this.head.id) {
            return this.removeFirst();
        }

        if (removable.id == this.tail.id) {
            return this.removeLast();
        }

        // Isolando item
        removable.previous.next = removable.next;
        removable.next.previous = removable.previous;

        this.size -= 1;

        return removable.data;
    }

    @Override
    public void show() {
        Node current = this.head;

        if (current == null) {
            System.out.println("Lista vazia.\n");
        } else {
            System.out.println(this.size + " itens\n");
            do {
                System.out.println("[#" + current.id + "]");
                System.out.println(current.data.toString());
                System.out.println("");

                current = current.next;
            } while (current != null);
        }
    }
    
    public void showReverse() {
        Node current = this.tail;

        if (current == null) {
            System.out.println("Lista vazia.\n");
        } else {
            System.out.println(this.size + " itens\n");
            do {
                System.out.println("[#" + current.id + "]");
                System.out.println(current.data.toString());
                System.out.println("");

                current = current.previous;
            } while (current != null);
        }
    }
    
}
