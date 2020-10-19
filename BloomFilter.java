import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
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
            setB.add(i+999);
        }

        filter = new int[10000];        

    }

    public static void main(String args[]){

        BloomFilter bF = new BloomFilter();
        bF.encode();
        bF.lookup();
        
    }

    public void encode(){

        int index = 0, counter = 0;

        Collections.shuffle(randomList);

        //encode the filter with hashes of each element from set A
        for(int i : setA){
            
            while(counter < 7){
                int val = (randomList.get(index++)^i)%10000;
                
                if(filter[val] == 0){
                    filter[val] = 1;
                }
                counter++;
            }
            counter = 0;
        } 
    }

    public void lookup(){

        int index = 0, counter = 0, answer = 0;
        for(int i : setB){
            boolean flag = true;
            while(counter < 7){
                int val = (randomList.get(index++)^i)%10000;
                
                if(filter[val] == 0){
                    flag = false;
                    break;
                }
                counter++;
            }
            if(flag){
                answer++;
            }
            counter = 0;
        }

        System.out.println(answer);
    }

}

          /*
            1, 2,3, ..10000
            shuffle -> 988, 566, 122, 000...

            Set A - a, b, c, d
            988, 566, 122, 22, 9, 81, 1
*/
