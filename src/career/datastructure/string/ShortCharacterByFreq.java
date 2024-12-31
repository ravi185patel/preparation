package career.datastructure.string;

import java.util.PriorityQueue;

public class ShortCharacterByFreq {
    public static void main(String[] args) {
        PriorityQueue<Character> pq = new PriorityQueue<>();
        pq.add('C');
        pq.add('C');
        pq.add((char)('A'+2));
        System.out.println(pq);
    }
}
