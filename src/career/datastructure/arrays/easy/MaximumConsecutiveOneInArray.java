package career.datastructure.arrays.easy;

public class MaximumConsecutiveOneInArray {
    public static void main(String[] args) {
        int prices[] = {1, 1, 0, 1, 1, 1};

        int res = findMaxConOne(prices);
        System.out.println(res);
    }

    public static int findMaxConOne(int prices[]){
        int cnt=0;
        int max=0;
        for(int i=0;i<prices.length;i++){
            if(prices[i] == 1){
                cnt++;
            }else{
                cnt = 0;
            }
            max = Math.max(cnt,max);
        }

        return max;
    }
}
