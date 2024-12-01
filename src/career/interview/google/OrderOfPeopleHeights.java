package career.interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

Problem statement : https://www.naukri.com/code360/problems/order-of-people-heights_1170764?topList=top-google-coding-interview-questions&problemListRedirection=true&leftPanelTabValue=PROBLEM
There are ‘N’ people numbered from 0 to N - 1, standing in a queue. You are given two arrays ‘Height’ and ‘Infront‘ consisting of ‘N’ non-negative integers. ‘Height[i]’ gives the height of the ith person, and ‘Infront[i]’ gives the number of persons who are taller than the ith person and standing in front of the ith person.

Your task is to find out the actual order of people in a queue. You should print ‘N’ integers where the ‘ith’ integer is the height of the person who should be at the ith position from the start of the queue.

Note :

1. Consider that all elements in array ‘Height’ are unique.
2. It is guaranteed that a valid order always exists for the given array ‘Height’ and ‘Infront’.
Example :

Let there are 6 people, their heights are given by array  ‘Height’ :  [5, 3, 2, 6, 1, 4],  and the number of people in front of them is given by array ‘Infront’: [0, 1, 2, 0, 3, 2]

Thus the actual order of people’s height in the queue will be [5, 3, 2, 1, 6, 4]


solution :
1] sorting
0 <> 6
[6]
0 <> 5
[5, 6]
2 <> 4
[5, 6, 4]
1 <> 3
[5, 3, 6, 4]
2 <> 2
[5, 3, 2, 6, 4]
3 <> 1
[5, 3, 2, 1, 6, 4]
[5, 3, 2, 1, 6, 4]
[5, 3, 2, 1, 6, 4]

2] segment tree => O(NlogN)

 */
class Person{
    int height;
    int position;
    public Person(int height,int position){
        this.height = height;
        this.position = position;
    }
}
public class OrderOfPeopleHeights {
    public static void main(String[] args) {
         List<Integer> infront = Arrays.asList(0, 1, 2, 0, 3, 2);
        List<Integer> height = Arrays.asList(5, 3, 2, 6, 1, 4);
        ArrayList<Integer> res = findOrder(height,infront);
        System.out.println(res);
        res = findOrderWithoutSorting(height,infront);
        System.out.println(res);
    }

    public static ArrayList<Integer> findOrderWithoutSorting(List<Integer> height, List<Integer> infront) {
        int maxPerson = 0;
        for(int personHeight:height){
            maxPerson = Math.max(maxPerson,personHeight);
        }
        int[] persons = new int[maxPerson+1];
        Arrays.fill(persons,-1);
        for(int i=0;i<height.size();i++){
            persons[height.get(i)] = infront.get(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=maxPerson;i>=0;i--){
            if(persons[i] == -1) continue;
            res.add(persons[i],i);
        }
        return res;
    }
    public static ArrayList<Integer> findOrder(List<Integer> height, List<Integer> infront) {
        List<Person> persons = new ArrayList<>();
        for(int i=0;i<height.size();i++){
            persons.add(new Person(height.get(i),infront.get(i)));
        }
        Collections.sort(persons,(person1, person2) ->{
            return person2.height - person1.height;
        });


        ArrayList<Integer> res = new ArrayList<>();
        for(Person person: persons){
            System.out.println(person.position+" <> "+person.height);
            res.add(person.position,person.height);
            System.out.println(res);
        }
        return res;
    }

}
