import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] D = new int[n + 1];
        int[] S = new int[n + 1];
        int[] P = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                P[D[j]] = S[j];
            }
            S = P.clone();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(P[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}