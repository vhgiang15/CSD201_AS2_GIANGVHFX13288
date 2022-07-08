import java.util.Scanner;

public class AS2Main {
    //public static MyList<Product> mylist=new MyList<Product>();
    public static String filename="data.txt";
    public static void main(String[] args) {
        boolean tieptuc=true;
        MyList<Product> mylist=new MyList<Product>();
        do{
            System.out.println();
            System.out.println("Product list:");
            System.out.println("1.Load data from file and display");
            System.out.println("2.Input & add to the end.");
            System.out.println("3.Display data");
            System.out.println("4.Save product list to file.");
            System.out.println("5.Search by ID");
            System.out.println("6.Delete by ID");
            System.out.println("7.Sort by ID.");
            System.out.println("8.Convert to Binary");
            System.out.println("9.Load to stack and display");
            System.out.println("10.Load to queue and display.");
            System.out.println("0. Exit");
            OperationToProduct OP=new OperationToProduct();
            int n=0;
            boolean hople=true;
            do {                    //kiểm tra tính hợp lệ của việc lựa chọn chức năng - số nhập vào phải là số nguyên
                try {
                    System.out.print("Choice= ");
                    n = new Scanner(System.in).nextInt();
                    hople=true;
                }catch (Exception e) {
                    System.out.println("Your choice is not correct");
                    hople=false;
                }
            }while (!hople);
            switch(n) {
                case 1:
                    OP.getAllItemsFromFile(filename,mylist);
                    System.out.println(mylist.getLength());
                    break;
                case 2:
                    OP.addLast(mylist);
                    break;
                case 3:
                    OP.displayAll(mylist);
                    break;
                case 4:
                    OP.writeAllItemsToFile(filename,mylist);
                    break;
                case 5:
                    OP.searchByCode(mylist);
                    break;
                case 6:
                    OP.deleteByCode(mylist);
                    break;
                case 7:
                    OP.quickSortedByID(mylist,0, mylist.getLength()-1);
                    System.out.println("Successfully!");
                    break;
                case 8:
                    int quantity=mylist.getHead().getInfo().getQuantity();
                    MyStack<Integer> myStack=new MyStack<Integer>();
                    OP.convertToBinary(quantity,myStack);
                    System.out.println("Quantity="+quantity+"=>("+myStack.toString()+")");
                    break;
                case 9:
                    MyStack<String> myStack1=new MyStack<String>();
                    OP.getAllItemsFromFile(filename,myStack1);
                    OP.displayStack(myStack1);
                    break;
                case 10:
                    MyQueue<String> myQueue= new MyQueue<String>();
                    OP.getAllItemsFromFile(filename,myQueue);
                    OP.displayQueue(myQueue);
                    break;
                case 0:
                    tieptuc=false;
                    break;
                default:
                    System.out.println("Khong co chuc nang ban da chon");
                    break;
            }
        }while(tieptuc);
    }
}
