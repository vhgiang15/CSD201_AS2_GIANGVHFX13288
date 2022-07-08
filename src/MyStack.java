public class MyStack<T> {
    public Node<T> head;

    public MyStack() {
        this.head=null;
    }
    public boolean isEmpty(){
        return this.head==null;
    }
    public void push(T i){
        if(isEmpty()) { this.head=new Node<T>(i);}
        else{
            this.head=new Node<T>(i,this.head);
        }
    }

    public Node<T> pop() {
        Node<T> result=this.head;
        this.head= this.head.getNextNode();
        return result;
    }
    public String toString(){
        String result="";
        while (!this.isEmpty()) {
            result+=this.pop().getInfo();
        }
        return result;
    }


}
