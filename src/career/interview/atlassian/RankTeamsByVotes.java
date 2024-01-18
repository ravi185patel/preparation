package career.interview.atlassian;

import java.util.HashMap;
import java.util.PriorityQueue;

/*
 leetcode :- https://leetcode.com/problems/rank-teams-by-votes/

 sorting + hashtable + counting.
 */

public class RankTeamsByVotes {

    public static String rankTeams(String[] votes) {

        int rank=votes[0].length();

        /*
          count each character at particular index.
          ABC,BCA
                [0,0,0]
          A ->  [1,0,1]
          B ->  [1,1,0]
          C ->  [0,1,1]
          ANS -> A,B,C
         */
        HashMap<Character,int[]> teamToVoteMap=new HashMap<>();
        for(String vote:votes){
            for(int i=0;i<rank;i++){
                char team = vote.charAt(i);
                teamToVoteMap.putIfAbsent(team, new int[rank]);
                teamToVoteMap.get(team)[i]++;
            }
        }

        PriorityQueue<Character> pq=new PriorityQueue<>((team1, team2) ->
        {
            int votes1[]=teamToVoteMap.get(team1);
            int votes2[]=teamToVoteMap.get(team2);
            for(int i=0;i<rank;i++){
                if(votes1[i] != votes2[i]){
                    return votes2[i] - votes1[i];
                }
            }
            return team1-team2;
        }
        );

        /*
          why pq ?
           need to sort based on rank + alphabetic order.

          You can use sorting which use n*n time to sort.
          where pq required n*logn time to sort and retrieve.
          normally pq required n*logn to remove and add n elements.

         */
        StringBuilder result = new StringBuilder();
        for(char c: teamToVoteMap.keySet()){
            pq.add(c);
        }

        while(!pq.isEmpty()){
            result.append(pq.poll());
        }
        return result.toString();

    }

    public static void main(String[] args) {
        String param[]={"ABC","ACB","ABC","ACB","ACB"};
        String res = rankTeams(param);
        System.out.println(res);
    }
}
