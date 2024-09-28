package career.datastructure;

public class FindShortesRoute {
    public static void main(String[] args) {
        
    }

    private String findStRoute(String route){
            int countE = 0, countW = 0, countN = 0, countS = 0;
            for(int i = 0; i < route.length(); i++) {
                if(route.charAt(i) == 'E') {
                    countE++;
                }
                if(route.charAt(i) == 'W') {
                    countW++;
                }
                if(route.charAt(i) == 'S') {
                    countS++;
                }
                if(route.charAt(i) == 'N') {
                    countN++;
                }
            }

            String result = "";

        if(countE > countW) {
                for(int i = 0; i < (countE-countW); i++) {
                    result += 'E';
                }
            }
        if(countN > countS) {
                for(int i = 0; i < (countN-countS); i++) {
                    result += 'N';
                }
            }
        if(countS > countN) {
                for(int i = 0; i < (countS-countN); i++) {
                    result += 'S';
                }
            }
        if(countW > countE) {
                for(int i = 0; i < (countW-countE); i++) {
                    result += 'W';
                }
            }

        return result;
     }
}
