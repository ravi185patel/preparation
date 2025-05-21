package career.sixmonthssep.takeuforward;

/*

https://leetcode.com/problems/merge-sorted-array/description/ (easy)

1] simple while loop when find replace variable increase second array index.

https://takeuforward.org/plus/dsa-concept-revision/day-4/merge-two-sorted-arrays-without-extra-space (hard)


1] using priority queue
2] using two pointer
 */


public class MergeTwoSortedArray {
    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1=m-1,idx2=n-1;
        int index=nums1.length-1;
        while(idx1 >= 0 && idx2 >=0){
            if(nums1[idx1] > nums2[idx2]){
                nums1[index] = nums1[idx1];
                idx1--;
                index--;
            }else if(nums1[idx1] <= nums2[idx2]){
                nums1[index] = nums2[idx2];
                idx2--;
                index--;
            }
        }

        while(idx2>=0){
            nums1[index] = nums2[idx2];
            idx2--;
            index--;
        }
    }
    public void mergeIMP(int[] nums1, int m, int[] nums2, int n) {
        int idx1=m-1,idx2=n-1;
        int index=nums1.length-1;
        while(idx2 >=0){
            if(idx1 >= 0 && nums1[idx1] > nums2[idx2]){
                nums1[index] = nums1[idx1];
                idx1--;
                index--;
            }else{
                nums1[index] = nums2[idx2];
                idx2--;
                index--;
            }
        }

        //    while(idx2>=0){
        //       nums1[index] = nums2[idx2];
        //         idx2--;
        //         index--;
        //    }
    }
}
