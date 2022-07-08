public class Node<T> {
    private T info;
    private Node<T> nextNode;

    public Node(T info) {
        this.info = info;
    }

    public Node(T info, Node<T> nextNode) {
        this.info = info;
        this.nextNode = nextNode;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
