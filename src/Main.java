import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> bools = new MyHashMap<>();
        bools.put("Adolf", 10);
        System.out.println(bools.get("Adolf"));
    }

}