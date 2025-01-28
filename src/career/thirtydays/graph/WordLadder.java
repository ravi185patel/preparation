package career.thirtydays.graph;

import java.util.*;

/*
https://leetcode.com/problems/word-ladder/
https://leetcode.com/problems/word-ladder-ii

 */

class Pair{
    String word;
    int steps;
    public Pair(String word,int steps){
        this.word = word;
        this.steps = steps;
    }
}

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(ladderLength(beginWord,endWord,wordList));
    }
    public static boolean ladderLengthdfs(String beginWord, String endWord, List<String> wordList) {
        return dfs(0,beginWord.toCharArray(),endWord,wordList);
    }

    int min =0;
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for(String word:wordList){
            set.add(word);
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord,1));
        set.remove(beginWord);

        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int steps = pair.steps;
            String word = pair.word;
            if(word.equals(endWord)){
                return steps;
            }
            char wordCh[]=word.toCharArray();
            for(int i=0;i<wordCh.length;i++){
                char temp = wordCh[i];
                for(char c='a';c<='z';c++){
                    wordCh[i]=c;
                    String newWord = new String(wordCh);
                    if(set.contains(newWord) &&
                            !newWord.equals(beginWord)){
                        set.remove(newWord);
                        queue.add(new Pair(newWord,steps+1));
                    }

                }
                wordCh[i]=temp;
            }
        }
        return 0;
    }

    private static boolean dfs(int index,char[] beginWord,String endWord,List<String> wordList){

        if(index == beginWord.length){
            System.out.println(index);
            return false;
        }
        if(String.valueOf(beginWord).equalsIgnoreCase(endWord)){
            System.out.println("matched found");
            return true;
        }
        for(int i=0;i<beginWord.length;i++){
            char originalVal = beginWord[i];
            for(char c='a';c<='z';c++){
                beginWord[i]=c;
                if(wordList.contains(String.valueOf(beginWord))) {
                    System.out.println(String.valueOf(beginWord));
                    if(dfs(index+1, beginWord, endWord, wordList)){
                        System.out.println(" in dfs if");
                        return true;
                    }
                }
            }
            beginWord[i] = originalVal;
        }
        return false;
    }
}
