package career.datastructure.arrays.easy;

public class ValidWordSquare {
    public static void main(String[] args) {
//        String words[] = {"abcd","bnrt","crmy","dtye"};
        String words[] = {"abcd","bnrt","crmy","dtye"};
//        String words[] = {"ball","area","read","lady"};
        System.out.println(valid(words));
    }

    private static boolean valid(String[] words){
        int m = words.length;
        for(int i=0;i<m;i++){
            for(int j=0;j<words[i].length();j++) {
                // Check if the current column index is outside the number of rows
                // or if the row index is outside the length of the word at the current column index
                if (j >= m || i >= words[j].length()) {
                    return false;
                }
                if (words[i].charAt(j) != words[j].charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
