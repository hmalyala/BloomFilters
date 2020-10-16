import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class BloomFilter{

    Set<Integer> setA = new HashSet<>();
    Set<Integer> setB = new HashSet<>();
    List<Integer> randomList;

    int filter[];

    public BloomFilter() {
        
        randomList = new ArrayList<>();  // random list to implement hashing

        for(int i = 0 ; i < 10000; i++){
            randomList.add(i);
        }

        // Two different sets each containing 1000 elements
        for(int i = 1001; i< 2001; i++){
            setA.add(i);
            setB.add(i+1000);
        }

        filter = new int[10000];        

    }

    public static void main(String args[]){

        BloomFilter bF = new BloomFilter();

        Collections.shuffle(bF.randomList);

        bF.encode(bF.setA, bF.filter, bF.randomList);
        bF.encode(bF.setB, bF.filter, bF.randomList);

        for(int i : bF.filter){
            System.out.print(i+",");
        }
        
    }

    public void encode(Set<Integer> set, int[] filter, List<Integer> randomList){

        int index = 0, counter = 0;

        // We lookup each element from the set in the filter array
        for(int i : set){
            
            while(counter < 7 && index < 10000){

                int hashValue = (randomList.get(index++)^index)%10000;

                if(filter[hashValue] == 0){
                    filter[hashValue] = 1;
                }

            }

        }

    }

}