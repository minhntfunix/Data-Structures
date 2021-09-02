import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Stack;

public class OperationtoProduct {

    //Hàm xuất data từ file và lưu vào linked list
    public static void getAllItemsFromFile(LinkedList<Product> list){

            File file= new File ("Data.txt");
            String line;
            //Clear Linked list
            clear(list);
            try {
                BufferedReader br= new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null ){
                    String[] a = line.split(",");
                    int b=Integer.parseInt(a[2]);
                    double c= Double.parseDouble(a[3]);
                    Node<Product> new1= new Node<Product>(new Product(a[0],a[1],b,c));

                    //Dùng hàm tự tạo để add phần tử vào linked list
                    addLast(new1,list);
                }

                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }



    /*
    public void insertAtHead (Node <T> newNode){

        newNode.setNext(this.head);
        if (this.head!=null){
            this.head.setPrev(newNode);
            length++;
        }
        this.head= newNode;
        length++;
    }

     */


    //Hàm add new node vào cuối linked list
    public static void addLast(Node<Product> newNode, LinkedList<Product> list){

        //If list is empty
        if(list.head == null) {
            //Both head and tail will point to newNode
            list.head = list.tail = newNode;
            //head's previous will point to null
            list.head.prev = null;
            //tail's next will point to null, as it is the last node of the list
            list.tail.next = null;
            list.length++;
        }
        else {
            //newNode will be added after tail such that tail's next will point to newNode
            list.tail.next =  newNode;
            //newNode's previous will point to tail
            newNode.prev = list.tail;
            //newNode will become new tail
            list.tail = newNode;
            //As it is last node, tail's next will point to null
            list.tail.next = null;
            list.length++;
        }
    }


    //Hàm in linked list ra console
    public static void displayAll(LinkedList<Product> list){
        System.out.println("Danh sách linked list: ");
        Node <Product> currentNode = list.head;
        while (currentNode != null) {
            currentNode.PrintOut();
            currentNode = currentNode.next;
        }


    }

    //User nhập info để tạo ra Product mới
    public static Product createProduct(){
        Scanner input= new Scanner (System.in);
        System.out.println("Nhap ID: ");
        String ID= input.next();
        System.out.println("Nhap tên: ");
        String title= input.next();
        System.out.println("Nhap quantity: ");
        int q= input.nextInt();
        System.out.println("Nhap price: ");
        double p= input.nextDouble();
        return new Product(ID,title,q,p);
    }

    //Ghi linked list vào file txt
    public static void writeAllItemsToFile(LinkedList<Product> list){
        System.out.println("Danh sách sau khi ghi: ");
        File file= new File("Data.txt");

        Node <Product> currentNode = list.head;
        try {
            PrintStream write= new PrintStream(new FileOutputStream(file));
            while (currentNode != null) {
                currentNode.PrintOut();
                write.println(currentNode.data.ID+","+currentNode.data.title+","+currentNode.data.quantity+","+currentNode.data.price );

                currentNode = currentNode.next;
            }

            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Tìm node trong linked list theo ID
    public static void searchByID(LinkedList<Product> list){
        System.out.println(" Tìm phần tử có ID : ");
        Node <Product> currentNode = list.head;
        Scanner input= new Scanner(System.in);
        String search= input.next();
        boolean x= false;
        while (currentNode != null){
            if (currentNode.data.ID.contains(search)) {
                x = true;
                currentNode.PrintOut();
            }
            currentNode = currentNode.next;
        }


        //Nếu ko tìm dc ID
        if (!x){
        System.out.println(-1);
        }
    }


    //Xóa node theo ID
    public static void deleteByID(LinkedList<Product> list){
        System.out.println(" Nhập ID để xóa : ");
        Node <Product> currentNode = list.head;
        Scanner input= new Scanner(System.in);
        String search= input.next();
        boolean x= false;
        while (currentNode != null){
            if (currentNode.data.ID.equals(search)) {
                if (list.head == null) {
                    return;
                }
                if (list.head == currentNode) {
                    list.head = currentNode.next;
                }

                // Change next only if node to be deleted
                // is NOT the last node
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }

                // Change prev only if node to be deleted
                // is NOT the first node
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                System.out.println("Success");
                x=true;
                //currentNode.PrintOut();
            }
            currentNode = currentNode.next;
        }

        //Nếu ko có ID để xóa
        if (!x){
            System.out.println("ID này không tồn tại");
        }

    }





    // Sort by ID, ko dùng trong main
    public static void sortByCode(LinkedList<Product> list){
        Node <Product> current = null;
        Node <Product> index = null;
        Node <Product> temp = new Node<Product>(new Product("","",0,0));

        if(list.head == null) {
            return;
        }
        else {
            //Current will point to head
            for(current = list.head; current.next != null; current = current.next) {
                //Index will point to node next to current
                for(index = current.next; index != null; index = index.next) {
                    //If current's data is greater than index's data, swap the data of current and index
                    if(current.data.ID.compareTo(index.data.ID) >0) {
                        temp.data = current.data;
                        current.data = index.data;
                        index.data = temp.data;
                    }
                }
            }
        }
        displayAll(list);
    }




    //Sort node theo ID theo phuong pháp ArrayList
    public static void sortByID(LinkedList<Product> list){
        Node <Product> currentNode= list.head;
        ArrayList<Product> list2= new ArrayList<Product>();
        //Add linked list vào ArrayList
        while (currentNode != null) {
            list2.add(currentNode.data);
            currentNode = currentNode.next;
        }


        //Sắp xếp ArrayList theo ID
        Collections.sort(list2, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.ID.compareTo(o2.ID);
            }
        });



        //Xóa linked list cũ
        clear(list);

        //Add linked list mới
        for (int i=0; i<list2.size(); i++){
            Node<Product> new1= new Node<Product>(list2.get(i));
            addLast(new1,list);
        }
        //Display linked list mới
        System.out.println("Linked list sau khi sap xep");
        displayAll(list);
    }

    //Hàm xóa linked list
    public static void clear(LinkedList<Product> list){
        //Node <Product> currentNode= list.head;
        //while (currentNode !=null){
            //currentNode=currentNode.next;
            //currentNode=null;
            ;
        //}
        list.head=null;
    }

    // Convert  to binary

    public  static int Convert_Binary( int i){

        if (i == 0)
            return 0;
        else
            return i % 2 + 10 * Convert_Binary(i / 2);

    }

    //Hàm xuất data từ file và lưu vào stack
    public static void getAllItemsFromFile( MyStack<Product>  stack){
        File file= new File ("Data.txt");
        String line;

        //Clear stack để ko bị nhập data lần 2
        stack.top=null;
        try {
            BufferedReader br= new BufferedReader(new FileReader(file));
            int i=0;
            while ((line = br.readLine()) != null ){
                String[] a = line.split(",");
                int b=Integer.parseInt(a[2]);
                double c= Double.parseDouble(a[3]);
                Node<Product> new1= new Node<Product>(new Product(a[0],a[1],b,c));

                //Hàm add phần tử vào stack
                StackPush(new1,stack);
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Hàm thêm node vào stack
    public static void StackPush(Node<Product> newNode, MyStack<Product> stack){

        Node<Product> temp= new Node<>();
        // check if stack (heap) is full. Then inserting an
        //  element would lead to stack overflow
        if (temp == null) {
            System.out.print("\nHeap Overflow");
            return;
        }
        // initialize data into temp data field
        temp.data = newNode.data;

        // put top reference into temp link
        temp.next = stack.top;

        // update top reference
        stack.top = temp;

    }






    public static void clear(MyStack<Product> stack){

        stack.top=null;
    }


    //Hàm in Stack ra console
    public static void StackDisplay(MyStack<Product>  stack){
        // check for stack underflow
        if (stack.top == null) {
            System.out.printf("\nStack Underflow");
            return;
        }
        else {
            Node<Product> temp = stack.top;
            while (temp != null) {
                // print node data
                temp.PrintOut();
                // assign temp link to temp
                temp = temp.next;
            }
        }
    }



    public boolean isEmpty(MyStack<Product>  stack)
    {
        return stack.top == null;
    }


    //Hàm add note vào queue
    public static void Enqueue(Node<Product> newNode, MyQueue<Product> queue){

        // If queue is empty, then new node is front and rear both
        if (queue.rear == null) {
            queue.front = queue.rear = newNode;
            return;
        }

        // Add the new node at the end of queue and change rear
        queue.rear.next = newNode;
        queue.rear = newNode;
    }




    //Hàm lưu data từ file txt vào Queue
    public static void getAllItemsFromFile(MyQueue<Product>queue){

        File file= new File ("Data.txt");
        String line;
        queue.front=null;
        queue.rear=null;
        try {
            BufferedReader br= new BufferedReader(new FileReader(file));
            int i=0;
            while ((line = br.readLine()) != null ){
                String[] a = line.split(",");
                int b=Integer.parseInt(a[2]);
                double c= Double.parseDouble(a[3]);
                Node<Product> new1= new Node<Product>(new Product(a[0],a[1],b,c));
                Enqueue(new1,queue);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Hàm display queue ra console
    public static void QueueDisplay(MyQueue<Product> queue){
        if (queue.front == null) {
            System.out.printf("\nStack Underflow");
            return;
        }
        else {
            Node<Product> temp = queue.front;
            while (temp != null) {
                // print node data
                temp.PrintOut();
                // assign temp link to temp
                temp = temp.next;
            }
        }
    }

}




