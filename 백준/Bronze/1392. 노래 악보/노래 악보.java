import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int idx = 0, page = 1;
        for (int i = 0; i < N; i++) {
            int q = Integer.parseInt(br.readLine());
            for (int j = 0; j < q; j++) {
                map.put(idx++, page);
            }
            page++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(map.get(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }
}