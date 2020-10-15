import java.util.Set;
import java.util.HashSet;

class BloomFliter{

    Set<Integer> setA = new HashSet<>();
    Set<Integer> setB = new HashSet<>();

    public void BloomFilter() {
        
        // Two different set of elements each containing 1000 elements
        for(int i = 1001; i< 2001; i++){
            setA.add(i);
            setB.add(i+1000);
        }

    }

}