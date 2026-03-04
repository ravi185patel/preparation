package career.interview.priceline.bs;

public class AllocationBoooks {
    public static void main(String[] args) {
        System.out.println(findMinAllocationPages(new int[]{12, 34, 67, 90},4,2));
        System.out.println(findMinAllocationPages(new int[]{25,46,28,49,24},5,4));
    }
    public static int findMinAllocationPages(int arr[],int n,int m)
    {
        return solve(arr,n,m);
    }

    private static int solve(int[] arr,int n,int m){
        if(m > n) return -1;

        int low = 0;
        int high = 0;
        for(int i:arr){
            low = Math.max(i,low);
            high +=i;
        }

        while(low < high){
            int mid = low +(high-low)/2;
            int students = countStudents(arr,mid);
            if(students <= m){
                high = mid;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }

    private static int countStudents(int[] arr,int mid){
        int student =1;
        int alloactedPages=0;
        for(int i=0;i<arr.length;i++){
            if(alloactedPages + arr[i] <= mid){
                alloactedPages += arr[i];
            }else{
                student++;
                alloactedPages = arr[i];
            }
        }
        return student;
    }
}
