package career.interview.priceline.ll;

class Node{
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public int getVal() {
        return val;
    }

    public Node getNext() {
        return next;
    }
}
public class LinkedList {
    Node head;
    Node tail;
    int size;


    public LinkedList(){

    }

    public void add(int value){
        Node node = new Node(value);
        Node curr=head;
        while(curr != null && curr.next!=null){
            curr=curr.next;
        }
        if(head == null) {
            head = node;
        }else{
            curr.next = node;
        }
    }

    public void remove(int value){
        Node curr=head,prev=null;
        while(curr != null && curr.next!=null){
            if(curr.val == value){
                break;
            }
            prev =curr;
            curr=curr.next;
        }
        prev.next = curr.next;
    }

    public void removeAllMatchedWithGivenValue(int value){
        Node dummy = new Node(0);
        dummy.next=head;
        Node prev = dummy, curr = head;

        while (curr != null) {
            Node nxt = curr.next;
            if (curr.val == value) {
                prev.next = nxt;
            } else {
                prev = curr;
            }
            curr = nxt;
        }

        head = dummy.next;
    }

    public void removeAllMatchedWithGivenValueWithoutPrev(int value){
        Node dummy = new Node(0);
        dummy.next=head;
        Node curr = dummy;

        while (curr.next !=null) {

            if(curr.next.val == value){
                curr.next=curr.next.next;
            }else{
                curr=curr.next;
            }
        }

        head = dummy.next;
    }

    public void reverse(){
        Node curr=head,prev=null;
        while(curr != null){
            Node next = curr.next;
            curr.next =prev;
            prev = curr;
            curr=next;
        }
        head = prev;
        System.out.println("\n========after=========");
    }

    public void groupReverseK(int k) {
        int length = lengthOfLL();
        Node prev=null;
        Node currHead=head;
        for(int i=0;i<=length/k;i++){
            Node curr = currHead;
            int count=0;
            Node currentTailNode=null;
            while(curr!=null && count <=k){
                count++;
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            if(i==0){
                head = prev;
            }else{
                currHead = curr;
                currentTailNode.next = prev;
            }
        }
    }

    public int lengthOfLL(){
        int length=0;
        Node curr=head;
        while(curr != null){
            curr = curr.next;
            length++;
        }
        return length;
    }

    public int middelOfLL(){
        Node first=head,second=head.next;
        while(second != null && second.next!=null){
            first = first.next;
            second = second.next.next;
        }
        return first.val;
    }

    public void removeNthNode(int n){
        int length= lengthOfLL();
        int k = length-n;
        if(k==0){
            head = head.next;
            return;
        }
        Node curr = head, prev = null;
        for (int i = 0; i < k; i++) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr == null ? null : curr.next;
    }

    public boolean cycleDetect(){
        if(head == null) return false;
        Node slow=head,faster=head;
        while(faster != null && faster.next != null){
            slow = slow.next;
            faster = faster.next.next;
            if(slow.val == faster.val){ // remove val when compare with reference
                return true;
            }
        }
        return false;
    }

    public Node cycleDetectI(Node head){
        if(head == null) return null;
        Node slow=head,faster=head;
        while(faster != null && faster.next != null){
            slow = slow.next;
            faster = faster.next.next;
            if(slow.val == faster.val){ // remove val when compare with reference
                return slow;
            }
        }
        if(faster == null || faster.next == null) return null;
        Node curr=head;
        while(curr != slow){
            curr = curr.next;
            slow = slow.next;
        }
        return curr;
    }

    public Node mergedToLinkedList(Node head1,Node head2){
        Node temp=new Node(0);
        Node head = temp;
        while(head1 != null && head2 != null){
            if(head1.val <= head2.val){
                temp.next =head1;
                head1 = head1.next;
            }else{
                temp.next =head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        if(head1 != null){
            temp.next = head1;
        }
        if(head2 != null){
            temp.next = head2;
        }
        return head.next;
    }

    public boolean isPeliLL(Node head){
        Node faster=head,slow=head;
        while(faster != null && faster.next != null){
            faster = faster.next.next;
            slow = slow.next;
        }

        Node prev=null;
        while(slow!= null){
            Node next=slow.next;
            slow.next=prev;
            prev = slow;
            slow=next;
        }
        Node curr=head;
        while(curr!=null && prev!=null){
            if(curr.val != prev.val){
                return false;
            }
            curr = curr.next;
            prev = prev.next;
        }

        return true;
    }

    public Node getIntersectionNodeWithoutModifyList(Node headA, Node headB) {
        if(headA == null || headB == null){
            return null;
        }

        Node  a= headA;
        Node b=headB;

        while(a!=b){
            a = a==null ? headB:a.next;
            b = b== null ? headA:b.next;
        }

        return a;
    }
    public Node getIntersectionNode(Node headA, Node headB) {
        Node curr=headA;
        while(curr.next!=null){
            curr = curr.next;
        }
        curr.next=headB;

        Node slow = headA,fast=headA;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast =fast.next.next;
            if(slow == fast){
                return slow;
            }
        }
        return null;
    }

    public void reorderList(Node head){
        Node slow=head,fast=head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node second = reverse(slow.next);
        slow.next = null;
        Node first = head;
        Node dummy = new Node(0);
        Node curr = dummy;

        while (first != null || second != null) {

            if (first != null) {
                curr.next = first;
                first = first.next;
                curr = curr.next;
            }

            if (second != null) {
                curr.next = second;
                second = second.next;
                curr = curr.next;
            }
        }

        head = dummy.next;
    }


    private Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public Node reverseBetween(Node head, int left, int right) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        for(int i=0;i<left-1;i++){
            prev = prev.next;
        }

        Node curr = prev.next;

        for(int i=0;i<right-left;i++){
            Node forw = curr.next;
            curr.next = forw.next;
            forw.next = prev.next;
            prev.next = forw;
        }

        return dummy.next;
    }
    public void print(Node head){
        Node curr=head;
        System.out.println("========before=========");
        while(curr != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println("\n========after=========");
    }



    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);  ll.add(2);  ll.add(3);ll.add(4);ll.add(5);
        ll.print(ll.head);
        ll.reverse();
        ll.print(ll.head);
        System.out.println("middel > "+ll.middelOfLL());
        ll.removeNthNode(1);
        ll.print(ll.head);
        ll.add(3);
        System.out.println(ll.cycleDetect());

        LinkedList ll2 = new LinkedList();
        ll2.add(1);  ll2.add(2);
        LinkedList ll1 = new LinkedList();
        ll1.add(3);ll1.add(4);ll1.add(5);
        Node head = ll.mergedToLinkedList(ll2.head,ll1.head);
        ll.print(head);

        LinkedList ll3 = new LinkedList();
        ll3.add(1);  ll3.add(2);
        ll3.add(2); ll3.add(1);
        System.out.println(ll.isPeliLL(ll3.head));

        LinkedList ll4 = new LinkedList();
        ll4.add(1);  ll4.add(2);
        ll4.add(2); ll4.add(1);
//        ll4.removeAllMatchedWithGivenValue(2);
        ll4.removeAllMatchedWithGivenValueWithoutPrev(2);
        ll4.print(ll4.head);

        ll4.getIntersectionNode(ll4.head,ll4.head);

        LinkedList ll5 = new LinkedList();
        ll5.add(1);  ll5.add(2);
        ll5.add(3); ll5.add(4);

        ll5.reorderList(ll5.head);
        ll5.print(ll5.head);

        ll5.reverseBetween(ll5.head,1,2);
        ll5.print(ll5.head);


    }
}
