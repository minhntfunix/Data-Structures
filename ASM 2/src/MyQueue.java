public class MyQueue<T> {
    public Node <T> front;
    public Node <T> rear;

    public MyQueue() {
    }

    public MyQueue(Node<T> front, Node<T> rear) {
        this.front = front;
        this.rear = rear;
    }
}
