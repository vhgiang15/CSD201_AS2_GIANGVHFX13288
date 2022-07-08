public class MyQueue <T>{
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head=null;
        this.tail=null;
    }
    public boolean isEmpTy() {
        return this.head==null;
    }
    public void enqueue(T info){
        Node<T> newNode=new Node<T>(info);
        if(this.isEmpTy()) {this.tail=newNode; this.head=newNode;}
        else {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
    }
    public  Node<T> dequeue(){
        Node<T> result=null;
        if(isEmpTy()) {return  null;}
        else {
            result=this.head;
            this.head=this.head.getNextNode();
        }
        return result;
    }

}
