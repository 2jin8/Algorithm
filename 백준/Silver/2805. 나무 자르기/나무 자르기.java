import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 */

public class Main {
    private static int N, M;
    private static long sum = 0;

    // 완전 이분탐색 이용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] trees = new int[N];
        int min = 1, max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(trees[i], max);
        }

        int mid = 0;
        while (min <= max) {
//            mid = (min + max) / 2;
            sum = 0;
            for (int tree : trees) {
                if (tree - mid > 0) {
                    sum += tree - mid;
                }
            }

            if (sum > M) {
                min = mid + 1;
            } else if (sum < M) {
                max = mid - 1;
            } else {
                System.out.println(mid);
                return;
            }
            mid = (min + max) / 2;
        }
        System.out.println(mid);
    }
}