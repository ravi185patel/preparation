package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.*;

/*
https://leetcode.com/problems/jump-game-iv/description/

 */
public class JumpGameIV {
    public static void main(String[] args) {
        System.out.println(minJump(new int[]{100,-23,-23,404,100,23,23,23,3,404}));
//        System.out.println(minJump(new int[]{7,6,9,6,9,6,9,7}));
//        System.out.println(minJump(new int[]{7}));


    }
    public static int minJump(int[] nums) {
        Map<Integer, Set<Integer>> map =new HashMap<>();
        /*
        You created this illegal interpretation:
        “From ANY index with value 100, I can jump to:
        all other 100s
        neighbors of ALL 100s”

        Main part: you can jump either index with same value | index-1 | index+1;
        So here you put all same value left and right child into single node which is wrong
        how other same value left and right child will be child of first one.

         */
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
                map.get(nums[i]).add(i - 1);
                if (i + 1 < nums.length) map.get(nums[i]).add(i + 1);
            }else{
                map.putIfAbsent(nums[i], new HashSet<>());
            }
        }

        return minJumpRec(nums,map);
    }

    public static int minJumpRec(int[] nums,Map<Integer, Set<Integer>> map) {
        Queue<int[]> queue = new LinkedList<>();
        int n = nums.length;
        boolean visited[]=new boolean[n];
        queue.add(new int[]{0,0});
        visited[0]=true;
        while(!queue.isEmpty()){
            int point[] = queue.poll();
            int index = point[0];
            int distance = point[1];
            if(index == nums.length-1) return distance;
            for(int nb:map.getOrDefault(nums[index],new HashSet<>())){
                if(!visited[nb]){
                    System.out.print(nb);
                    visited[nb]=true;
                    queue.add(new int[]{nb,distance+1});
                }
            }
            System.out.println();
        }
        return 0;
    }

    public static int minJumpRec(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        // value -> all indices with that value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // BFS level
            for (int s = 0; s < size; s++) {
                int i = queue.poll();

                // reached end
                if (i == n - 1) return jumps;

                // 1️⃣ same value jumps
                if (map.containsKey(arr[i])) {
                    for (int next : map.get(arr[i])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // 🔥 critical optimization
                    map.remove(arr[i]);
                }

                // 2️⃣ i + 1
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // 3️⃣ i - 1
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }
            }
            jumps++;
        }

        return -1;
    }
}
