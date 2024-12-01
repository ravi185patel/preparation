package career.interview.google;

import java.util.PriorityQueue;

/*
sc = O(log k) ; k = no of list;
tc = O(n log k) ; n = no of nodes;
Understand :- https://takeuforward.org/linked-list/merge-k-sorted-linked-lists
https://www.naukri.com/code360/problems/merge-k-sorted-lists_992772?topList=top-google-coding-interview-questions&problemListRedirection=true&leftPanelTabValue=SUBMISSION&count=25&page=3&search=&difficulty%5B%5D=Hard&sort_entity=order&sort_order=ASC&customSource=studio_nav
 */

class LinkedListNode<Integer> {
    int data;
    LinkedListNode<Integer> next;

    public LinkedListNode(int data) {
        this.data = data;
    }
}
public class MergeKLinkedList {
    public static void main(String[] args) {

    }
    public static LinkedListNode<Integer> mergeKLists(LinkedListNode<Integer>[] listArray) {

        PriorityQueue<LinkedListNode<Integer>> pq = new PriorityQueue<>((head1, head2) -> head1.data - head2.data);

        for(LinkedListNode<Integer> linkedListNode: listArray){
            if(linkedListNode == null) continue;
            pq.add(linkedListNode);
        }

        LinkedListNode<Integer>  head = new LinkedListNode<Integer>(0);
        LinkedListNode<Integer> current = head;

        while(!pq.isEmpty()){
            LinkedListNode<Integer> smallestNode = pq.poll();
            if(smallestNode.next != null){
                pq.add(smallestNode.next);
            }
            current.next = smallestNode;
            current = current.next;
        }
        return head.next;
    }
}
