package career.sixmonthssep.graph;

/*
https://leetcode.com/problems/keys-and-rooms/description/?envType=study-plan-v2&envId=leetcode-75
 */

import java.util.LinkedList;
import java.util.Queue;

public class KeysAndRooms {
    public static void main(String[] args) {
        int rooms[][] = {{1},{2},{3},{}};
//        int rooms[][] = {{1,3},{3,0,1},{2},{0}};
//        int rooms[][] = {{1},{}};
        System.out.println(canVisitAllRooms(rooms));
    }

    public static boolean canVisitAllRooms(int rooms[][]) {
        int noOfRoom = rooms.length;
        boolean visited[] = new boolean[noOfRoom];
        Queue<Integer> queue = new LinkedList<>();
        visited[0]=true;
        queue.add(0);
        int roomVisited=0;
        while(!queue.isEmpty()){
            int room = queue.poll();
            roomVisited++;
            for(int roomKey:rooms[room]){
                if(visited[roomKey] == false){
                    visited[roomKey]=true;
                    queue.add(roomKey);
                }
            }
        }

        for(boolean visit:visited){
            if(visit==false){
                return false;
            }
        }
        return roomVisited == noOfRoom;
    }
}
