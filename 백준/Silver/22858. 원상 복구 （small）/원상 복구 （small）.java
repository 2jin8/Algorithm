import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] D, S, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = new int[N + 1];
        S = new int[N + 1];
        P = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                P[D[j]] = S[j];
            }
            S = P.clone();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(P[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}