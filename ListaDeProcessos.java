public class ListaDeProcessos{
    private static class Node{
        Process data;
        Node next;

        Node(Process data){
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private Node tail;

    public ListaDeProcessos(){
        head = null;
        tail = null;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public void addToEnd(Process P){
        Node newNode = new Node(P);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }
    public Process removeFromFront(){
        if(isEmpty()){
            return null;
        }
        Process p = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return p;
    }
    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null){
            sb.append("{").append(current.data.getId()).append(":").append(current.data.getNome()).append("}");
            if(current.next != null){
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}