package career.datastructure.graph.design;

import java.util.ArrayList;
import java.util.List;

/*

Two approach
1] dfs --> gives TLE
  TC = O(n^26)
2] trie --> not explained but code is added
 */

class WordDictionary {
    private WordDictionary[] children;
    boolean isEndOfWord;
    // Initialize your data structure here.
    public WordDictionary() {
        children = new WordDictionary[26];
        isEndOfWord = false;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        WordDictionary curr = this;
        for(char c: word.toCharArray()){
            if(curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new WordDictionary();
            curr = curr.children[c - 'a'];
        }
        curr.isEndOfWord = true;
    }

    // Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        WordDictionary curr = this;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(c == '.'){
                for(WordDictionary ch: curr.children)
                    if(ch != null && ch.search(word.substring(i+1))) return true;
                return false;
            }
            if(curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr != null && curr.isEndOfWord;
    }
}

//class WordDictionary {
//
//    List<String> words;
//    public WordDictionary() {
//        words = new ArrayList<>();
//    }
//
//    public void addWord(String word) {
//        words.add(word);
//    }
//
//    public boolean search(String word) {
//        return dfs(0,word.toCharArray());
//    }
//
//    public boolean dfs(int index,char wChar[]){
//        if(index == wChar.length){
//            return words.contains(new String(wChar));
//        }
//        if(words.contains(new String(wChar))){
//            return true;
//        }
//        for(int i=index;i<wChar.length;i++){
//            if(wChar[i] == '.'){
//                for(char ch='a';ch<='z';ch++){
//                    wChar[i]=ch;
//                    if(dfs(i+1,wChar)){
//                        return true;
//                    }
//                    wChar[i]='.';
//                }
//            }
//        }
//        return false;
//    }
//}
public class DesignAddAndSearchWordsDS {
    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        String op[]={"WordDictionary","addWord","addWord","addWord","search","search","search","search"};
        String word[][]={{}, {"bad"}, {"dad"}, {"mad"}, {"pad"}, {"bad"}, {".ad"}, {"b.."}};

        for(int i=0;i<op.length;i++){
            if(op[i].equals("addWord")){
                wd.addWord(word[i][0]);
            }else if(op[i].equals("search")){
                System.out.println(wd.search(word[i][0]));
            }
        }
    }

}
