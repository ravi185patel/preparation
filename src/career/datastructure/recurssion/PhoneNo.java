package career.datastructure.recurssion;

import java.util.ArrayList;
import java.util.List;

public class PhoneNo {
    char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};

    public static void main(String[] args) {
        PhoneNo phoneNo = new PhoneNo();
        List<String> words = phoneNo.letterCombinations("23");
        System.out.print(words);
    }
    public List<String> letterCombinations(String digits) {
        List<String> words = new ArrayList<>();
        dfs(digits,new StringBuilder(""),words,0);
        return words;
    }

    private void dfs(String digits,StringBuilder word,List<String> words,int index){
        if(index == digits.length()){
            words.add(word.toString());
            return;
        }

        int num = digits.charAt(index)-'0';
        for (int i = 0; i < map[num].length; i++) {
            word.append(map[num][i]);
            dfs(digits,word,words,index+1);
            word.deleteCharAt(word.length()-1);
        }
    }
}
