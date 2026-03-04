package career.interview.priceline.ll;

import career.common.UrlNode;

import java.util.HashMap;
import java.util.Map;

class List{
 int size;
 UrlNode head,tail;

 List(){
     head = new UrlNode("dummy","dummy");
     tail = new UrlNode("dummy","dummy");
     head.next = tail;
     tail.prev = head;
     size=0;
 }

 public void addFront(UrlNode node){
     UrlNode temp = head.next;
     node.next = temp;
     node.prev = head;
     head.next = node;
     temp.prev = node;
     size++;
 }
 public void removeNode(UrlNode node){
     UrlNode prevNode = node.prev;
     UrlNode nextNode = node.next;
     prevNode.next = nextNode;
     nextNode.prev = prevNode;
     size--;
 }
}
public class LFUCache {
    Map<String, UrlNode> keyNode;
    Map<Integer, List> freqMap;
    int maxSizeCache;
    int minFreq;
    int currSize;
    int capacity=0;
    UrlNode head,tail;
    public LFUCache(int capacity) {
        maxSizeCache = capacity;
        minFreq =0;
        currSize = 0;
        keyNode = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public String get(String key) {
        if(keyNode.containsKey(key)){
            UrlNode node = keyNode.get(key);
            String value = node.value;
            updateFreqMap(node);
            return value;
        }
        return " No Url Found";
    }

    public void put(String key, String value) {
        if(maxSizeCache == 0){
            return;
        }

        if (keyNode.containsKey(key)) {
            UrlNode node = keyNode.get(key);
            node.value = value;
            updateFreqMap(node);
        } else {
            if(currSize == maxSizeCache){
                List list = freqMap.get(minFreq);
                keyNode.remove(list.tail.prev.key);
                freqMap.get(freqMap).removeNode(list.tail.prev);
                currSize--;
            }
            currSize++;
            minFreq=1;
            List listFreq = new List();
            if(freqMap.containsKey(minFreq)){
                listFreq = freqMap.get(minFreq);
            }
            UrlNode node = new UrlNode(key,value);
            listFreq.addFront(node);
            keyNode.put(key,node);
            freqMap.put(minFreq,listFreq);
        }
    }

    private void updateFreqMap(UrlNode node){
        keyNode.remove(node.key);
        freqMap.get(node.freq).removeNode(node);
        if(node.freq == minFreq && freqMap.get(node.freq).size == 0){
            minFreq++;
        }

        List nextHigherFreqList = new List();
        if(freqMap.containsKey(node.freq+1)){
            nextHigherFreqList = freqMap.get(node.freq+1);
        }

        node.freq+=1;
        nextHigherFreqList.addFront(node);
        freqMap.put(node.freq,nextHigherFreqList);
        keyNode.put(node.key,node);
    }

    public static void main(String[] args) {
        // LFU Cache
        LFUCache cache = new LFUCache(2);

        // Queries
        cache.put("1", "1");
        cache.put("2", "2");
        System.out.print(cache.get("1") + " ");
        cache.put("3", "3");
        System.out.print(cache.get("2") + " ");
        System.out.print(cache.get("3") + " ");
        cache.put("4", "4");
        System.out.print(cache.get("1") + " ");
        System.out.print(cache.get("3") + " ");
        System.out.print(cache.get("4") + " ");
    }


}
