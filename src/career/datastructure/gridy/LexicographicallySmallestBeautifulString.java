package career.datastructure.gridy;

public class LexicographicallySmallestBeautifulString {
    public static void main(String[] args) {
        System.out.println(smallestBeautifulString("abcz",24));
    }
    public static String smallestBeautifulString(String s, int k) {
        char sChar[]=s.toCharArray();
        int n=s.length();

        for(int i=n-1;i>=0;i--){

            int position = sChar[i]-'a'+1;

            for(int j = position;j<k;j++){
                char nextChar = (char)('a'+j);

                if((i > 0 && sChar[i-1] == nextChar) || ( i > 1 && sChar[i-2] == nextChar)){
                    continue;
                }

                sChar[i] = nextChar;
                for(int l = i + 1;l<n;l++){
                    for(int m=0;m<k;m++){
                        nextChar = (char) ( 'a'+m);
                        if((l > 0 && sChar[l-1] == nextChar || (l > 1 && sChar[l-2] == nextChar))){
                            continue;
                        }
                        sChar[l]=nextChar;
                        break;
                    }
                }
                return new String(sChar);
            }
        }
        return "";
    }

}
