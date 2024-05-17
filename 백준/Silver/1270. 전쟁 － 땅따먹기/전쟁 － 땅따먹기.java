import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            HashMap<String, Long> hashMap = new HashMap<>();
            for (int j = 0; j < t; j++) {
                String num = st.nextToken();
                if (hashMap.containsKey(num)) {
                    hashMap.put(num, hashMap.get(num) + 1);
                } else {
                    hashMap.put(num, 1L);
                }
            }
            boolean find = false;
            for (String num : hashMap.keySet()) {
                long cnt = hashMap.get(num);
                if (cnt > t/2) {
                    sb.append(num).append("\n");
                    find = true;
                    break;
                }
            }
            if (!find) sb.append("SYJKGW\n");
        }
        System.out.println(sb.toString());
    }
}