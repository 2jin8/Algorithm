import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lines = new int[N];
        for (int i = 0; i < N; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lines);

        System.out.println(biSearch(1, lines[N - 1]));
    }

    public static long biSearch(long start, long end) {
        long ans = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += lines[i] / mid;
            }
            if (total < M) { // 랜선이 더 필요한 경우
                end = mid - 1; // 왼쪽으로 범위 이동
            } else { // 랜선이 충분한 경우
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
}