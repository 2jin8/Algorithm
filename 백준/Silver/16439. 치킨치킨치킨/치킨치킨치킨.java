import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] favorite = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                favorite[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3개씩 선택
        int maxTotal = 0;
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                if (i == j) continue;
                for (int k = j + 1; k < M; k++) {
                    if (i == k || j == k) continue;

                    int total = 0;
                    for (int p = 0; p < N; p++) {
                        total += Math.max(favorite[p][i], Math.max(favorite[p][j], favorite[p][k]));
                    }
                    maxTotal = Math.max(maxTotal, total);
                }
            }
        }
        System.out.println(maxTotal);
    }
}