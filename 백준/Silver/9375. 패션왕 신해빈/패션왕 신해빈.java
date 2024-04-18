import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String name = st.nextToken();
                String kind = st.nextToken();
                if (hashMap.containsKey(kind)) {
                    hashMap.put(kind, hashMap.get(kind) + 1);
                } else {
                    hashMap.put(kind, 1);
                }
            }

            List<Integer> list = hashMap.values().stream().collect(Collectors.toList());
            int ans = 1;
            for (int l : list) {
                ans *= l + 1;
            }
            sb.append(ans - 1).append("\n");
        }
        System.out.println(sb.toString());
    }
}