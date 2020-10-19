import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class CountingBloom {

    /*  Two different sets each one containing 
        1000 elements the other containing 
        500 elements
    */
    Set<Integer> setA;
    Set<Integer> setB;

    /*
        Map to store elements in set A and their
        corresponsing hashes for removal
    */
    Map<Integer,int[]> map;

    // random list to implement hashing
    List<Integer> randomList;

    // This array is used as a filter
    int filter[];

    public CountingBloom() {

        /*
            Initialising all the data structures
        */
        setA = new HashSet<>();
        setB = new HashSet<>();

        map  = new HashMap<>();

        randomList = new ArrayList<>();
        for(int i = 5000 ; i < 15000; i++){
            randomList.add(i);
        }
        Collections.shuffle(randomList);

        for(int i = 11; i< 511; i++){
            setA.add(i);
            setB.add(i*i);
            setA.add(i+(i*i));
        }

        filter = new int[10000];        

    }

    public static void main(String args[]){

        CountingBloom cbF = new CountingBloom();
        cbF.encode(cbF.setA);
        cbF.remove();
        cbF.encode(cbF.setB);
        cbF.lookup();
        
    }

    public void encode(Set<Integer> set){

        int index = 0, counter = 0;

        //encode the filter with hashes of each element from set A
        for(int i : set){   
            int keys[] = new int[7];
            while(counter < 7){
                int val = (randomList.get(index++)^i)%10000;                
                filter[val]++;
                keys[counter] = val;
                counter++;
            }
            map.put(i, keys);
            counter = 0;
        } 
    }

    /*
        Remove 500 elements from setA
    */
    public void remove(){

        int counter = 0;

        while(counter < 501){ 
            for(int i : setA){    
                int keys[] = map.get(i);
                for(int j : keys){
                    filter[j]--;
                }
            }
            counter++;
        }
    }

    public void lookup(){

        int answer = 0;
        for(int i : setA){
            int arr[] = map.get(i);
            boolean flag = true;
            for(int j: arr){
                if(filter[j] == 0){
                    flag = false;
                    break;
                }
                else{
                    filter[j]--;
                }
            }
            if(flag){
                answer++;
            }
        }

        System.out.println(answer);
    }
}
