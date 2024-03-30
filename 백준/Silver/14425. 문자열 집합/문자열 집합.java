import java.util.*;
import java.io.*;

public class Main {
    static HashSet<String> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            hashSet.add(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (hashSet.contains(br.readLine())) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}