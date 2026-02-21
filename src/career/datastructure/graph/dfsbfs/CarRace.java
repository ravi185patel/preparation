package career.datastructure.graph.dfsbfs;

import java.util.*;

public class CarRace {
    public static void main(String[] args) {
        System.out.println(racecar(3));
        System.out.println(racecar(5));
        System.out.println(racecar(4));
        System.out.println(racecar(6));
    }

    public static int racecar(int target) {
        return bfs(target);
    }

    public static int bfs(int target){
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{0,1,0});
        Set<String> set = new HashSet<>();
        set.add("0,1");
        while(!queue.isEmpty()){
            int point[]=queue.poll();
            int position = point[0];
            int speed = point[1];
            int steps = point[2];
            if(position == target){
                return steps;
            }
            // forward
            int forwardPosition = position + speed;
            int forwardSpeed = speed*2 ;

            int backwardPosition = position;
            int backwardSpeed = speed > 0 ? -1:1;

            if(2*target >= Math.abs(forwardPosition) && !set.contains(forwardPosition+","+forwardSpeed)){
                queue.add(new int[]{forwardPosition,forwardSpeed,steps+1});
                set.add(forwardPosition+","+forwardSpeed);
            }

            if(!set.contains(backwardPosition+","+backwardSpeed)){
                queue.add(new int[]{backwardPosition,backwardSpeed,steps+1});
                set.add(backwardPosition+","+backwardSpeed);
            }
        }
        return  0;
    }
}
