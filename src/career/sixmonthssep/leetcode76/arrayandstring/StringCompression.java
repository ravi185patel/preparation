package career.sixmonthssep.leetcode76.arrayandstring;

/*
link : https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.



Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 */
public class StringCompression {
    public static void main(String[] args) {
        char chars[] = {'a','a','b','b','c','c','c'};
        System.out.println(compress(chars)+" < "+String.valueOf(chars));
    }

    public static int compress(char[] chars) {
        int start=0,groupCnt=0,res=0,len = chars.length;

        while(start < len){
            int grpLen=1;
            while(start + grpLen < len &&
                chars[start + grpLen] == chars[start]){
                grpLen++;
            }

            chars[res++]=chars[start];
            if(grpLen > 1){
                for(char c:String.valueOf(grpLen).toCharArray()){
                    chars[res++]=c;
                }
            }
            start+=grpLen;
        }

        return res;
    }
}
