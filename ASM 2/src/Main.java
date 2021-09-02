import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static LinkedList<Product> list = new LinkedList<Product>();
    public static MyStack<Product> stack= new MyStack<>();
    public static MyQueue<Product> queue= new MyQueue<>();
    public static void main(String[] args) {

        int choose;
        Scanner scan = new Scanner(System.in);

        //Menu chọn
        do {
            showMenu();
            System.out.print("Choose number from menu:");
            choose = scan.nextInt();
            switch (choose) {
                case 1:
                    //Lấy data từ file txt rồi add vào linked list
                    OperationtoProduct.getAllItemsFromFile(list);

                    //Thể hiện danh sách linked list
                    OperationtoProduct.displayAll(list);
                    break;
                case 2:

                    //Hàm tạo 1 node mới với info nhập từ user
                    Node<Product> a= new Node<Product>(OperationtoProduct.createProduct());

                    //Add new node to linked list
                    OperationtoProduct.addLast(a,list);
                    break;
                case 3:
                    //Thể hiện danh sách linked list mới sau khi add
                    OperationtoProduct.displayAll(list);
                    break;
                case 4:
                    //Ghi linked list vào file txt
                    OperationtoProduct.writeAllItemsToFile(list);
                    break;
                case 5:
                    //Tìm node trong linked list theo ID
                    OperationtoProduct.searchByID(list);
                    break;
                case 6:
                    //Xóa 1 node trong linked list theo ID
                    OperationtoProduct.deleteByID(list);

                    //Thể hiện linked list sau khi xóa
                    OperationtoProduct.displayAll(list);

                    //Ghi linked list mới vào file txt gốc
                    //OperationtoProduct.writeAllItemsToFile(list);
                    break;
                case 7:

                    //Sắp xếp linked list theo ID
                    //OperationtoProduct.sortByCode(list);
                    OperationtoProduct.sortByID(list);

                    break;
                case 8:

                    //Chuyển quantity của 1st node sang binary dùng recursion
                    Node<Product> currentNode= list.head;
                    int i= currentNode.data.quantity;
                    System.out.println("So nhi phan là:");
                    System.out.println(OperationtoProduct.Convert_Binary(i));

                    break;
                case 9:
                    //Xuất data từ file vào stack và in ra màn hình
                    OperationtoProduct.getAllItemsFromFile(stack);
                    OperationtoProduct.StackDisplay(stack);
                    break;
                case 10:
                    //Xuất data từ file vào queue và in ra màn hình
                    OperationtoProduct.getAllItemsFromFile(queue);
                    OperationtoProduct.QueueDisplay(queue);
                    break;
                case 11:
                    //Thoát
                    System.out.println("Goodbye!!!");
                    break;
                default:
                    System.out.println("Nhap sai!!!!");
                    break;
            }
        } while (choose != 11);
    }


    public static void showMenu(){

        System.out.println("---------------Choose one of this options:-----------------");
        System.out.println("Product list:");
        System.out.println("1. Load data from file and display");
        System.out.println("2. Input & add to the end.");
        System.out.println("3.Display data");
        System.out.println("4.Save product list to file.");
        System.out.println("5. Search by ID");
        System.out.println("6.Delete by ID");
        System.out.println("7.Sort by ID.");
        System.out.println("8. Convert to Binary");
        System.out.println("9. Load to stack and display");
        System.out.println("10. Load to queue and display.");
        System.out.println("11. Exit");
    }



}
