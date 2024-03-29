import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] origin = new int[n];
        int[] sorted = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
            sorted[i] = origin[i];
        }
        Arrays.sort(sorted);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!hashMap.containsKey(sorted[i])) {
                hashMap.put(sorted[i], idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(hashMap.get(origin[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}