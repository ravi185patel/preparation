package career.sixmonthssep.graph;

import java.util.*;

public class ReconstructItinerary {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
//        [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]

//        [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]

        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));

        // jfk -> atl -> jfk -> sfo -> atl -> sfo.
        // jfk -> atl => jfk(atl,sfo) = jfk(sfo)
        // atl -> jfk => atl(jfk,sfo) = atl(sfo)
        // jfk -> sf0 => jfk()
        // sfo -> atl => again repeated.

        // another ans jfk -> sfo -> atl -> jfk -> atl -> sfo -- it is larger in lexical order
        System.out.println(new ReconstructItinerary().findItinerary(tickets));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> flights = createAdjList(tickets);
        LinkedList<String> path = new LinkedList<>();

        dfs("JFK",flights,path);
        // System.out.println(path);
        return path;
    }

    private void dfs(String departure,Map<String,PriorityQueue<String>> flights,LinkedList<String> path){

        PriorityQueue<String> arrivals = flights.get(departure);
        while(arrivals != null && !arrivals.isEmpty()){
            dfs(arrivals.poll(),flights,path); // remove from priorityQueue mapped with departure.
            // 1 -> 2, 2-> 1 i have to remove 1 from priority queue.
        }
        path.addFirst(departure); // add in path

    }

    private Map<String,PriorityQueue<String>> createAdjList(List<List<String>> tickets){
        Map<String,PriorityQueue<String>> flights = new HashMap<>();
        for(List<String> ticket:tickets){
            flights.putIfAbsent(ticket.get(0),new PriorityQueue<>());  // to get lexicographically smallest string;
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        return flights;
    }

}
