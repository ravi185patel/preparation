package career.datastructure.linkedlist;

public class ListNode {
    int value;
    ListNode next;

    public ListNode(int value){
        this.value= value;
        next = null;
    }
    public ListNode(int value,ListNode node){
        this(value);
        this.next = node;
    }
}
