import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Long, Integer> hashMap = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        int max = 1;
        for (int i = 0; i < N; i++) {
            Long num = Long.parseLong(br.readLine());
            if (hashMap.containsKey(num)) {
                int get = hashMap.get(num);
                hashMap.put(num, get + 1);
                max = Math.max(max, get + 1);
            } else {
                hashMap.put(num, 1);
            }
        }


        List<Long> answer = new ArrayList<>();
        for (Long num : hashMap.keySet()) {
            if (hashMap.get(num) == max) {
                answer.add(num);
            }
        }
        Collections.sort(answer);
        System.out.println(answer.get(0));
    }
}