package career.interview.amazon;

public class FindGreatestLexiString {



    // find adjcent character if same then change one and increase other; (i)
    // increase -> yz -> check character before y -> zyz -> z (pick which is not z and small) a -> replace all character with increasing sequence (ii)
    // zyzyzyzyz -> zyz -> zab
    // decrease -> da -> a replace by d+1; (iii)
    // abcda -> abcde
    // abcdz -> cdz -> c (not equal to d and z and also more then d) -> abcea
    // abczy -> abczz -> check if both same return -1
    // zzabc -> return -1  (iv) check full string

    // Driver Code
    public static void main (String[] args)
    {
//        String str = "samez";
//        String str = "abccdc";
//        String str = "zyzyzyzyz";
//        String str = "abcdc";
//        String str = "zzabc";
//        String str = "abczz";
//        String str = "abcza";
                String str = "abcdefghijklmnopqrstuvwza";
        System.out.print(nextString(str));
    }

    public static String nextString(String s){
        char ch[]=s.toCharArray();
        boolean flag = true;
        for(int i=1;i<ch.length;i++){
            if(ch[i] == ch[i-1]){
                if(ch[i] =='z') return "-1";
                ch[i] = (char)(ch[i]+1);
                updateChar(ch,i+1,'a');
                flag=false;
                break;
            }
        }
        if(flag) {
            for (int i = ch.length - 1; i >= 1; i--) {
                if (ch[i - 1] > ch[i]) { // z , y
                    char newChar = (char) (ch[i] + 1);
                    if (newChar == ch[i - 1]) {
                        newChar = (char) (newChar + 1);
                    }
                    if (newChar < 'a' || newChar > 'z') {
                        newChar = 'a';
                    }
                    ch[i] = newChar;
                    newChar++;
                    updateChar(ch, i + 1, newChar);
                    break;
                }
            }
        }
        if(isValidString(ch,s)){
            return String.valueOf(ch);
        }
        return "-1";
    }

    public static void updateChar(char ch[],int index,char newChar){
        for(int i=index;i<ch.length;i++){
            ch[i]=newChar;
            newChar++;
        }
    }


    public static boolean isValidString(char ch[],String s){
        for(int i=1;i<ch.length;i++){
            if(ch[i] == ch[i-1]){
                return false;
            }
        }
        return s.compareTo(String.valueOf(ch)) < 1;
    }


}