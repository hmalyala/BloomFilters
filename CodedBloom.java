import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class CodedBloom {

    //Map to store 7 sets and their corresponding 1000 elements
    Map<String,Set<Integer>> map;

    /* 
        This map stores the elements from all the sets and 
        their corresponding hashes for final lookup
    */
    Map<Integer,int[]> allElements;

    // This 2D array is used as a filter
    int filter[][];

    // random list to implement hashing
    List<Integer> randomList;

    public CodedBloom() {

        String arr[] = {"001","010","011","100","101","110","111"};

        map  = new HashMap<>();
        allElements= new HashMap<>();

        randomList = new ArrayList<>();
        for(int i = 5001 ; i < 35001; i++){
            randomList.add(i);
        }
        Collections.shuffle(randomList);

        Set<Integer> rows[] = new HashSet[7];

        for(int i = 0 ; i < 7; i++){
            rows[i] = new HashSet<>();
        }

        for (int i = 1; i < 1001; i++) {
            for(int j = 0 ; j < 7; j++){
                rows[j].add(i+(j*1000));
            }            
        }

        for(int i = 0 ; i < 7; i++){
            map.put(arr[i], rows[i]);
        }

        filter = new int[3][30000];   

    }

    public static void main(String[] args){

        CodedBloom cdF = new CodedBloom();
        cdF.encode();
        cdF.lookup();
    }

    public void encode() {

        for(String s : map.keySet()){
            for(int i = 0 ; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    helper(map.get(s),i);
                }
            }
        }
    }


    public void helper(Set<Integer> set, int position){
        int index = 0, counter = 0;

        //encode the filter with hashes of each element from set A
        for(int i : set){   
            int keys[] = new int[7];
            while(counter < 7){
                int val = (randomList.get(index++)^i)%30000;             
                filter[position][val]++;
                keys[counter] = val;
                counter++;
            }
            allElements.put(i, keys);
            counter = 0;
        } 
    }

    public void lookup(){

        int answer = 0;

        for(int i : allElements.keySet()){

            int values[] = allElements.get(i);
            StringBuilder str = new StringBuilder();

            for(int z = 0 ; z < 3; z++){
                boolean flag = true;
                for(int k : values){
                    if(filter[z][k] == 0){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    str.append('1');
                }
                else{
                    str.append('0');
                }
            }
            // System.out.println(str);
            if(map.get(str.toString()).contains(i)){
                answer++;
            }
        }
        System.out.println("number of elements whose lookup results are correct:"+answer);
    }
}