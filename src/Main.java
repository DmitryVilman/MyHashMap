public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> list = new MyHashMap<>();
        list.put("Dmitry", 13);
        list.put("Anton",2);
        System.out.println(list.get("Dmitry"));
        System.out.println(list.get("Anton"));
        list.remove("Anton");
        System.out.println(list.get("Anton"));
    }

}
