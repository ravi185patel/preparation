package career.datastructure.dp.mcmandpartitiondp;

import java.util.ArrayList;
import java.util.List;

public class EvaluateExpressionDiffWayToAchieveTrue {
    public static void main(String[] args) {

    }

    public static int noDiffWayToAchieveTrue(String expression){
        List<Character> chs = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        for(char c:expression.toCharArray()){
            if(Character.isAlphabetic(c)){
                chs.add(c);
            }else{
                ops.add(c);
            }
        }
        return solve(0,chs,ops);
    }

    public static int solve(int index,List<Character> chs,List<Character>ops){
        if(index < 0){
            return 0;
        }

//        for(int i=index;i<chs.size();i++){
//
//        }
        return 0;
    }

}
