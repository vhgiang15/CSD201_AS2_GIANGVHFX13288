import java.io.*;
import java.util.Scanner;

public class OperationToProduct {
    /**
     * Tạo thông tin một sản phẩm mới với ID không bị trùng nhau.
     * @param myList DS list hiện tại
     * @return The product
     */

    public Product createProduct(MyList<Product> myList) {
        boolean checkID;
        String bcode="";
        do {
            System.out.print("Input new ID: ");
            bcode = new Scanner(System.in).next();
            checkID=this.checkBcode(myList,bcode);
        }while (checkID); // nếu ID đã tồn tại rồi thì yêu cầu nhập lại ID
        System.out.print("Input Product's Name: ");
        String title=new Scanner(System.in).nextLine();
        int quantity=0;
        double price=0;
        boolean check=true;
        do{
            try{
                System.out.print("Input Product's quantity:");
                quantity=new Scanner(System.in).nextInt();
                check=true;
            }catch (Exception e) {
                System.out.println("Input Product's quantity is not correct");
                check=false;
            }
        }while(!check);

        do{
            try{
                System.out.print("Input Product's price:");
                price=new Scanner(System.in).nextDouble();
                check=true;

            }catch (Exception e) {
                System.out.println("Input Product's price is not correct");
                check=false;
            }
        }while(!check);
        Product newProduct=new Product(bcode.toUpperCase(),title.toUpperCase(),quantity,price);
        return newProduct;
    }

    /**
     * Đọc thông tin sản phẩm từ file và lưu vào phía cuối của DS Linklist
     * @param fileName Đường dẫn file cần đọc
     * @param mylist    DS Linklist để lưu thông tin sản phẩm vào
     */

    public void getAllItemsFromFile(String fileName, MyList<Product> mylist) {
        mylist.clear();
        try{
            FileReader fr=new FileReader(fileName);
            BufferedReader br=new BufferedReader(fr);
            String s=br.readLine();
            while (s!=null) {
                String[] temp=s.trim().split("\\|");   // Tách chuỗi theo ký tự | khi đọc từ file
                String bcode=temp[0].toUpperCase();
                String title=temp[1].toUpperCase();
                int quantity=Integer.parseInt(temp[2]);
                double price=Double.parseDouble(temp[3]);
                Product p=new Product(bcode,title,quantity,price);
                mylist.insertAtTail(p);
                s= br.readLine();
            }
            br.close();
            fr.close();
            System.out.println("Successfully!");
        }catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Đọc thông tin sản phẩm trong fileName và lưu vào Stack
     * @param fileName File lưu trữ thông tin sản phẩm
     * @param myStack     Stack lưu thông tin sau khi đọc file
     */

    public void getAllItemsFromFile(String fileName, MyStack<String> myStack) {
        try{
            FileReader fr= new FileReader(fileName);
            BufferedReader br=new BufferedReader(fr);
            String s=br.readLine();
            while (s!=null) {
                myStack.push(s);
                s= br.readLine();
            }
        }catch (Exception e) {
            System.out.println("Error");
        }
    }
    /**
     * Hiển thị thông tin sản phẩm có trong Stack
     * @param myStack     Stack chứa thông tin sản phẩm
     */

    public void displayStack(MyStack<String> myStack){
        System.out.printf("%-10s%-15s%10s%10s\n","ID", "TITLE","QUANTITY","PRICE");
        while (!myStack.isEmpty()){
            String s=myStack.pop().getInfo();
            String[] temp= s.split("\\|");
            System.out.printf("%-10s%-15s%10s%10s\n",temp[0], temp[1],temp[2],temp[3]);
        }
    }

    /**
     * Đọc thông tin sản phẩm trong fileName và lưu vào Stack
     * @param fileName File lưu trữ thông tin sản phẩm
     * @param myQueue     Queue lưu thông tin sau khi đọc file
     */

    public void getAllItemsFromFile(String fileName, MyQueue<String> myQueue) {
        try {
            FileReader fr=new FileReader(fileName);
            BufferedReader br=new BufferedReader(fr);
            String s=br.readLine();
            while (s!=null){
                myQueue.enqueue(s);
                s=br.readLine();
            }
        }catch (Exception e) {
            System.out.println("Error");
        }
    }


    /**
     * Hiển thị thông tin sản phẩm có trong Queue
     * @param myQueue     Queue chứa thông tin sản phẩm
     */

    public void displayQueue(MyQueue<String> myQueue){
        System.out.printf("%-10s%-15s%10s%10s\n","ID", "TITLE","QUANTITY","PRICE");
        while (!myQueue.isEmpTy()){
            String s=myQueue.dequeue().getInfo();
            String[] temp=s.split("\\|");
            System.out.printf("%-10s%-15s%10s%10s\n",temp[0], temp[1],temp[2],temp[3]);
        }

    }

    /**

     * Thêm mới sản phẩm nhập từ bàn phím vào Linklist
     * @param list DS Linklist
     */

    public void addLast(MyList<Product> list) {
        list.insertAtTail(this.createProduct(list));
    }


    /**
     * Hiển thị thông tin các sản phẩm có trong LinkList
     * @param list DS linklist
     */

    public void displayAll(MyList<Product> list) {
        if(list.getHead()==null) {
            System.out.println("Nothing is exist in List");
            return;
        }
        Node<Product> current=list.getHead();
        System.out.printf("%-10s%-15s%10s%10s\n","ID", "TITLE","QUANTITY","PRICE");
        while (current!=null){
            current.getInfo().display();
            current=current.getNextNode();
        }
    }

    /**
     * Ghi thông tin sản phẩm trong Linklist vào file
     * @param fileName file lưu thông tin sản phẩm
     * @param list     Input Linked list
     */

    public void writeAllItemsToFile(String fileName, MyList<Product> list) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw=new BufferedWriter(fw);
            Node<Product> current=list.getHead();
            while (current!=null) {
                Product p=current.getInfo();
                String temp=p.getBcode()+"|"+p.getTitle()+"|"+p.getQuantity()+"|"+p.getPrice();
                bw.write(temp);
                bw.newLine();
                current=current.getNextNode();
            }
            bw.close();
            fw.close();
            System.out.println("Successfully!");
        }catch (Exception e) {
            System.out.println("Error");
        }

    }

    /**
     * Tìm kiếm sản phẩm theo ID nhập từ bàn phím
     * @param list DS linklist
     */

    public void searchByCode(MyList<Product> list) {
        System.out.print("Input the ID to search:");
        String bcode=new Scanner(System.in).next();
        bcode=bcode.toUpperCase();
        Node<Product> current= list.getHead();
        while (current!=null){
            if(current.getInfo().getBcode().equals(bcode)){
                break;
            }
            current=current.getNextNode();
        }
        if(current!=null) {
            System.out.println("Result: ");
            System.out.printf("%-10s%-15s%10s%10s\n","ID", "TITLE","QUANTITY","PRICE");
            current.getInfo().display();
        } else System.out.println("Result: -1");

    }


    /**
     * Xóa sản phẩm được tìm thấy đầu tiên trong DS linklist theo ID với ID được nhập từ bàn phím
     * @param list DS linklist
     */

    public void deleteByCode(MyList<Product> list) {
        System.out.print("Input the bcode to delete: ");
        String bcode=new Scanner(System.in).next();
        bcode=bcode.toUpperCase();
        if(list.isEmpty()) {
            System.out.println("Your product is Empty");
            return;
        }
        if(list.getHead().getInfo().getBcode().equals(bcode)) {
            list.deleteAtHead();
            System.out.println("Deleted!");
        } else{
            Node<Product> current=list.getHead();
            while (current.getNextNode()!=null){
                if(current.getNextNode().getInfo().getBcode().equals(bcode)) {
                    current.setNextNode(current.getNextNode().getNextNode());
                    System.out.println("Deleted!");
                    break;
                }
                current=current.getNextNode();
            }
        }

    }

    /**
     * Sắp xếp sản phẩm trong DS linklist theo mã ID bằng QuickSorted
     * @param myList The Linked list
     * @param left index bên trái nhỏ nhất của Danh sách đang sắp xếp
     * @param right index bên phải lớn nhất của Danh sách đang sắp xếp
     */
    public void quickSortedByID(MyList<Product> myList, int left, int right) {
        if(left>right) {return;}
        int i=left;
        int j=right;
        String pivot=this.getBocdeIndex(myList,(left+right)/2);
        do{
            while (this.getBocdeIndex(myList,i).compareTo(pivot)<0) {i++;} //<
            while (this.getBocdeIndex(myList,j).compareTo(pivot)>0) {j--;}
            if(i<=j)
            {
                Node<Product> nodeI=this.getNodeIndex(myList,i);
                Node<Product> nodeJ=this.getNodeIndex(myList,j);
                this.swapNodeInfo(nodeI, nodeJ);
                i++;
                j--;
            }
        }while(i<j);
        quickSortedByID(myList,left,j);
        quickSortedByID(myList,i,right);

    }

    /**
     * Hàm chuyển đổi số thập phân sang nhị phân
     * @param i Số thập phân cần chuyển đổi sang nhị phân
     * @param myStack  Stack để lưu thông tin
     */

    public void convertToBinary(int i,MyStack<Integer> myStack) {
        if(i==0) {return ;}
        else {myStack.push(i%2);
            i=i/2;
            convertToBinary(i,myStack);
        }
    }
    /**
     * Hàm Swap nội dung của 2 Node
     * @param a Node a
     * @param b Node b
     */

    public void swapNodeInfo(Node<Product> a,Node<Product> b)
    {
        Product temp=a.getInfo();
        a.setInfo(b.getInfo());
        b.setInfo(temp);
    }
    /**
     * Hàm lấy ID của Node theo index trong List
     * @param myList List chứa thông tin sản phẩm
     * @param index chỉ số index của sản phẩm cần lấy thông tin ID
     * @return ID của Product
     */

    public String getBocdeIndex(MyList<Product> myList, int index){
        String result=null;
        if(myList.getLength()<index) { return null;}
        else {
            Node<Product> current=myList.getHead();
            for (int i = 0; i <index; i++) {
                current=current.getNextNode();
            }
            result=current.getInfo().getBcode();
        }
        return result;
    }
    /**
     * Hàm lấy Node của List theo index
     * @param myList List chứa thông tin sản phẩm
     * @param index chỉ số index của Node cần lấy thông tin Node
     * @return Node của List
     */

    public Node<Product> getNodeIndex(MyList<Product> myList, int index){
        Node<Product> result=null;
        if(myList.getLength()<index) { return null;}
        else {
            Node<Product> current=myList.getHead();
            for (int i = 0; i <index; i++) {
                current=current.getNextNode();
            }
            result=current;
        }
        return result;

    }
    /**
     * Check bcode nhập từ bàn phím để xét mã ID đã tổn tại chưa
     * @param myList List chứa thông tin sản phẩm
     * @param bcode chỉ số index của Node cần lấy thông tin Node
     * @return true nếu bcode tồn tại trong list, ngược lại trả về false
     */

    public boolean checkBcode(MyList<Product> myList, String bcode) {
        boolean result=false;
        Node<Product> current=myList.getHead();
        while (current!=null) {
            if(current.getInfo().getBcode().equals(bcode.toUpperCase())) {
                System.out.println("ID product was existent");
                result=true;
                break;
            }
            current=current.getNextNode();
        }
        return  result;

    }

}

