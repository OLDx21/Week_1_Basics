package Week_2;

import java.util.HashMap;
import java.util.Map;

public class HashStorage {

    private static HashStorage hashStorage = new HashStorage();

    private HashMap<Integer, Integer> mainStorage = new HashMap<>();

    public static HashStorage getInstance() {
        return hashStorage;
    }

    synchronized public void readMap() {
        for (Map.Entry<Integer, Integer> s : mainStorage.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }

    synchronized public void writeToMap(Integer key, Integer value) {
        mainStorage.put(key, value);
    }

    public void fillData(){
        for(int i =0 ;i<30; i++){
            mainStorage.put(i, (i+i)*2);
        }
    }
    public Integer getSize(){
        return mainStorage.size();
    }
}
