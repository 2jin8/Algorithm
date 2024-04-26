import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int total = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cow = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            if (hashMap.containsKey(cow)) {
                int prevRoad = hashMap.get(cow);
                if (road != prevRoad) {
                    total++;
                    hashMap.put(cow, road);
                }
            } else {
                hashMap.put(cow, road);
            }
        }
        System.out.println(total);
    }
}