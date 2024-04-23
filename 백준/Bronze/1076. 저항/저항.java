import java.util.*;
import java.io.*;

public class Main {
    static HashMap<String, Integer> valueMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        int color1 = valueMap.get(br.readLine());
        int color2 = valueMap.get(br.readLine());
        long mul = (long) Math.pow(10, valueMap.get(br.readLine()));

        int color = Integer.parseInt(String.valueOf(color1) + String.valueOf(color2));
        System.out.println(color * mul);
    }

    public static void init() {
        valueMap.put("black", 0);
        valueMap.put("brown", 1);
        valueMap.put("red", 2);
        valueMap.put("orange", 3);
        valueMap.put("yellow", 4);
        valueMap.put("green", 5);
        valueMap.put("blue", 6);
        valueMap.put("violet", 7);
        valueMap.put("grey", 8);
        valueMap.put("white", 9);
    }
}