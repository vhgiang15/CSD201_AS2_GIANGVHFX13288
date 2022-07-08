public class MyList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public MyList() {
    }

    public MyList(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }
    public boolean isEmpty() {
        return this.head==null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void clear(){
        this.head=null;
        this.tail=null;
        this.length=0;
    }
    public void insertAtTail(T info){
        Node<T> newNode= new Node<T>(info);
        if(this.isEmpty()) {
            this.head=newNode;
            this.tail=newNode;
        }
        if(this.tail!=null) {
            this.tail.setNextNode(newNode);
            this.tail=newNode;
        }
        this.length++;
    }
    public void deleteAtHead(){
        this.head=this.head.getNextNode();
        this.length--;
   }
}
