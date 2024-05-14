import java.io.*;
import java.util.*;

class Solution {
    static final int N = 100, M = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int testCase = Integer.parseInt(br.readLine());
            int[] scores = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int score = Integer.parseInt(st.nextToken());
                scores[score]++;
            }
            int max = scores[0], maxIdx = 0;
            for (int i = 1; i <= N; i++) {
                if (scores[i] >= max) {
                    max = scores[i];
                    maxIdx = i;
                }
            }
            sb.append("#").append(testCase).append(" ").append(maxIdx).append("\n");
        }
        System.out.println(sb.toString());
    }
}