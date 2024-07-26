package career.datastructure.linkedlist;

import java.util.List;

class LinkedList{
    ListNode root;

    public LinkedList(ListNode node){
        root = node;
    }

    public void addNode(ListNode node){
        ListNode temp = root;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public void removeNode(){
        ListNode temp = root,prev=temp;
        while(temp.next != null){
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
    }

    public void removeNode(int value){
        ListNode temp = root,prev=temp;
        while(temp.next != null){
            if(temp.value == value){
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
    }

    public ListNode reverseLikedList(){
        return reverseLinkedList(root);
    }

    public ListNode reverseLinkedList(ListNode root){
        ListNode prev = null, cur=root,next=null;
        while(cur != null){
            next = cur.next;
            cur.next =prev;
            prev = cur;
            cur = next;
        }
        root = prev;
        return prev;
    }

    public ListNode getKthNode(ListNode temp, int k) {
        k -= 1;
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }


    public ListNode reverseKElements(int k){
        ListNode temp =root;
        ListNode prevLast = null;
        while(temp != null){
            ListNode kThNode = getKthNode(temp,k);

            if(kThNode == null){
                if(prevLast != null){
                    prevLast.next = temp;
                }
                break;
            }
            ListNode nextNode = kThNode.next;
            kThNode.next = null;
            reverseLinkedList(temp);
            if (temp == root) {
                root = kThNode;
            } else {
                prevLast.next = kThNode;
            }
            prevLast = temp;
            temp = nextNode;
        }
        return root;
    }


    public void print(){
        print(root);
    }
    public void print(ListNode root){
        ListNode temp = root;
        System.out.println();
        while(temp != null){
            System.out.print(temp.value+" ");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(new ListNode(-1));
        for(int i=1;i<=10;i++) {
            linkedList.addNode(new ListNode(i));
        }

        linkedList.print();

        linkedList.removeNode();
        linkedList.removeNode(6);
        linkedList.print();

        ListNode root = linkedList.reverseLikedList();
        linkedList.print(root);
        linkedList.print();

        LinkedList linkedList1 = new LinkedList(new ListNode(-1));
        for(int i=1;i<=10;i++) {
            linkedList1.addNode(new ListNode(i));
        }

        root = linkedList1.reverseKElements(2);
        linkedList1.print(root);
        linkedList1.print();


    }
}
