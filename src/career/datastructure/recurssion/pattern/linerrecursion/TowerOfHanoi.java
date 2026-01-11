package career.datastructure.recurssion.pattern.linerrecursion;

public class TowerOfHanoi {
    public static void main(String[] args) {
        System.out.println(solve(3,1,2,3));
    }

    public static int solve(int n,int from,int to,int aux){
        if(n == 0 || n==1) return n;
        int count=0;
        count = solve(n-1,from,aux,to);
        count++;
        count += solve(n-1,aux,to,from);
        return count;
    }
}
