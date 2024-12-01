package career.datastructure.segmenttree;

import java.util.Arrays;

class Alphabet{
    int n;
    char[] chArr;
    StringBuffer[] segArr;

    public Alphabet(char [] chArr){
        this.chArr=chArr;
        n = chArr.length;
        segArr = new StringBuffer[n*4];
        for(int i=0;i<segArr.length;i++){
            segArr[i]=new StringBuffer();
        }
        buildSegTree(1,0,n-1);
    }

    private  void buildSegTree(int ind,int left,int right){
        if(left == right){
            segArr[ind].append(chArr[left]);
            return;
        }
        int leftInd = 2 * ind;
        int rightInd = 2 * ind + 1;
        int mid = left + (right - left) / 2;

        buildSegTree(leftInd,left,mid);
        buildSegTree(rightInd,mid+1,right);
        segArr[ind].append(segArr[leftInd]).append(segArr[rightInd]);
    }

    public String query(int left,int right){
        return getQuery(1,0,n-1,left,right);
    }

    private String getQuery(int ind,int left,int right,int l,int r){
        if(left > r || right < l){
            return "";
        }
        if(left == right){
            return segArr[ind].toString();
        }else if( left == l && right == r){
            return segArr[ind].toString();
        }else{
            int mid = (left + right)/2;
            String leftString= getQuery(ind*2,left,mid,l,r);
            String rightString= getQuery(ind*2+1,mid+1,right,l,r);
            return leftString+rightString;
        }

    }
}
public class AlphabatRangeQueries {
    public static void main(String[] args) {
        char ch[]=new char[26];
        for(int i=0;i<26;i++){
            ch[i] = (char)('A'+i);
        }
        System.out.println(Arrays.toString(ch));

        Alphabet alphabet=new Alphabet(ch);
//        System.out.println(Arrays.toString(alphabet.segArr));
        System.out.println(alphabet.query(0,16));
    }
}
